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

function showHidePassword() {
    const showPasswordInput = document.getElementById("password");
    if (showPasswordInput.type === "password") {
        showPasswordInput.type = "text";
    } else {
        showPasswordInput.type = "password";
    }
}
