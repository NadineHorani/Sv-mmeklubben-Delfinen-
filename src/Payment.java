import java.time.LocalDate;

public class Payment {

    private double amount;
    private LocalDate paymentDate;
    private boolean isPaid;

    public Payment(double amount) {
        this.amount = amount;
        this.isPaid = false;
    }

    public void markAsPaid() {
        this.isPaid = true;
        this.paymentDate = LocalDate.now();
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public String toString() {
        if (isPaid) {
            return "Medlemmet har betalt" + amount + "d." + paymentDate;
        } else {
            return "Medlemmet har endnu ikke indbetalt kontingent. Beløb:" + amount;
        }
    }
}