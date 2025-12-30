package com.mycompany.ordersystem.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveMember(Member member) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Member> entity = memberRepository.findByName(member.getName());
        if (entity.isEmpty()) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            // 이건 사용자를 등록하는 과정인데...이게 무슨 말도 안되는 역할 부여냐...
            if (authentication.getAuthorities().stream().anyMatch(
                    auth -> auth.getAuthority().equals("ROLE_ADMIN")
            ) || member.getName().equals("admin")) {
                member.setAuthority("ADMIN");
            } else {
                member.setAuthority("USER");
            }
            memberRepository.save(member);
        }
    }
}
