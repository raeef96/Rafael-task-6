import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            Bid bid = new Bid(bidder, value);
            boolean successful = selectedLot.bidFor(bid);
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    
    public void close()
    {
        for(Lot lot : lots) {
            System.out.println(lot.getNumber() + ": " + lot.getDescription());
            // Include any details of a highest bid.
            Bid highestBid = lot.getHighestBid();
            if(highestBid != null) {
             System.out.println(" Highest bidder: " + highestBid.getBidder().getName());
             System.out.println(" Bid: " + highestBid.getValue());
            }
            else {
             System.out.println(" Not sold");
            }

        }
    }
    
    public ArrayList<Lot> getUnsold()
    {
        ArrayList<Lot> unsoldLots = new ArrayList<Lot>();
        for(Lot lot : lots) {
            Bid highestBid = lot.getHighestBid();
            if(highestBid == null) {
                unsoldLots.add(lot);
            }
        }
        return unsoldLots;
    } 
    
    public Lot getLot(int number)
    {
        Lot lot = null;
        if(lots.size() > 0) {
            lot = lots.get(0);
            int nextIndex = 1;
            while(lot.getNumber() != number && nextIndex < lots.size()) {
                lot = lots.get(nextIndex);
                nextIndex++;
            }
        }
        if (lot == null || lot.getNumber() != number) {
            System.out.println("Lot number: " + number + " does not exist.");
            return null;
        }
        else {
            return lot;
        }
    }
    
    public Lot removeLot(int number)
    {
        //First we find the lot with the given number
        Lot lot = getLot(number);
        if(lot != null) {
         //Then we can use the method remove with lot as argument
            lots.remove(lot);
        }
        return lot;
    }   
}  
