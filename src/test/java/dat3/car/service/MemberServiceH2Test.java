package dat3.car.service;

import dat3.car.dto.Member.MemberRequest;
import dat3.car.dto.Member.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Transactional (rollback)
// Includes what is necessary for JPA/Hibernate and ONLY that
// Uses an in-memory database (H2)
@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    public MemberRepository memberRepository;

    MemberService memberService;

    boolean dataIsReady = false;

    @BeforeEach
    void setUp() {
        if (!dataIsReady) {
            memberRepository.save(new Member("m1", "test12", "m1@a.dk", "bb", "Olsen", "test vej 34", "Lyngby", "2800"));
            memberRepository.save(new Member("m2", "test12", "m2@a.dk", "aa", "hansen", "test vej 34", "Lyngby", "2800"));
            dataIsReady = true;
            memberService = new MemberService(memberRepository); //Real DB is mocked away with H2
        }
    }


    @Test
    void getMembers() {
        List<MemberResponse> members = memberService.getMembers(true);
        assertEquals(2, members.size());
    }

    @Test
    void addMember() {
        Member m = new Member("m3", "test12", "m3@a.dk", "dd", "Olsen", "test vej 34", "Lyngby", "2800");
        MemberRequest request = new MemberRequest(m);
        request.setFirstName(m.getFirstName());
        request.setLastName(m.getLastName());
        MemberResponse response = memberService.addMember(request);
        assertEquals("m3@a.dk", response.getEmail());
    }
}