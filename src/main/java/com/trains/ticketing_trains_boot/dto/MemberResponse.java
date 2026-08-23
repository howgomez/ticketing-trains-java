package com.trains.ticketing_trains_boot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Integer memberId;
    private String username;
    private String realName;
    private String gender;
    private Integer age;
    private String idCard;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
