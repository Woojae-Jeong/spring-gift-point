package gift.member.service;

import gift.member.exception.EmailAlreadyExistsException;
import gift.member.exception.InvalidCredentialException;
import gift.member.exception.MemberNotFoundException;
import gift.member.entity.Member;
import gift.member.dto.RequestMemberDto;
import gift.vo.Email;
import gift.member.repository.MemberRepository;
import gift.util.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil){
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public String signUpUser(RequestMemberDto requestMemberDTO){
        Optional<Member> optionalMember = memberRepository.findByEmail(new Email(requestMemberDTO.email()));
        if(optionalMember.isPresent())
            throw new EmailAlreadyExistsException();
        Member member =  memberRepository.save(new Member(requestMemberDTO.email(), requestMemberDTO.password(), 0));
        return jwtUtil.generateToken(member);
    }

    @Transactional(readOnly = true)
    public String loginUser(RequestMemberDto requestMemberDTO) {
        Member member = memberRepository.findByEmail(new Email(requestMemberDTO.email())).orElseThrow(() -> new InvalidCredentialException());
        String temp = member.getPassword().getValue();
        if (!(temp.equals(requestMemberDTO.password())))
            throw new InvalidCredentialException();

        return jwtUtil.generateToken(member);
    }

    @Transactional(readOnly = true)
    public Member getUserByToken(String token) {
        return memberRepository.findByEmail(new Email(jwtUtil.getSubject(token))).orElseThrow(()-> new MemberNotFoundException());
    }

    @Transactional
    public void subtractPoint(Member member, int totalPrice) {
        member.subtractPoint(totalPrice);
    }

    @Transactional
    public void refundPoints(Member member, int point) {
        member.addPoint(point);
    }

    @Transactional
    public void addPoints(Long memberId, int point) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException());
        member.addPoint(point);
    }
    @Transactional(readOnly = true)
    public int getPoints(Member member) {
        return member.getPoint().getValue();
    }

    @Transactional(readOnly = true)
    public Page<Member> getAllMembers(Pageable pageable) {
        Page<Member> memberPage= memberRepository.findAll(pageable);
        return memberPage;
    }

    @Transactional(readOnly = true)
    public Member selectMember(Long id) {
        return memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException());
    }

    @Transactional
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
