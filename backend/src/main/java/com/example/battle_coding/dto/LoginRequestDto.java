package com.example.battle_coding.dto;

import com.example.battle_coding.entity.LoginProvider;

public record LoginRequestDto(
        String email,
        String password,
        LoginProvider provider,
        String providerId
){}
