package com.trains.ticketing_trains_boot.service;

import java.util.List;
import com.trains.ticketing_trains_boot.dto.MemberRequest;
import com.trains.ticketing_trains_boot.dto.MemberResponse;

public interface MemberService {

    MemberResponse registerMember(MemberRequest request);

    List<MemberResponse> getAllMembers();

    MemberResponse getMemberById(Integer id);

    MemberResponse getMemberByUsername(String username);
    
    void deleteMember(Integer id);
}
