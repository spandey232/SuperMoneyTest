import interfaces.Buyer;
import manager.AuctionManager;
import manager.BuyerManager;
import manager.SellerManager;

public class MainDriverClassForAuction {
    private static  BuyerManager buyerManager = new BuyerManager();
    private static  SellerManager sellerManager = new SellerManager();
    private static  AuctionManager auctionManager = new AuctionManager();
    public static void main(String[] args) {
        // Sample test cases
        buyerManager.addBuyer("buyer1");
        buyerManager.addBuyer("buyer2");
        buyerManager.addBuyer("buyer3");

        sellerManager.addSeller("seller1");
        sellerManager.addSeller("seller2");

        auctionManager.createAuction("A1", 10, 50, 1, "seller1");
        auctionManager.createAuction("A2", 5, 20, 2, "seller2");

        auctionManager.placeBid("buyer1", "A1",17);
        auctionManager.placeBid("buyer2", "A1",15);
        auctionManager.updateBid("buyer2","A1",19);
        auctionManager.placeBid("buyer3", "A1",19);
        auctionManager.closeAuction("A1");

        Buyer winner = auctionManager.getWinner("A1");
        if (winner != null) {
            System.out.println("Winner: " + winner.getName());
        } else {
            System.out.println("No winner for auction A1.");
        }

        auctionManager.placeBid("buyer3","A2", 25); // it will fail with a message
        auctionManager.placeBid("buyer2", "A2",5);
        auctionManager.closeAuction("A2");

        Buyer winner2 = auctionManager.getWinner("A2");
        if (winner2 != null) {
            System.out.println("Auction A2 Winner: " + winner2.getName());
        } else {
            System.out.println("Auction A2 has no winner.");
        }
    }
}