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

$(document).on('click', '#toggleOldPassword', function () {
    const type = $('#oldPassword').attr('type') === "password" ? "text" : "password";
    $('#oldPassword').attr("type", type);
    $('#toggleOldPassword').toggleClass('fa-eye-slash fa-eye');
})

$(document).on('click', '#toggleNewPassword', function () {
    const type = $('#newPassword').attr('type') === "password" ? "text" : "password";
    $('#newPassword').attr("type", type);
    $('#toggleNewPassword').toggleClass('fa-eye-slash fa-eye');
})

$(document).on('click', '#toggleRepeatedPassword', function () {
    const type = $('#repeatedPassword').attr('type') === "password" ? "text" : "password";
    $('#repeatedPassword').attr("type", type);
    $('#toggleRepeatedPassword').toggleClass('fa-eye-slash fa-eye');
})
