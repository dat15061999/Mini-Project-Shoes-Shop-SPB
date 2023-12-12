const divCreateProduct = $('div.product-form')
const btnShowCreate = $('.btn-create')
const btnCloseCreate = $('.close')
const divChooseFile = $('div.choose-file')
//create product
const frmProduct = $('#frm-product')
const addButton = $('#create-product');
const eleTitle = $('#title')
const elePrice = $('#price')
const eleCategory = $('#categories')
const eleCompany = $('#companies')
const eleColor = $('#colors')
let newFile;
let newFileToo;
let products = [];
let page = []
let currentPage = 0;
let perPage;
let alertContent;
divCreateProduct.hide()

async function getPageProduct() {
    return await $.ajax({
        url: AppUntil.BASE_PRODUCTS_API,
        method: "GET"
    })
}

async function getAllCompanies() {
    return await $.ajax({
        url: AppUntil.BASE_COMPANIES_API,
        method: "GET"
    })
}

async function getAllCategories() {
    return await $.ajax({
        url: AppUntil.BASE_CATEGORIES_API,
        method: "GET"
    })
}

async function getAllColors() {
    return await $.ajax({
        url: AppUntil.BASE_COLORS_API,
        method: "GET"
    })
}

btnShowCreate.on('click', async function () {
    divChooseFile.empty();
    const str = `
     <i class="fas fa-download" style="font-size: 50px;"></i>
     <span class="text-decoration-underline"> Browse a photo</span>
    `;
    divChooseFile.append(str);
    await renderSelectAll();
    divCreateProduct.show();
})

btnCloseCreate.on('click', function () {
    divCreateProduct.hide();
    $('#frm-product').children('input').val('');
    newFile = newFileToo;
    frmProduct[0].reset();
})

function renderProduct(obj) {
    return `
          <tr id=${obj.id}>
             <th class="text-start align-middle" style="min-width: 200px;">
                 <div class="d-flex align-items-center">
                     <img src=${obj.image.url} alt=${obj.title}
                          style="width: 70px;height: 40px">
                     <span class="ms-2">${obj.title}</span>
                 </div>
             </th>
             <th class="text-center align-middle">
                 <span class="badge px-2 py-1 border ${(obj.color.name === 'white') ? 'black' : 'white-sp'}" style="text-transform:capitalize ;
                  background-color: ${obj.color.name} ;           
                  ">${obj.color.name}</span>
             </th>
             <th class="text-center align-middle" style="text-transform:capitalize">${obj.category.name}</th>
             <th class="text-center align-middle">${obj.company.name}</th>
             <th class="text-center align-middle">
                    <div class="d-flex flex-column">
                        <span>$${obj.prevPrice}</span>
                        <del>$${obj.newPrice}</del>             
                    </div>       
             </th>
             <th class="text-center align-middle">5</th>
             <th class="text-center align-middle">
                 <i class="far fa-edit edit-product btn-ed me-2" style="color: green" onclick="editProduct(${obj.id})"></i>
                 <i class="fas fa-trash delete-product btn-ed" style="color: red" onclick="deleteProduct(${obj.id})"></i>
             </th>
          /tr>
    `;
}

function renderProducts(list) {
    $('#render-product').empty();
    list.forEach((item) => {
        const str = renderProduct(item);
        $('#render-product').append(str);
    })
}

function pagination(totalPages) {
    const pagination = $('.pagination');
    pagination.empty();
    let prevPage;
    if (currentPage <= 0) {
        prevPage = `<li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true" id="${currentPage}">Previous</a>
                    </li>`;
    } else {
        prevPage = `<li class="page-item">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true" id="${currentPage - 1}">Previous</a>
                    </li>`;
    }
    pagination.append(prevPage)
    for (let i = 0; i < totalPages; i++) {
        const str = `<li class="page-item ${i === currentPage ? 'active' : ''} " ><a class="page-link" href="#" id="${i}">${i + 1}</a></li>`;
        pagination.append(str);
    }
    let nextPage;
    if (currentPage + 1 >= totalPages) {
        nextPage = `<li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true" id="${currentPage}">Next</a>
                    </li>`;
    } else {
        nextPage = `<li class="page-item">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true" id="${currentPage + 1}">Next</a>
                    </li>`;
    }
    pagination.append(nextPage)
    handleClick();
}

function handleClick() {
    $('li.page-item').on('click', function () {
        currentPage = parseInt($(this).children('a').attr('id'));
        getProducts();
    })
    $('#pageSize,#sortField,#sort').on('change', function () {
        getProducts();
    })
}

function getProducts() {
    const page = currentPage;
    const size = $('#pageSize').find(':selected').val();
    const sortField = $('#sortField').find(':selected').val();
    const value = $('#sort').find(':selected').val();
    const data = {
        page,
        size,
        sort: "" + sortField + "," + value
    }
    $.ajax({
        url: AppUntil.BASE_PRODUCTS_API,
        method: "GET",
        data: data
    }).then((data) => {
        renderProducts(data.content);
        pagination(data.totalPages);
    })
}

//save file
divChooseFile.on('click', function () {
    $('#file-photo').click();

    $('#file-photo').on('change', function (event) {
        var file = event.target.files[0];
        var absolutePath = URL.createObjectURL(file);
        confirmUpload(file, absolutePath)
    })
})

