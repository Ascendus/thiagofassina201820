package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.dto.LoginRequest;
import br.gov.mt.seplag.api.dto.LoginResponse;
import br.gov.mt.seplag.api.dto.RefreshRequest;
import br.gov.mt.seplag.api.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza login e retorna tokens JWT")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        String username = auth.getName();
        return ResponseEntity.ok(new LoginResponse(
                jwtUtil.gerarAccessToken(username),
                jwtUtil.gerarRefreshToken(username)
        ));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Renova o access token usando o refresh token")
    public ResponseEntity<LoginResponse> refresh(@RequestBody @Valid RefreshRequest request) {
        String username = jwtUtil.extrairUsername(request.refreshToken());
        if (jwtUtil.isTokenValido(request.refreshToken(), username)) {
            return ResponseEntity.ok(new LoginResponse(
                    jwtUtil.gerarAccessToken(username),
                    null
            ));
        }
        return ResponseEntity.status(401).build();
    }

}