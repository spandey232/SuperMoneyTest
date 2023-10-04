package interfaces;


import java.util.Map;

public interface Buyer {
    void markAsPreferred();
    void placeBid(Auction auction, int amount);
    void updateBid(Auction auction, int amount);
    void withdrawBid(Auction auction);
    String getName();
    boolean isPreferred();
    Map<Auction, Integer> getBids();
}