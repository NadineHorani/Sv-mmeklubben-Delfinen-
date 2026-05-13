package model;

import model.enums.MemberType;
import model.enums.MembershipStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    private Member createMember(int age, MembershipStatus status) {
        return new Member(
                "Test Navn",
                "Test Vej 1",
                "test@mail.dk",
                "12345678",
                age,
                1,
                status,
                MemberType.MOTIONIST
        );
    }

    @Test
    void passiveMemberShouldPay500() {
        Member member = createMember(25, MembershipStatus.PASSIVE);

        assertEquals(500, member.calculateFee());
    }

    @Test
    void activeJuniorShouldPay1000() {
        Member member = createMember(17, MembershipStatus.ACTIVE);

        assertEquals(1000, member.calculateFee());
    }

    @Test
    void activeSeniorShouldPay1600() {
        Member member = createMember(18, MembershipStatus.ACTIVE);

        assertEquals(1600, member.calculateFee());
    }

    @Test
    void memberOver60ShouldPay1200() {
        Member member = createMember(61, MembershipStatus.ACTIVE);

        assertEquals(1200, member.calculateFee());
    }

    @Test
    void memberAt60ShouldPay1600() {
        Member member = createMember(60, MembershipStatus.ACTIVE);

        assertEquals(1600, member.calculateFee());
    }
}