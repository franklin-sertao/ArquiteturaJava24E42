document.addEventListener("DOMContentLoaded", () => {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (event) => {
            const target = event.target;
            if (target.checked) {
                console.log(`Selecionado: ${target.name}`);
            } else {
                console.log(`Desmarcado: ${target.name}`);
            }
        });
    });
});
