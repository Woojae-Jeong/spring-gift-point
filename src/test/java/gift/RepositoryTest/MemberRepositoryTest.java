package gift.RepositoryTest;

import gift.member.entity.Member;
import gift.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void saveTest() {
        Member member = new Member("woo6388@naver.com", "12345678", 5000);
        assertThat(member.getId()).isNull();
        var actual = memberRepository.save(member);
        assertThat(actual.getId()).isNotNull();
    }

    @Test
    void FindByEmailTest(){
        Member member = new Member("woo6388@naver.com", "12345678", 5000);
        memberRepository.save(member);
        Optional<Member> actual = memberRepository.findByEmail(member.getEmail());
        assertThat(actual.get().getEmail().getValue()).isEqualTo("woo6388@naver.com");

    }

    @Test
    void updateTest() {
        Member member1 = memberRepository.save(new Member("woo6388@naver.com", "12345678", 5000));
        Optional<Member> optionalMember = memberRepository.findByEmail(member1.getEmail());
        Member member = optionalMember.get();
        member.update("qoo6388@naver.com", "0000", 4000);

        var actual = memberRepository.findByEmail(member1.getEmail());
        assertAll(
                () -> assertThat(actual.get().getEmail().getValue()).isEqualTo("qoo6388@naver.com"),
                () -> assertThat(actual.get().getPassword().getValue()).isEqualTo("0000"),
                () -> assertThat(actual.get().getPoint().getValue()).isEqualTo(4000)
        );
    }

    @Test
    void deleteTest() {
        Member member1 = memberRepository.save(new Member("woo6388@naver.com", "12345678", 5000));
        Optional<Member> optionalMember = memberRepository.findByEmail(member1.getEmail());
        memberRepository.deleteById(optionalMember.get().getId());
        var actual = memberRepository.findByEmail(member1.getEmail());
        assertThat(actual).isEmpty();
    }
}