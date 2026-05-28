package br.gov.mt.seplag.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarHashBCrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin123"));
    }
}
