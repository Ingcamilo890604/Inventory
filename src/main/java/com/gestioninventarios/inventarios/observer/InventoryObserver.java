package com.gestioninventarios.inventarios.observer;

import com.gestioninventarios.inventarios.model.Product;

public interface InventoryObserver {
    void notifyChanges(Product product, String operation);
}
