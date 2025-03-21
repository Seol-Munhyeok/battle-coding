package com.example.battle_coding.dto;

import com.example.battle_coding.entity.LoginProvider;

public record SignupRequestDto(
        String email,
        String password,
        String nickname,
        LoginProvider provider,
        String providerId
) {}
