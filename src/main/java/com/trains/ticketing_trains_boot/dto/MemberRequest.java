package com.trains.ticketing_trains_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequest {
    private String username;
    private String password;
    private String realName;
    private String gender;
    private Integer age;
    private String idCard;
}
