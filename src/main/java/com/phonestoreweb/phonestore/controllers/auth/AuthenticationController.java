package com.phonestoreweb.phonestore.controllers.auth;

import com.phonestoreweb.phonestore.dto.request.*;
import com.phonestoreweb.phonestore.dto.response.AuthenticationResponse;
import com.phonestoreweb.phonestore.dto.response.IntrospectResponse;
import com.phonestoreweb.phonestore.service.IAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        AuthenticationResponse result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();

    }

    @PostMapping(value = "/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request){
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();

    }
    @PostMapping(value = "/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();

    }
    @PostMapping(value = "/refreshToken")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) throws ParseException {
        AuthenticationResponse result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();

    }

}
