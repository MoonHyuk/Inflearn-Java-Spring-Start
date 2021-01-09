package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("moonhyuk");

        // when
        Member newMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(newMember).isEqualTo(findMember);
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("moonhyuk");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("moonlight");
        memberRepository.save(member2);

        // when
        Member findMember = memberRepository.findByName("moonhyuk").get();

        // then
        assertThat(findMember).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("moonhyuk");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("moonlight");
        memberRepository.save(member2);

        // when
        List<Member> findMembers = memberRepository.findAll();

        // then
        assertThat(findMembers.size()).isEqualTo(2);
        assertThat(findMembers.get(0)).isEqualTo(member1);
        assertThat(findMembers.get(1)).isEqualTo(member2);
    }
}