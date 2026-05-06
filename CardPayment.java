public class CardPayment extends payment {

    private String cardNumber;
    private String cardHolderName;

    public CardPayment(String orderId,double amount,String cardNumber,String cardHolderName){
        super(orderId,amount);
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolderName = cardHolderName;
    }

    private String maskCardNumber(String cardNumber){
        if (cardNumber != null && cardNumber.length() > 4){
            String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
            return "****_****_****_"+ lastFourDigits;

        }
        return "****-****-****-****";

    }

    @Override
    public void processPayment() {
        System.out.println("\n PROCESSING CARD PAYMENT..."+getOrderId());

        if(cardNumber != null && cardHolderName != null && !cardHolderName.isEmpty()){
            System.out.println("Card: "+ cardNumber);
            System.out.println("Card holder: "+ cardHolderName);
            System.out.println("Charging Rs." + getAmount());
            setstatus("COMPLETED");
            System.out.println("PAYMENT SUCCESSFULL.");
        }
        else{
            setstatus("FAILED");
            System.out.println("PAYMENT FAILED! Invalid card detailed");
        }

    }

    @Override
    public void displayDetail() {
        super.displayDetail();
        System.out.println("Payment Method: CARD");
        System.out.println("Card Number: "+cardNumber);
        System.out.println("Card Holder: "+ cardHolderName);
    }
}
