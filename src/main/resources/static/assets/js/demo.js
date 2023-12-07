let products = [];
let page = [];
let newListProducts = [];
let currentPage = 1;
let productsPerPage;
const eleRenderCarts = $('#render-cart-detail');
let listCart = [];

function renderNav(obj) {
    obj.forEach((element) => {
        const str = render(element);
        $('.render-nav').append(str);
    });
}

function renderBtnBrand() {
    btnBrandProduct.forEach((ele, index) => {
        let str = ``;
        if (index === 0) {
            str += `<button class="btn btn-sm btn-outline-secondary me-1 active btn-brand" type="button" value="${ele}">${ele}</button>`;
        } else {
            str += `<button class="btn btn-sm btn-outline-secondary me-1 btn-brand"  type="button" value="${ele}">${ele}</button>`;
        }
        $('.render-btn-brand').append(str);
    })
}

function handleClick() {
    $('button.btn-brand').on('click', async function () {
        $('button.btn-brand').removeClass('active');
        $(this).addClass('active');
        await filterDataFromTypePro()
    })
    $('button.btn-search').on('click', async function () {
        await filterDataFromTypePro();
    })
    $('input.in-search').on('input', async function () {
        await filterDataFromTypePro();
    })
}

async function filterDataFromTypePro() {
    const valueBrand = $('button.active.btn-brand').val();
    const valueCategory = $('input.category:checked').val();
    const valueColor = $('input.color:checked').val();
    const valuePrice = $('input.price:checked').val();
    const [numberBefore, numberAfter] =
        (valuePrice === "All" ) ? "1-5550".match(/^(\d+)-(\d+)$/)?.slice(1) : (valuePrice === "Over150") ? "150-150".match(/^(\d+)-(\d+)$/)?.slice(1) :valuePrice.match(/^(\d+)-(\d+)$/)?.slice(1);
    const search = $('input.in-search').val().toLowerCase();
    const brandMatch = (valueBrand === "All Products") ? "" : valueBrand;
    const categoryMatch = (valueCategory === "All") ? "" : valueCategory.toLowerCase();
    const colorMatch = (valueColor === "All") ? "" : valueColor.toLowerCase();
    const data = {
        search: search,
        category: categoryMatch,
        company: brandMatch,
        color: colorMatch,
        min: numberBefore,
        max: numberAfter
    }
    const url = `http://localhost:8081/api/products`;
     newListProducts = await getAllProductByFilter(url, "GET", data);
    renderProducts(newListProducts.content);
}

async function getAllProductByFilter(url, method, data) {
    return await $.ajax({
        url: url,
        method: method,
        data: data,
        contentType: "application/json"
    });
}
async function callAPI(url, method, data) {
    return await $.ajax({
        url: url,
        method: method,
        data: JSON.stringify(data),
        contentType: "application/json"
    });
}
function render(obj) {
    let divs = '';
    for (let i = 0; i < obj.render.length; i++) {
        const div = renderDiv(obj, i, "checked");
        divs += div;
    }
    return `
        <div class="py-2 d-flex flex-column justify-content-center">
            <h5>${obj.content}</h5>
            <div class="form-group">
                ${divs}
            </div>
        </div>
    `;
}

function renderDiv(element, index, type) {
    let str = ``;
    if (index === 0) {
        str += `
        <div class="form-check py-1">
            <input type="radio" name="${element.type}" class="form-check-input ${element.type}" id="${element.id + index}"
                value="${element.render[index]}" style="${element.style[index]}" ${type}>
            <label for="${element.id + index}" role="button" class="form-check-label">${element.render[index]}</label>
        </div>   
        `
    } else {
        str += `
        <div class="form-check py-1">
            <input type="radio" name="${element.type}" class="form-check-input ${element.type}" id="${element.id + index}"
                value="${element.render[index]}" style="${element.style[index]}">
            <label for="${element.id + index}" role="button" class="form-check-label">${element.render[index]}</label>
        </div>   
        `
    }
    return str;
}

function changeUnderline(ele, idName) {
    $('input.' + ele).on('change', async function () {
        for (let i = 0; i < 6; i++) {
            const isChecked = $('#' + idName + i).is(':checked');
            $('label[for="' + idName + i + '"]').toggleClass('text-decoration-underline fw-bolder', isChecked);
        }
        await filterDataFromTypePro();
    });
}

function renderPro(obj) {
    return `
        <div class="col-md-3 mb-4">
            <div class="card d-flex align-items-center pt-5">
                <img src=${obj.image.url}
                    alt="" style="width: 150px; height: 100px;">
                <div class="card-body">
                    <p class="fw-bolder">
                        ${obj.title}
                    </p>
                </div>
                <div class="d-flex align-items-center justify-content-between">
                    <div>                        
                        <span>$${obj.prevPrice}</span>
                        <del>${obj.newPrice}</del>
                    </div>
                    <button class="btn btn-cart" type="button" id="${obj.id}">
                        <i class="fas fa-cart-arrow-down"></i>
                    </button>
                </div>
            </div>
        </div>      
            `;
}

async function getAllProduct() {
    return await $.ajax({
        url: "http://localhost:8081/api/products"
    })
}

