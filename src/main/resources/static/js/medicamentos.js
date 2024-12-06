document.addEventListener('DOMContentLoaded', function () {
    const buyButtons = document.querySelectorAll('.btn-success');

    buyButtons.forEach(button => {
        button.addEventListener('click', function () {
            const id = this.getAttribute('data-id');
            const nombre = this.getAttribute('data-nombre');
            const precio = this.getAttribute('data-precio');
            const stock = this.getAttribute('data-stock');

            document.getElementById('productName').innerText = `Producto: ${nombre}`;
            document.getElementById('productPrice').innerText = `Precio: S/ ${precio}`;
            document.getElementById('totalPrice').innerText = `Total: S/ ${precio}`;

            const quantityInput = document.getElementById('purchaseQuantity');
            quantityInput.addEventListener('input', function () {
                const cantidad = parseInt(this.value, 10);  // Asegúrate de convertir a entero
                const total = parseFloat(precio) * cantidad;
                document.getElementById('totalPrice').innerText = `Total: S/ ${total.toFixed(2)}`;
            });

            const modal = new bootstrap.Modal(document.getElementById('purchaseModal'));
            modal.show();

            document.getElementById('confirmPurchase').addEventListener('click', function () {
                const cantidad = parseInt(quantityInput.value, 10);  // Convertir cantidad a entero
                const total = parseFloat(precio) * cantidad;

                const data = {
                    detalles: [
                        {
                            producto: parseInt(id, 10),  // Asegurarse de que 'producto' sea entero
                            cantidad: cantidad,  // Asegurarse de que 'cantidad' sea entero
                        }
                    ]
                };
                const accessToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzMzMzQ5NjgzLCJpYXQiOjE3MzMzNDYwODMsImp0aSI6IjIwZTQxNjgwNzE0NzQ1YzY4ODQ5ZjlkNGYzM2NjNjZlIiwidXNlcl9pZCI6NX0.m4hKMpPsqxGcyl8OU3Q1-eWfeYQxl95IGdcoihqQXv8';  // Sin coma al final
                fetch('https://alexsandrovs.pythonanywhere.com/api/v1/facturas-cliente/', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${accessToken}`,
                    },
                    body: JSON.stringify(data),
                })
                .then(response => response.json())
                .then(data => {
                    console.log('Respuesta de la API:', data);
                    alert('Factura registrada exitosamente.');
                })
                .catch(error => {
                    console.error('Error al registrar la factura:', error);
                    alert('Hubo un error al registrar la factura.');
                });
            });
        });
    });
});
