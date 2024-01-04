package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    // 싱글톤이다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);
        //when
        Member saveMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(saveMember.getId());

        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findById() {
        //given
        Member member1 = new Member("HJ", 30);
        //when
        Member savemember = memberRepository.save(member1);
        //then
        Member byIdMember = memberRepository.findById(savemember.getId());

        assertThat(byIdMember).isEqualTo(member1);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("HJ", 30);
        Member member2 = new Member("AJ", 25);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

}