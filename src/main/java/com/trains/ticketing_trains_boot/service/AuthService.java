package com.trains.ticketing_trains_boot.service;

import com.trains.ticketing_trains_boot.dto.ChangePasswordRequest;
import com.trains.ticketing_trains_boot.dto.LoginRequest;
import com.trains.ticketing_trains_boot.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void changePassword(ChangePasswordRequest request);
}
