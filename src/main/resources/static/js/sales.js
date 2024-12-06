document.getElementById('comprarButton').addEventListener('click', function(event) {
    event.preventDefault();  // Evita la acción por defecto del formulario

    let id = document.getElementById('id').value;
    let nombre = document.getElementById('nombre').value;
    let precio = document.getElementById('precio').value;
    let stock = document.getElementById('stock').value;

    // Construir la URL con los parámetros
    let url = `/sales/ajax?id=${id}&nombre=${nombre}&precio=${precio}&stock=${stock}`;

    fetch(url, {
        method: 'GET',
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => response.text())
    .then(data => {
        // Aquí puedes procesar la respuesta AJAX
        document.getElementById('sales-frag').innerHTML = data;
    })
    .catch(error => {
        console.error('Error en la solicitud:', error);
        alert("Hubo un error al procesar la compra. Inténtalo de nuevo.");
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const medicamentoId = urlParams.get('id');
    const medicamentoNombre = urlParams.get('nombre');
    const medicamentoPrecio = urlParams.get('precio');
    const medicamentoStock = urlParams.get('stock');

    if (medicamentoId && medicamentoNombre && medicamentoPrecio) {
        // Rellenar el formulario de ventas con los datos del medicamento
        document.getElementById('totalAmount').value = medicamentoPrecio;

        const saleItemsTableBody = document.getElementById('saleItemsTableBody');
        saleItemsTableBody.innerHTML = `
            <tr>
                <td><input type="text" class="form-control" name="itemName" value="${medicamentoNombre}" required readonly></td>
                <td><input type="number" class="form-control" name="itemQuantity" min="1" max="${medicamentoStock}" value="1" required></td>
                <td><input type="number" class="form-control" name="itemPrice" value="${medicamentoPrecio}" required readonly></td>
                <td><input type="number" class="form-control" name="itemTotal" value="${medicamentoPrecio}" required readonly></td>
            </tr>
        `;

        const quantityInput = saleItemsTableBody.querySelector('input[name="itemQuantity"]');
        quantityInput.addEventListener('input', function() {
            const cantidad = parseInt(this.value);
            const precioUnitario = parseFloat(medicamentoPrecio);
            const total = cantidad * precioUnitario;
            saleItemsTableBody.querySelector('input[name="itemTotal"]').value = total.toFixed(2);
            document.getElementById('totalAmount').value = total.toFixed(2);
        });
    }
});
