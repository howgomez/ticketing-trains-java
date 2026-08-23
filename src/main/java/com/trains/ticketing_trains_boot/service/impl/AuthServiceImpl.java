package com.trains.ticketing_trains_boot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trains.ticketing_trains_boot.dto.ChangePasswordRequest;
import com.trains.ticketing_trains_boot.dto.LoginRequest;
import com.trains.ticketing_trains_boot.dto.LoginResponse;
import com.trains.ticketing_trains_boot.entity.Member;
import com.trains.ticketing_trains_boot.repository.MemberRepository;
import com.trains.ticketing_trains_boot.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Verificar contraseña
        if (!member.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Verificar estado activo
        if (Boolean.FALSE.equals(member.getIsActive())) {
            throw new RuntimeException("User account is inactive. Please contact support.");
        }

        return LoginResponse.builder()
                .memberId(member.getMemberId())
                .username(member.getUsername())
                .realName(member.getRealName())
                .role(member.getRole())
                .message("Login successful")
                .build();
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + request.getMemberId()));

        if (!member.getPassword().equals(request.getOldPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        member.setPassword(request.getNewPassword());
        memberRepository.save(member);
    }
}
