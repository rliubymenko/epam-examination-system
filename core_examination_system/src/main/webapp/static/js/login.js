document.getElementById("login-form").reset();
const forms = document.querySelectorAll('.needs-validation');

Array.prototype.slice.call(forms).forEach((form) => {

    form.addEventListener('submit', (event) => {
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add('was-validated');
    }, false);
});

$(document).on('click', '#togglePassword', function () {
    const type = $('#password').attr('type') === "password" ? "text" : "password";
    $('#password').attr("type", type);
    $('#togglePassword').toggleClass('fa-eye-slash fa-eye');
})