function renderProducts(list) {
    $('.render-product').empty();
    const startIndex = (currentPage - 1) * productsPerPage;
    const endIndex = startIndex + productsPerPage;
    const paginatedList = list.slice(startIndex, endIndex);
    paginatedList.forEach((ele) => {
        const str = renderPro(ele);
        $('.render-product').append(str);
    })
}

function renderPagination(totalPages) {
    const elePage = $('.pagination');
    elePage.empty();
    let prevButton = '';
    if (currentPage > 1) {
        prevButton = ` <li class="page-item">
                           <a class="page-link" data-page="${currentPage - 1} href="#" tabindex="-1" aria-disabled="true">Previous</a>
                       </li>`;
    } else {
        prevButton = ` <li class="page-item disabled">
                           <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                       </li>`;
    }
    elePage.append(prevButton);
    for (let i = 1; i <= totalPages; i++) {
        const str = `<li class="page-item ${i === currentPage ? 'active' : ''}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
        elePage.append(str);
    }
    let nextButton = '';
    if (currentPage < totalPages) {
        nextButton = ` <li class="page-item">
                                    <a class="page-link" data-page="${currentPage + 1}" href="#">Next</a>
                       </li>`;
    } else {
        nextButton = ` <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
                       </li>`;
    }
    elePage.append(nextButton);
}

function handlePaginationClick() {
    $('.pagination').on('click', 'a', function () {
        currentPage = parseInt($(this).data('page'));
        renderProducts(products);
        renderPagination(Math.ceil(products.length / productsPerPage));
    });
    $('.btn-cart').on("click", async function () {
        const idPro = parseFloat($(this).attr("id"));
        const quantity = 1;
        const data = {
            idProduct: idPro,
            quantity
        }
        const url = "http://localhost:8081/api/products/cart"
        await callAPI(url, "POST", data)
        showToast();
        await count();
    })
}

function showToast() {
    let toast = document.querySelector('.toast');
    let progress = document.querySelector('.progress')
    $("button.btn-cart").hide();
    toast.style.display = 'block';
    progress.classList.add("active");
    setTimeout(() => {
        progress.classList.remove("active");
        $("button.btn-cart").show();
    }, 1000)
    setTimeout(function () {
        toast.style.display = 'none';
    }, 1000);
}

function closeToast() {
    let toastClose = document.querySelector('.toast-close');
    toastClose.addEventListener('click', function () {
        let toast = document.querySelector('.toast');
        toast.style.display = 'none';
    });
}

async function getCount() {
    return await $.ajax({
        url: "http://localhost:8081/api/products/amountCartDetail"
    })
}
async function count() {
    const count = await getCount();
    if (count > 0) {
        $('#count').text(count);
        $('#count').show();
        $('button.btn-checkOut').show();
    } else {
        $('#count').hide();
        $('button.btn-checkOut').hide();
    }
}
function renderCart(obj) {
    return `
            <tr class="align-content-center" style="vertical-align: middle;" id="${obj.id}">
                <td style="max-width: 200px;">
                    <div class="d-flex align-items-center">
                        <img class="product-image"
                            src=${obj.url} alt=""
                            style=" height: 50px;">
                        <div class="d-inline">
                            <div class="d-block fw-bolder mb-2">${obj.productName}</div>                            
                            <div class="badge py-2" style="background-color: ${obj.color === 'white' ? "#9f9b9b" : obj.color};">${obj.color}</div>
                        </div>
                    </div>
                </td>
                <td class="text-end">$${obj.productPrice}</td>
                <td class="text-center">
                    <div class="align-content-center" role="group" aria-label="Basic outlined example" >
                        <button type="button" class="btn btn-outline-secondary btn-minus"><i class="fas fa-minus"></i></button>
                        <input type="button" class="btn quantity_${obj.id}" value=${obj.quantity} readonly>
                        <button type="button" class="btn btn-outline-secondary btn-plus"><i class="fas fa-plus"></i></button>
                    </div>
                </td>
                <td class="text-end amount_${obj.id}">$${obj.total}</td>
                <td class="text-center">
                    <button type="button" class="btn" onclick="confirmDelete(${obj.id})"><i class="fas fa-trash"></i></button>
                </td>
            </tr>
    `;
}

async function getAllCarts() {
    return await $.ajax({
        url: "http://localhost:8081/api/products/cart",
        method: "GET"
    })
}

async function renderCarts(list) {
    eleRenderCarts.empty();
    list.forEach((item) => {
        const str = renderCart(item);
        eleRenderCarts.append(str);
    })
}
 function confirmDelete(id){
    showAlertById(id);
}

function showAlertById(id) {
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            await deleteProduct(id);
            deleteEle(id);
            await count();
            changeTotalAmount();
            await count();
            Swal.fire({
                title: "Deleted!",
                text: "Your product has been deleted.",
                icon: "success"
            });
        }
    });
}
function deleteEle(idCart) {
    $('#render-cart-detail').children(`tr[id="${idCart}"]`).remove();
}

async function deleteProduct(id) {
    await $.ajax({
        url: "http://localhost:8081/api/products/cart/" + id,
        method: "DELETE"
    })
}




