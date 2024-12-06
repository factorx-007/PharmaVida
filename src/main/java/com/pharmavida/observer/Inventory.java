package com.pharmavida.observer;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Observer> observers = new ArrayList<>();
    private int stock;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setStock(int stock) {
        this.stock = stock;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stock);
        }
    }
}

