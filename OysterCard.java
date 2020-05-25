import java.math.RoundingMode;
import java.math.BigDecimal;

public class OysterCard{
   public static void main(String[] args) {
        OysterCard card = new OysterCard("someNr");
        card.topUp(15.00);

        card.touch(1); // in
        card.touch(1); // out

        card.touch(1); // in
        card.touch(2); // out

        card.touch(2); // in
        card.touch(1); // out

        card.touch(1); // in
        card.touch(3); // out

        card.touch(3); // in
        card.touch(2); // out

        card.touch(2); // in
        card.touch(2); // out

        card.touch(2); // in
        card.touch(3); // out
    }
    
    
   private double currentBalance = 0;
   private String cardNumber;
   private int touchInZone = 0;
   private double[][] priceTable = { { 2.40, 2.40, 2.80 }, { 2.40, 1.50, 1.50 }, {2.80, 1.50, 1.50} }; 

   public OysterCard(String cardNumber){
      
     this.cardNumber = cardNumber;
    
   };

  public String getCardNumber(){
     
      return cardNumber;
     
  };
  
  
 public double getBalance(){
    
    return currentBalance;
 };
 
 
 public boolean topUp(double amount){
    
      if(amount > 0){
        currentBalance += amount;
        currentBalance = BigDecimal.valueOf(currentBalance).setScale(2, RoundingMode.HALF_UP).doubleValue();
            return true;
        
      } else {
        return false;
     }
 };
 
  
  public boolean touch(int zone){
   
      if(currentBalance < 5){
        System.out.println("\n[ Warning ] Your balance is below 5.00!");
        return false;
      };
      
      if(touchInZone == 0){
         touchInZone = zone;
      } else {
            // exit
            double price = getTripPrice(touchInZone, zone);
            pay(price);
            System.out.println("\n[ Journey log ]" + "\n\tZoneIn: " + touchInZone + ", ZoneOut: " + zone
                    + "\n\tJourney price: " + price + "\n\tBalance rest: " + currentBalance);

            touchInZone = 0;
        
      };
      
      return true;
      
  };
  
    private double getTripPrice(int zoneIn, int zoneOut) {
        return priceTable[zoneIn - 1][zoneOut - 1];
    };
    
    
    private void pay(double price) {
        currentBalance -= price;
        currentBalance = BigDecimal.valueOf(currentBalance).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
};