package Implemantations;

import interfaces.Auction;
import interfaces.Buyer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BuyerImpl implements Buyer {
        private String name;
        private boolean isPreferred;
        private Map<Auction, Integer> bids;

        public BuyerImpl(String name) {
            this.name = name;
            this.isPreferred = false;
            this.bids = new HashMap<>();
        }

        @Override
        public void markAsPreferred() {
            this.isPreferred = true;
        }

        @Override
        public void placeBid(Auction auction, int amount) {
            bids.put(auction, amount);
        }

        @Override
        public void updateBid(Auction auction, int amount) {
            if (bids.containsKey(auction)) {
                bids.put(auction, amount);
            }
        }

        @Override
        public void withdrawBid(Auction auction) {
            bids.remove(auction);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean isPreferred() {
            return isPreferred;
        }

        @Override
        public Map<Auction, Integer> getBids() {
            return Collections.unmodifiableMap(bids);
        }
}
