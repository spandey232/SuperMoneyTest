package Implemantations;

import interfaces.Auction;
import interfaces.Buyer;
import interfaces.Seller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AuctionImpl implements Auction {
    private String id;
    private int lowestBidLimit;
    private int highestBidLimit;
    private int participationCost;
    private Seller seller;
    private Map<Buyer, Integer> bids;
    private Buyer winner;

    public AuctionImpl(String id, int lowestBidLimit, int highestBidLimit, int participationCost, Seller seller) {
        this.id = id;
        this.lowestBidLimit = lowestBidLimit;
        this.highestBidLimit = highestBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
        this.bids = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getLowestBidLimit() {
        return lowestBidLimit;
    }

    @Override
    public int getHighestBidLimit() {
        return highestBidLimit;
    }

    @Override
    public int getParticipationCost() {
        return participationCost;
    }

    @Override
    public Seller getSeller() {
        return seller;
    }

    @Override
    public boolean placeBid(Buyer bidder, int amount) {
        if (amount < lowestBidLimit || amount > highestBidLimit) {
            System.out.println("Bid value is not in range of lowest and highest bid limit.Hence rejecting for "+bidder.getName()+" and amount:"+amount+" for auction:"+id);
            return false;  // Bid is not within limits
        }

        bids.put(bidder, amount);
        return true;
    }

    @Override
    public void closeAuction() {
        Map<Integer, Buyer> uniqueBids = new HashMap<>();
        for (Map.Entry<Buyer, Integer> entry : bids.entrySet()) {
            Buyer bidder = entry.getKey();
            int amount = entry.getValue();

            if (!uniqueBids.containsKey(amount)) {
                uniqueBids.put(amount, bidder);
            } else {
                uniqueBids.put(amount, null); // Mark as non-unique
            }
        }

        Buyer preferredWinner = null;
        int winningAmount = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Buyer> entry : uniqueBids.entrySet()) {
            int amount = entry.getKey();
            Buyer bidder = entry.getValue();

            if (bidder != null && bidder.isPreferred()) {
                if (amount < winningAmount) {
                    preferredWinner = bidder;
                    winningAmount = amount;
                }
            }
        }

        for (Map.Entry<Integer, Buyer> entry : uniqueBids.entrySet()) {
            int amount = entry.getKey();
            Buyer bidder = entry.getValue();

            if (bidder != null && bidder == preferredWinner) {
                winner = bidder;
                break;
            }
        }

        if (winner == null) {
            // If there is no preferred winner, find the next highest unique bid as the winner
            for (Map.Entry<Integer, Buyer> entry : uniqueBids.entrySet()) {
                int amount = entry.getKey();
                Buyer bidder = entry.getValue();

                if (bidder != null) {
                    winner = bidder;
                    break;
                }
            }
        }
    }

    @Override
    public void withdrawBid(Buyer bidder) {
        bids.remove(bidder);
    }

    @Override
    public Buyer getWinner() {
        return winner;
    }

    @Override
    public void updateBid(Buyer bidder, int amount) {
        if (bids.containsKey(bidder)) {
            bids.put(bidder, amount);
        }
    }

    @Override
    public Map<Buyer, Integer> getBids() {
        return Collections.unmodifiableMap(bids);
    }
}