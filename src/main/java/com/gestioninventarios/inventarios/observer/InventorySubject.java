package com.gestioninventarios.inventarios.observer;

import com.gestioninventarios.inventarios.model.Product;

public interface InventorySubject {
    void addObserver(InventoryObserver observer);
    void deleteObserver(InventoryObserver observer);
    void notifyObservers(Product product, String operation);
}
