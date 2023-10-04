package interfaces;

import java.util.Map;

public interface Auction {
    String getId();
    int getLowestBidLimit();
    int getHighestBidLimit();
    int getParticipationCost();
    Seller getSeller();
    boolean placeBid(Buyer bidder, int amount);
    void closeAuction();

    void withdrawBid(Buyer bidder);

    Buyer getWinner();

    void updateBid(Buyer bidder, int amount);


    Map<Buyer, Integer> getBids();
}
