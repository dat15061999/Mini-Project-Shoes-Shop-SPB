$(async () => {

    renderBtnBrand();
    renderNav(navContent);
    changeUnderline("category", "Ca_");
    changeUnderline("price", "Pri_");
    changeUnderline("color", "Cl_");

    // products = await getAllProduct();

    page = await getAllProduct();

    products = page.content;

    productsPerPage = page.pageable.pageSize;

    renderProducts(products);
    renderPagination(Math.ceil(products.length / productsPerPage));
    handlePaginationClick();
    handleClick();
    await count()
    closeToast();
})