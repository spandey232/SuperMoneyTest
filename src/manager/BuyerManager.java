package manager;

import Implemantations.BuyerImpl;
import interfaces.Buyer;

import java.util.ArrayList;
import java.util.List;

public class BuyerManager {
    private static List<Buyer> buyers;

    public BuyerManager() {
        buyers = new ArrayList<>();
    }

    public void addBuyer(String name) {
        Buyer buyer = new BuyerImpl(name);
        buyers.add(buyer);
    }

    public Buyer getBuyerByName(String name) {
        for (Buyer buyer : buyers) {
            if (buyer.getName().equals(name)) {
                return buyer;
            }
        }
        return null;
    }
}
