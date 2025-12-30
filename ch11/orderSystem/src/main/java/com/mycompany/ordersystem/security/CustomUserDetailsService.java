package com.mycompany.ordersystem.security;

import com.mycompany.ordersystem.member.Member;
import com.mycompany.ordersystem.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> entity = memberRepository.findByName(username);
        Member member = entity.orElseThrow(
                () -> new UsernameNotFoundException("사용자가 등록되지 않았습니다: " + username)
        );
        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getAuthority())
                .build();
    }
}
