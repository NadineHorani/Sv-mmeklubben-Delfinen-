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

    public Member getMember() {
        return member;
    }

    public void markAsPaid() {
        isPaid = true;
        paymentDate = LocalDate.now();
    }

    public boolean isPaid() {
        return isPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {

        if (isPaid) {
            return member.getName() +
                    " har betalt " +
                    member.calculateFee() +
                    " kr. d. " + paymentDate;
        }

        return member.getName() +
                " mangler at betale " +
                member.calculateFee() +
                " kr.";
    }
}