package com.trains.ticketing_trains_boot.dto;

import com.trains.ticketing_trains_boot.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Integer memberId;
    private String username;
    private String realName;
    private Role role;
    private String message;
}
