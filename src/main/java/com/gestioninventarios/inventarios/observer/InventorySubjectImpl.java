package com.gestioninventarios.inventarios.observer;

import com.gestioninventarios.inventarios.model.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InventorySubjectImpl implements InventorySubject{

    private final List<InventoryObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(InventoryObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Product product, String operation) {
        for (InventoryObserver observer : observers) {
            observer.notifyChanges(product, operation);
        }
    }

}
