function handleClickBtnPlusAndMinus(item) {
    $(item).off('click');
    $(item).on('click',  function () {
        const idCart = $(this).closest("tr").attr("id");
        const quantity = parseFloat($(".quantity_"+idCart).val());
        if (quantity <= 1 && item === 'button.btn-minus') {
            confirmDelete(idCart);
            return;
        } else if ( quantity >= 50 && item === 'button.btn-plus') {
            alert("het hang")
            return;
        }
        if (item  === 'button.btn-plus') {
             changeQuantity(idCart, 1);
        } else {
             changeQuantity(idCart, -1);
        }
    })
}

 function changeQuantity(idCart, data) {
    const item = {
        quantity: data
    }
     $.ajax({
        url: "http://localhost:8081/api/products/cart/" + idCart,
        method: "PATCH",
        data: JSON.stringify(item),
        contentType: "application/json"
    }) .then(()=>{
         showToast();
         renderNewCart(idCart);
    })
}
function renderNewCart(idCart) {
    $.ajax({
        url: "http://localhost:8081/api/products/cart/" + idCart,
        method: "GET"
    }).then((data)=>{
        const str = renderCart(data);
        $('tr[id="' + idCart + '"]').replaceWith(str);
        handleClickBtnQuantity();
        changeTotalAmount();
    })
}
function handleClickBtnQuantity(){
    handleClickBtnPlusAndMinus('button.btn-plus');
    handleClickBtnPlusAndMinus('button.btn-minus')
}
function changeTotalAmount(){
    let total = 0;
    $('#render-cart-detail').children("tr").each(function () {
        const amount = parseFloat($('.amount_'+ $(this).attr('id')).text().replace('$',''))
        total += amount;
    })
    $('span.subtotal').text("$"+ total);
    $('span.total').text("$"+ total);
}

function checkOutBill() {

}


$(async () => {
    listCart = await getAllCarts();
    await count()
    await renderCarts(listCart);
    handleClickBtnQuantity()
    changeTotalAmount();
})