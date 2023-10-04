package manager;

import Implemantations.AuctionImpl;
import interfaces.Auction;
import interfaces.Buyer;
import interfaces.Seller;

import java.util.ArrayList;
import java.util.List;

public class AuctionManager {

    private static List<Auction> auctions;
    private BuyerManager buyerManager;
    private SellerManager sellerManager;

    public AuctionManager() {
        auctions = new ArrayList<>();
        this.buyerManager = new BuyerManager();
        this.sellerManager = new SellerManager();
    }
    public void createAuction(String id, int lowestBidLimit, int highestBidLimit, int participationCost, String sellerName) {
        Seller seller = sellerManager.getSellerByName(sellerName);
        if (seller != null) {
            Auction auction = new AuctionImpl(id, lowestBidLimit, highestBidLimit, participationCost, seller);
            auctions.add(auction);
        } else {
            System.out.println("Seller not found: " + sellerName);
        }
    }

    public void placeBid(String buyerName, String auctionId, int amount) {
        Buyer buyer = buyerManager.getBuyerByName(buyerName);
        Auction auction = getAuctionById(auctionId);

        if (buyer != null && auction != null) {
            boolean success = auction.placeBid(buyer, amount);
            if (success) {
                System.out.println(buyerName + " placed a bid of " + amount + " on Auction " + auctionId);
                // Check if the buyer has participated in more than 2 auctions
                int auctionsParticipated = buyer.getBids().size();
                if (auctionsParticipated > 2 && !buyer.isPreferred()) {
                    buyer.markAsPreferred();
                    System.out.println(buyerName + " is now a preferred buyer.");
                }
            } else {
                System.out.println("Bid could not be placed. Check bid limits.");
            }
        } else {
            System.out.println("Buyer or auction not found.");
        }
    }

    public void updateBid(String buyerName, String auctionId, int amount) {
        Buyer buyer = buyerManager.getBuyerByName(buyerName);
        Auction auction = getAuctionById(auctionId);

        if (buyer != null && auction != null) {
            auction.updateBid(buyer, amount);
            System.out.println(buyerName + "'s bid on Auction " + auctionId + " updated to " + amount);
        } else {
            System.out.println("Buyer or auction not found.");
        }
    }

    public void closeAuction(String auctionId) {
        Auction auction = getAuctionById(auctionId);
        if (auction != null) {
            auction.closeAuction();
            Buyer winner = auction.getWinner();
            if (winner != null) {
                int winningBid = auction.getBids().get(winner);
                System.out.println("Auction " + auctionId + " Winner: " + winner.getName());
                System.out.println("Winning Bid: " + winningBid);
            } else {
                System.out.println("Auction " + auctionId + " has no winner.");
            }
        } else {
            System.out.println("Auction not found: " + auctionId);
        }
    }

    private Auction getAuctionById(String auctionId) {
        for (Auction auction : auctions) {
            if (auction.getId().equals(auctionId)) {
                return auction;
            }
        }
        return null;
    }

    public void withdrawBid(String buyerName, String auctionId) {
        Buyer buyer = buyerManager.getBuyerByName(buyerName);
        Auction auction = getAuctionById(auctionId);

        if (buyer != null && auction != null) {
            buyer.withdrawBid(auction);
            System.out.println(buyerName + " withdrew their bid on Auction " + auctionId);
        } else {
            System.out.println("Buyer or auction not found.");
        }
    }

    public Buyer getWinner(String auctionId) {
        Auction auction = getAuctionById(auctionId);
        if (auction != null) {
            return auction.getWinner();
        } else {
            System.out.println("Auction not found: " + auctionId);
            return null;
        }
    }
}
