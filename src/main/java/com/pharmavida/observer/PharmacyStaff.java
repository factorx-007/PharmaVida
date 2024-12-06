package com.pharmavida.observer;

public class PharmacyStaff implements Observer {
    @Override
    public void update(int stock) {
        if (stock < 5) {
            System.out.println("Alerta: stock bajo de medicamentos.");
        }
    }
}

