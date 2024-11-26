package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.dto.request.AuthenticationRequest;
import com.phonestoreweb.phonestore.dto.request.IntrospectRequest;
import com.phonestoreweb.phonestore.dto.request.LogoutRequest;
import com.phonestoreweb.phonestore.dto.request.RefreshTokenRequest;
import com.phonestoreweb.phonestore.dto.response.AuthenticationResponse;
import com.phonestoreweb.phonestore.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(String username);

    IntrospectResponse introspect(IntrospectRequest request);

    void logout(LogoutRequest request) throws ParseException;

    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException;
}
