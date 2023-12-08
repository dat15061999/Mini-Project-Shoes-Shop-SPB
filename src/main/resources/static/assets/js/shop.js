$(async () => {

    renderBtnBrand();
    renderNav(navContent);
    changeUnderline("category", "Ca_");
    changeUnderline("price", "Pri_");
    changeUnderline("color", "Cl_");

    // products = await getAllProduct();

    page = await getAllProduct();

    products = page.content;

    productsPerPage = page.totalPages;
    renderProducts(products);
    handlePaginationClick();
    handleClick();
    await count()
    closeToast();
})