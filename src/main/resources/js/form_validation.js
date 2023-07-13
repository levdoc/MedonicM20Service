function validateForm() {
    'use strict'

    const forms = document.querySelectorAll('.needs-validation');
    const role = document.getElementById("role");
    // Зацикливайтесь на них и предотвращайте отправку
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                // if (role.value === 'default') {
                //     alert("Выберите роль, создаваемого пользователя!");
                //     event.preventDefault()
                //     event.stopPropagation()
                //     return false;
                // }
                form.classList.add('was-validated')
            }, false)
        })
}