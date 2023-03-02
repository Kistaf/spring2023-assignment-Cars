package dat3.car.api;

import dat3.car.dto.Member.MemberRequest;
import dat3.car.dto.Member.MemberResponse;
import dat3.car.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/members")
class MemberController {

    private MemberService memberService;

    private MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // ADMIN ONLY
    @GetMapping
    List<MemberResponse> getMembers() {
        return memberService.getMembers(false);
    }

    // ADMIN
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.getMemberByUsername(username, false);
    }


    // ANONYMOUS
    @PostMapping()
    MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }

    // MEMBER
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username) {
        return null;
    }

    // ADMIN
    @PatchMapping("/ranking/{username}/{value}")
    void setRankingForUser(@PathVariable String username, @PathVariable int value) {
    }

    // ADMIN
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {
    }

}

