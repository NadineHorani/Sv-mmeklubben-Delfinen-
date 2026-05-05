import enums.AgeCategory;
import enums.MemberType;
import enums.MembershipStatus;

import java.util.ArrayList;

public class Member {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private int age;
    private int memberId;
    private MembershipStatus status;
    private MemberType memberType;
    private ArrayList<Payment> payments;

    public Member(String name, String address, String email, String phoneNumber, int age, int memberId, MembershipStatus status,
                  MemberType memberType) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.memberId = memberId;
        this.status = status;
        this.memberType = memberType;
        this.payments = new ArrayList<>();

    }

    public AgeCategory getAgeCategory() {
        if (age >= 18) {
            return AgeCategory.SENIOR;
        }
        return AgeCategory.JUNIOR;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public boolean isInDebt() {
        for (Payment payment : payments) {
            if (!payment.isPaid()) {
                return true;
            }
        }
        return false;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public int calculateFee() {
        if (getStatus().equals(MembershipStatus.PASSIVE)) {
            return 500;
        }
        if (age < 18) {
            return 1000;
        } else if (age < 60) {
            return 1600;
        }
        return 1200;     //for medlemmer over 60

    }

    public String toString() {
        return String.format("""
                MemberID: %s
                Name: %s
                
                Adress: %s
                Phone Number: %s
                Email: %s
                
                Age: %d
                Membership Status: %s
                Level: %s""", memberId, name, address, phoneNumber, email, age, status, memberType);
    }


}
