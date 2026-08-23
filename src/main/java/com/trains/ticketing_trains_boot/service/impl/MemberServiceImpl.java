package com.trains.ticketing_trains_boot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trains.ticketing_trains_boot.dto.MemberRequest;
import com.trains.ticketing_trains_boot.dto.MemberResponse;
import com.trains.ticketing_trains_boot.entity.Member;
import com.trains.ticketing_trains_boot.repository.MemberRepository;
import com.trains.ticketing_trains_boot.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberResponse registerMember(MemberRequest request) {
        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists: " + request.getUsername());
        }

        Member member = Member.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .realName(request.getRealName())
                .gender(request.getGender())
                .age(request.getAge())
                .idCard(request.getIdCard())
                .role(request.getRole() != null ? request.getRole() : com.trains.ticketing_trains_boot.entity.Role.ROLE_MEMBER)
                .build();

        Member saved = memberRepository.save(member);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponse getMemberById(Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        return mapToResponse(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponse getMemberByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found with username: " + username));
        return mapToResponse(member);
    }

    @Override
    @Transactional
    public void deleteMember(Integer id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    private MemberResponse mapToResponse(Member member) {
        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .username(member.getUsername())
                .realName(member.getRealName())
                .gender(member.getGender())
                .age(member.getAge())
                .idCard(member.getIdCard())
                .isActive(member.getIsActive())
                .role(member.getRole())
                .createdAt(member.getCreatedAt())
                .build();

    }
}
