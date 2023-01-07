document.getElementById("registration-form").reset();
const forms = document.querySelectorAll('.needs-validation');

Array.prototype.slice.call(forms).forEach((form) => {

    form.addEventListener('submit', (event) => {

        const password = document.getElementById("password");
        const repeatedPassword = document.getElementById("repeated-password");
        const repeatedPasswordContainer = document.getElementById("repeated-password-div");

        const isPasswordsNotMatch = password.value !== repeatedPassword.value;

        if (!form.checkValidity() || isPasswordsNotMatch) {
            event.preventDefault();
            event.stopPropagation();
            if (isPasswordsNotMatch) {
                repeatedPasswordContainer
                    .querySelector(".invalid-feedback")
                    .setAttribute("style", "display: block !important;");
            }
            repeatedPasswordContainer.setAttribute('style', 'margin-top: 3rem !important;');
        } else {
            repeatedPasswordContainer.removeAttribute('style');
        }
        form.classList.add('was-validated');
    }, false);
});