function confirmUpload(file, absolutePath) {
    Swal.fire({
        title: "Are you sure?",
        text: "You will be upload image this!",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, upload it!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            newFile = file;
            renderImg(absolutePath);
            Swal.fire({
                text: "Your image has been uploaded.",
                icon: "success"
            });
        }
    });
}

function alert(content) {
    Swal.fire({
        text: content.text,
        icon: content.icon
    });
}

async function uploadImage(file) {
    const formData = new FormData();
    formData.append("images", file);
    formData.append("fileType", "image");
    return await $.ajax({
        url: AppUntil.BASE_UPLOAD_API + "/image",
        method: "POST",
        data: formData,
        processData: false,
        contentType: false
    });
}

frmProduct.validate({
    onkeyup: function (element) {
        $(element).valid();
    },
    onclick: false,
    onfocusout: false,
    rules: {
        eleTitle: {
            required: true
        },
        elePrice: {
            required: true
        },
        eleCategory: {
            required: true
        },
        eleCompany: {
            required: true
        },
        eleColor: {
            required: true
        }
    },
    messages: {
        eleTitle: {
            required: "Title is a required field"
        },
        elePrice: {
            required: "Price is a required field"
        },
        eleCategory: {
            required: "Category is a required field"
        },
        eleCompany: {
            required: "Company is a required field"
        },
        eleColor: {
            required: "Color is a required field"
        }
    },
    errorPlacement: function (error, element) {
        if (this.numberOfInvalids() > 0) {
            error.addClass("invalid-feedback");
        } else {
            error.removeClass("invalid-feedback")
        }
        error.insertAfter(element.parent());
        element.on('change', function () {
            $(this).valid();
        });
    },
    submitHandler: function () {
        createProduct();
    }
});

async function createProduct() {
    addButton.prop("disabled", true);
    $('#loading').removeClass("hide");
    const title = eleTitle.val();
    const price = elePrice.val();
    const idCategory = eleCategory.find(":selected").val();
    const idCompany = eleCompany.find(":selected").val();
    const idColor = eleColor.find(":selected").val();
    if (!newFile) {
        const alertContent = {
            text: "Your image has not been uploaded yet",
            icon: "warning"
        };
        alert(alertContent);
        addButton.prop("disabled", false);
        $('#loading').addClass('hide');
        return;
    }
    const formData = new FormData();
    formData.append("images", newFile);
    formData.append("fileType", "image");
    try {
        const image = await $.ajax({
            url: AppUntil.BASE_UPLOAD_API + "/image",
            method: "POST",
            data: formData,
            processData: false,
            contentType: false
        });
        const data = {
            title,
            price,
            idCategory,
            idCompany,
            idColor,
            image: image.id
        };
        $.ajax({
            url: AppUntil.BASE_PRODUCTS_API,
            method: "POST",
            data: JSON.stringify(data),
            contentType: "application/json"
        }).then((item) => {
            const alertContent = {
                text: "Your product has been saved.",
                icon: "success"
            };
            const str = renderProduct(item);
            $('#render-product').append(str);

            frmProduct[0].reset();
            $('#loading').addClass('hide');
            addButton.prop("disabled", false);
            alert(alertContent);
        })

    } catch (error) {
        event.preventDefault();
        console.error(error);
        const alertContent = {
            text: "An error occurred while creating the product.",
            icon: "error"
        };
        addButton.prop("disabled", false);
        $('#loading').addClass('hide');
        alert(alertContent);
    }
}

function renderImg(obj) {
    divChooseFile.empty();
    const str = renderImageAndButton(obj);
    divChooseFile.append(str);
}

function renderImageAndButton(obj) {
    return `
        <img src=${obj} style="max-width: 330px; max-height: 170px" >           
    `;
}

async function renderSelectAll() {
    await renderSelectOfCategory();
    await renderSelectOfCompany();
    await renderSelectOfColor();
}

async function renderSelectOfCategory() {
    const categories = await getAllCategories();
    eleCategory.empty();
    eleCategory.append(`<option value="">Please select category</option>`);
    categories.forEach((item) => {
        const str = `
            <option value="${item.id}">${item.name.charAt(0).toUpperCase() + item.name.slice(1)}</option>
        `;
        eleCategory.append(str);
    })
}

async function renderSelectOfCompany() {
    const companies = await getAllCompanies();
    eleCompany.empty();
    eleCompany.append(`<option value="">Please select company</option>`);
    companies.forEach((item) => {
        const str = `
            <option value="${item.id}">${item.name}</option>
        `;
        eleCompany.append(str);
    })
}

async function renderSelectOfColor() {
    const colors = await getAllColors();
    eleColor.empty();
    eleColor.append(`<option value="">Please select color</option>`);
    colors.forEach((item) => {
        const str = `
            <option value="${item.id}">${item.name.charAt(0).toUpperCase() + item.name.slice(1)}</option>
        `;
        eleColor.append(str);
    })
}

async function renderAll() {
    page = await getPageProduct();
    products = page.content;
    perPage = page.totalPages;
    renderProducts(products);
    pagination(perPage);
}

$(async () => {
    await renderAll();
})