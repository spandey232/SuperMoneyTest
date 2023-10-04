package Implemantations;

import interfaces.Seller;

public class SellerImpl implements Seller {
    private String name;

    public SellerImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}