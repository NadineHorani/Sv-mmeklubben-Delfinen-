package model;

import java.time.LocalDate;

public class Payment {

    private LocalDate paymentDate;
    private boolean isPaid;
    private Member member;

    public Payment(Member member) {
        this.member = member;
        this.isPaid = false;
    }


    public Member getMember(){
        return member;
    }

    public void markAsPaid() {
        this.isPaid = true;
        this.paymentDate = LocalDate.now();
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
            return "Medlemmet har betalt" + member.calculateFee() + "d." + paymentDate;
        } else {
            return "Medlemmet har endnu ikke indbetalt kontingent. Beløb:" + member.calculateFee();
        }
    }
}