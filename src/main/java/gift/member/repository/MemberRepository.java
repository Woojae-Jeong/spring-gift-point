package gift.member.repository;

import gift.member.entity.Member;
import gift.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
}