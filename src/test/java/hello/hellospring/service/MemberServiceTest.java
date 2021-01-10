package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private MemoryMemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberRepository.clearStore();
        memberService = new MemberService(memberRepository);
    }

    @Test
    public void join() {
        // given
        Member member1 = new Member();
        member1.setName("moonhyuk");

        // when
        Long newId = memberService.join(member1);

        // then
        Member findMember = memberService.findOne(newId).get();
        Assertions.assertThat(member1.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void joinDuplicate() {
        // given
        Member member1 = new Member();
        member1.setName("moonhyuk");

        Member member2 = new Member();
        member2.setName("moonhyuk");

        // when
        memberService.join(member1);

        Exception e = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }
}