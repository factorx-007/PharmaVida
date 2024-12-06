package com.pharmavida.service;

import com.pharmavida.strategy.ShoppingCart;
import com.pharmavida.strategy.CashPayment;
import com.pharmavida.strategy.CreditCardPayment;

public class ShoppingCartService {
    private ShoppingCart cart;

    public ShoppingCartService() {
        cart = new ShoppingCart();
    }

    public void processPayment(String paymentType, double amount) {
        switch (paymentType) {
            case "CASH":
                cart.setPaymentStrategy(new CashPayment());
                break;
            case "CREDIT_CARD":
                cart.setPaymentStrategy(new CreditCardPayment());
                break;
            default:
                System.out.println("Método de pago no soportado.");
                return;
        }
        cart.checkout(amount);
    }
}
