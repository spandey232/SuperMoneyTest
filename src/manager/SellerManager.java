package manager;

import Implemantations.SellerImpl;
import interfaces.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerManager {
    private static List<Seller> sellers;

    public SellerManager() {
        sellers = new ArrayList<>();
    }

    public void addSeller(String name) {
        Seller seller = new SellerImpl(name);
        sellers.add(seller);
    }

    public Seller getSellerByName(String name) {
        for (Seller seller : sellers) {
            if (seller.getName().equals(name)) {
                return seller;
            }
        }
        return null;
    }
}
