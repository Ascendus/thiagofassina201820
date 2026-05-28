package br.gov.mt.seplag.api.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {}