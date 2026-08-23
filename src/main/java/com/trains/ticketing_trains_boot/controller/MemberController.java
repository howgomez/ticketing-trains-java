package com.trains.ticketing_trains_boot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trains.ticketing_trains_boot.dto.MemberRequest;
import com.trains.ticketing_trains_boot.dto.MemberResponse;
import com.trains.ticketing_trains_boot.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> registerMember(@RequestBody MemberRequest request) {
        MemberResponse registered = memberService.registerMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registered);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Integer id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<MemberResponse> getMemberByUsername(@PathVariable String username) {
        return ResponseEntity.ok(memberService.getMemberByUsername(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
