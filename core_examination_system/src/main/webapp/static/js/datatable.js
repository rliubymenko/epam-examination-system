function runSortWithPagination(sort, order, page, size, pageOperator) {
    submitRequest(sort, order, parseInt(page, 10) + pageOperator, size);
}

function runSort(sort, order, page, size) {
    if (order === 'desc') {
        order = 'asc';
    } else {
        order = 'desc';
    }
    submitRequest(sort, order, page, size);
}

function runSortByCriteria(event, sort, order, page, size) {
    const uuid = event.target.value;
    if (uuid !== '-1') {
        let personalSearch = document.getElementById('personalSearch');
        const searchIdInput = document.getElementById("searchIdInput");
        if (searchIdInput === undefined || searchIdInput === null) {
            const input = document.createElement("input");
            input.setAttribute("id", "searchIdInput");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "search_uuid");
            input.setAttribute("value", uuid);
            personalSearch.appendChild(input);
            runSearch(sort, order, page, size);
        } else if (uuid !== searchIdInput.getAttribute('value')) {
            searchIdInput.setAttribute("value", uuid);
            runSearch('created', 'desc', 1, 10);
        }
    }
}

function runSearch(sort, order, page, size) {
    const searchInput = document.getElementById('searchId');
    const searchQuery = searchInput.value;
    if (!!searchQuery) {
        const personalSearch = document.getElementById('personalSearch');
        const cloneSearch = searchInput.cloneNode(true);
        cloneSearch.setAttribute("type", "hidden");
        personalSearch.appendChild(cloneSearch);
        submitRequest(sort, order, page, size);
    } else {
        submitRequest(sort, order, page, size);
    }
}

function resetAll() {
    const personalSearchForm = document.getElementById('personalSearch');
    const searchIdInput = document.getElementById("searchIdInput");
    if (searchIdInput !== undefined && searchIdInput !== null) {
        searchIdInput.remove();
    }
    personalSearchForm.reset();
    personalSearchForm.submit();
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
