package com.phonestoreweb.phonestore.service.implService;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.phonestoreweb.phonestore.dto.request.AuthenticationRequest;
import com.phonestoreweb.phonestore.dto.request.IntrospectRequest;
import com.phonestoreweb.phonestore.dto.request.LogoutRequest;
import com.phonestoreweb.phonestore.dto.request.RefreshTokenRequest;
import com.phonestoreweb.phonestore.dto.response.AuthenticationResponse;
import com.phonestoreweb.phonestore.dto.response.IntrospectResponse;
import com.phonestoreweb.phonestore.exception.AppException;
import com.phonestoreweb.phonestore.exception.ErrorCode;
import com.phonestoreweb.phonestore.models.InvalidatedToken;
import com.phonestoreweb.phonestore.models.User;
import com.phonestoreweb.phonestore.repositories.InvalidatedTokenRepository;
import com.phonestoreweb.phonestore.repositories.UserRepository;
import com.phonestoreweb.phonestore.service.IAuthenticationService;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY ;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESH_DURATION;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(),user.getPassword());

        if(!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);
        String token = generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public String generateToken(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512); // header

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("daile_dev")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",user.getRole())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
       String token = request.getToken();
        boolean ísValid = true;
       try {
           verifyToken(token,false);
       }catch (AppException e){
           ísValid = false;
       }

       return IntrospectResponse.builder()
               .valid(ísValid)
               .build();

    }

    @Override
    public void logout(LogoutRequest request) throws ParseException {
        try {
            var signToken = verifyToken(request.getToken(),true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedTokenRepository.save(invalidatedToken);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException {
        var signToken = verifyToken(request.getToken(),true);

        var jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
        String username = signToken.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        String token = generateToken(username);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    private SignedJWT verifyToken(String token,boolean isRefresh){
        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);

            Date expiryTime = (isRefresh)
                    ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                        .toInstant().plus(REFRESH_DURATION,ChronoUnit.SECONDS).toEpochMilli())
                    : signedJWT.getJWTClaimsSet().getExpirationTime();

            var verified = signedJWT.verify(verifier);

            if(!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

            if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }

            return signedJWT;
        } catch (JOSEException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
