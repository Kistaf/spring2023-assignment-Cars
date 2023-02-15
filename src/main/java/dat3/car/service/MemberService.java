package dat3.car.service;

import dat3.car.dto.Member.MemberRequest;
import dat3.car.dto.Member.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> response = members.stream().map(m -> new MemberResponse(m, includeAll)).toList();
        return response;
    }

    public MemberResponse getMemberByUsername(String username, boolean includeAll) {
        Member member = memberRepository.findById(username).get();
        MemberResponse response = new MemberResponse(member, includeAll);
        return response;
    }

    public MemberResponse addMember(MemberRequest memberRequest) {
        if (memberRepository.existsById(memberRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this ID already exist");
        }
        if (memberRepository.existsByEmail(memberRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this Email already exist");
        }

        Member newMember = MemberRequest.getMemberEntity(memberRequest);
        newMember = memberRepository.save(newMember);

        return new MemberResponse(newMember, false);
    }

    public boolean deleteMemberByUsername(String username) {
        try {
            memberRepository.deleteById(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
