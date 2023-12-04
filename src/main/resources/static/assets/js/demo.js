let products = [];

let newListProducts = [];

let i = 1;

let h = 1;

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
    $('button.btn-brand').on('click', function () {

        $('button.btn-brand').removeClass('active');

        $(this).addClass('active');

        filterDataFromTypePro()

    })
    $('button.btn-search').on('click', function () {
        filterDataFromTypePro();
    })

    $('input.in-search').on('input', function () {
        filterDataFromTypePro();
    })

}




function filterDataFromTypePro() {
    const valueBrand = $('button.active.btn-brand').val();
    const valueCategory = $('input.category:checked').val();
    const valueColor = $('input.color:checked').val();
    const valuePrice = $('input.price:checked').val();
    const [numberBefore, numberAfter] = (valuePrice === "All" || valuePrice === "Over150") ? "150-250".match(/^(\d+)-(\d+)$/)?.slice(1) : valuePrice.match(/^(\d+)-(\d+)$/)?.slice(1);
    const search = $('input.in-search').val().toLowerCase();

    newListProducts = products.filter((item) => {
        const brandMatch = (valueBrand !== "All Products") ? item.company === valueBrand : item.company !== valueBrand;
        const categoryMatch = (valueCategory !== "All") ? item.category === valueCategory.toLowerCase() : item.category !== valueCategory.toLowerCase();
        const colorMatch = (valueColor !== "All") ? item.color === valueColor.toLowerCase() : item.color !== valueColor.toLowerCase();
        const priceMatch = (valuePrice !== "All") ? (parseFloat(item.prevPrice) > parseFloat(numberBefore) && parseFloat(item.prevPrice) < parseFloat(numberAfter)) : item.prevPrice > 0;

        const searchMatch = item.company.toLowerCase().includes(search) ||
            item.title.toLowerCase().includes(search) ||
            item.color.includes(search) ||
            item.category.includes(search) ||
            item.prevPrice.toString().includes(search);

        return brandMatch && colorMatch && categoryMatch && priceMatch && searchMatch;
    });

    renderProducts(newListProducts);
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
    $('input.' + ele).on('change', function () {
        for (let i = 0; i < 6; i++) {
            const isChecked = $('#' + idName + i).is(':checked');
            $('label[for="' + idName + i + '"]').toggleClass('text-decoration-underline fw-bolder', isChecked);
        }
        filterDataFromTypePro();
    });

}

function renderPro(obj) {
    return `
        <div class="col-md-3 mb-4">
            <div class="card d-flex align-items-center pt-2">
                <img src=${obj.img}
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
                    <button class="btn btn-cart" type="button">
                        <i class="fas fa-cart-arrow-down"></i>
                    </button>
                </div>
            </div>
        </div>      
            `;
}

async function getAllProduct() {
    return await $.ajax({
        url: "https://jsonserver-vercel-api.vercel.app/products"
    })
}

function renderProducts(list) {
    $('.render-product').empty()

    list.forEach((ele) => {
        const str = renderPro(ele);

        $('.render-product').append(str);
    })
}




$(async () => {

    renderBtnBrand();
    renderNav(navContent);
    changeUnderline("category", "Ca_");
    changeUnderline("price", "Pri_");
    changeUnderline("color", "Cl_");

    products = await getAllProduct();
    renderProducts(products);
    handleClick();
})



