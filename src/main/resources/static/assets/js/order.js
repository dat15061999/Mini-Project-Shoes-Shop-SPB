let bills = []
const divOrderDetail = $('#render-billDetail');
const divBills = $('#order-detail');
async function getAllBill(){
    return await $.ajax({
        url:AppUntil.BASE_BILLS_API,
        method: "GET"
    })
}
async function getBillAndBillDetail(idBill){
    return await $.ajax({
        url:AppUntil.BASE_BILL_DETAIL_API + idBill,
        method: "GET"
    })
}

function renderBill(obj){
    return `
        <tr id=${obj.id}>
            <th class="text-center align-middle">${obj.created}</th>
            <th class="text-center align-middle">${obj.totalProduct}</th>
            <th class="text-center align-middle">$${obj.total}</th>
            <th class="text-center align-middle">Free</th>
            <th class="text-center align-middle">$${obj.total}</th>
            <th class="text-center align-middle">
                <span class="badge px-2 py-1 border"
                      style="background-color: #938e8e ; color: white">${obj.status}
                </span>
            </th>
            <th class="text-star align-middle">${obj.customerName}</th>
            <th class="text-end align-middle">
                <i class="fas fa-outdent edit-product btn-ed me-2" onclick="showDetail(${obj.id})" style="color: green"></i>
            </th>
       </tr>
    `;
}

function renderBills(list){
    const eleBill = $('#render-bill');
    eleBill.empty();

    list.forEach((item)=> {
        const str = renderBill(item);
        eleBill.append(str);
    })
}

async function showDetail(idBill){
    const bill = await getBillAndBillDetail(idBill);
    if (bill !== null) {
        changeClass();
        const divRender = $('#render-billDetail');
        divRender.empty()
        const str = renderBillDetail(bill);
        divRender.append(str);
        renderOrderDetail(bill.id,bill.billDetails);
    }
}

function changeClass(){
    divOrderDetail.addClass("col-lg-5 border p-2");
    divOrderDetail.show();
    divBills.removeClass();
    divBills.addClass("col-md-7");
}

function closeOrderDetail(){
    divOrderDetail.removeClass();
    divOrderDetail.hide();
    divBills.removeClass();
    divBills.addClass("col-md-12");
}
function renderBillDetail(obj){
    return `
        <div class="d-flex align-content-center justify-content-md-between border-bottom">
            <h5>Order details</h5>
            <span role="button" class="btn-close" onclick="closeOrderDetail()"></span>
        </div>
        <div class="my-2 border-bottom">
            <h6>Order Information</h6>
            <div class="d-flex justify-content-md-between mb-2">
                <span>Subtotal</span>
                <span class="fw-bolder">$${obj.total}</span>
            </div>
            <div class="d-flex justify-content-md-between mb-2">
                <span>Shipping</span>
                <span class="fw-bolder">Free</span>
            </div>
            <div class="d-flex justify-content-md-between mb-2">
                <span>Total</span>
                <span class="fw-bolder">$${obj.total}</span>
            </div>
        </div>
        <div class="my-2 border-bottom">
            <h6>Customer Information</h6>
            <div class="d-flex justify-content-between mb-2">
                <span>FullName</span>
                <span class="fw-bolder">${obj.customer.name}</span>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <span>Email</span>
                <span class="fw-bolder">${obj.customer.email}</span>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <span>Mobile</span>
                <span class="fw-bolder">${obj.customer.phone}</span>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <span>Address</span>
                <span class="fw-bolder">${obj.customer.address}</span>
            </div>
        </div>
        <div class="my-2">
            <h6>Order details</h6>
            <table class="table table-striped">
                <tbody id="bill_${obj.id}">
                <tr>
                    <td style="width:250px">anh</td>
                    <td class="text-end align-middle">1</td>
                    <td class="text-end align-middle">price</td>
                    <td class="text-end align-middle">tien</td>
                </tr>
                </tbody>
            </table>
        </div>
    `;
}
function renderOrderDetail(id,list){
    $('#bill_'+id).empty();

   list.forEach((item)=>{
       const str = renderProductDetail(item);
       $('#bill_'+id).append(str);
   })

}
function renderProductDetail(obj){
    return `
     <tr>
         <td style="width:250px">
            <img src=${obj.url} alt="" style="width: 30px; height: 20px">
         </td>
         <td class="text-end align-middle">${obj.quantity}</td>
         <td class="text-end align-middle">$${obj.productPrice}</td>
         <td class="text-end align-middle">$${obj.total}</td>
     </tr>
    `;
}


$(async ()=>{
    divOrderDetail.removeClass();
    divOrderDetail.hide();
    bills = await getAllBill();
    renderBills(bills);

})