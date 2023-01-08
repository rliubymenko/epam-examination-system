function runSortWithPagination(sort, order, page, size, pageOperator) {
    submitRequest(sort, order, parseInt(page,10) + pageOperator, size);
}

function runSort(sort, order, page, size) {
    if (order === 'desc') {
        order = 'asc';
    } else {
        order = 'desc';
    }
    submitRequest(sort, order, page, size);
}

function submitRequest(sort, order, page, size) {
    let personalSearchSubmit = document.getElementById('personalSearchSubmit');
    if (personalSearchSubmit !== null) {
        let personalSearch = document.getElementById('personalSearch');
        if (personalSearch !== null) {
            let input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "sort");
            input.setAttribute("value", sort);
            personalSearch.appendChild(input);
            input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "order");
            input.setAttribute("value", order);
            personalSearch.appendChild(input);
            input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "page");
            input.setAttribute("value", page);
            personalSearch.appendChild(input);
            input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "size");
            input.setAttribute("value", size);
            personalSearch.appendChild(input);
            personalSearch.submit();
        }
    }
}
