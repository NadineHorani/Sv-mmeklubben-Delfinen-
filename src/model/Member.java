package model;

import model.enums.AgeCategory;
import model.enums.MemberType;
import model.enums.MembershipStatus;

import java.util.ArrayList;
import java.util.Random;

public class Member {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private int age;
    private int memberId;
    private static int counter = 0;
    private MembershipStatus status;
    private MemberType memberType;

    public Member(String name, String address, String email, String phoneNumber, int age, MemberType memberType) {
        counter++;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.memberId = counter;
        this.status = MembershipStatus.ACTIVE;
        this.memberType = memberType;
    }

    public int getMemberId(){
        return memberId;
    }

    public String getName(){
        return name;
    }

    public AgeCategory getAgeCategory() {
        if (age >= 18) {
            return AgeCategory.SENIOR;
        }
        return AgeCategory.JUNIOR;
    }


    public MemberType getMemberType() {
        return memberType;
    }

    public int getAge() {
        return age;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void changeStatus(){
        if (this.getStatus() == MembershipStatus.PASSIVE){
            status = MembershipStatus.ACTIVE;
        } else {
            status = MembershipStatus.PASSIVE;
        }
    }

    public MembershipStatus getOppositeStatus(){
        if (this.getStatus() == MembershipStatus.PASSIVE){
            return MembershipStatus.ACTIVE;
        }
        return MembershipStatus.PASSIVE;
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
