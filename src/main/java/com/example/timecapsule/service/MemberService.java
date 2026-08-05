package com.example.timecapsule.service;

import com.example.timecapsule.config.MemberDetails;
import com.example.timecapsule.domain.Member;
import com.example.timecapsule.dto.SignupForm;
import com.example.timecapsule.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다: " + username);
        }
        return new MemberDetails(member);
    }

    @Transactional
    public void signup(SignupForm form) {
        if (memberMapper.countByUsername(form.getUsername()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        Member member = Member.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .build();

        memberMapper.insert(member);
    }

}

