document.getElementById("registration-form").reset();
const forms = document.querySelectorAll('.needs-validation');

Array.prototype.slice.call(forms).forEach((form) => {

    const passwordContainer = document.getElementById("password-div");
    const repeatedPasswordContainer = document.getElementById("repeated-password-div");
    form.addEventListener('submit', (event) => {
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
            passwordContainer.setAttribute('style', 'margin-bottom: 3rem !important;');
            repeatedPasswordContainer.setAttribute('style', 'margin-bottom: 3rem !important;');
        } else {
            passwordContainer.removeAttribute('style');
            repeatedPasswordContainer.removeAttribute('style');
        }
        form.classList.add('was-validated');
    }, false);
});
