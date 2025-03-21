package com.example.CTDT_APP.Service;

import com.example.CTDT_APP.DTO.Request.AuthenticationRequest;
import com.example.CTDT_APP.DTO.Request.IntrospectRequest;
import com.example.CTDT_APP.DTO.Respone.AuthenticationRespone;
import com.example.CTDT_APP.DTO.Respone.IntrospectRespone;
import com.example.CTDT_APP.Exception.AppException;
import com.example.CTDT_APP.Exception.ErrorCode;
import com.example.CTDT_APP.Repository.TaiKhoanRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    TaiKhoanRepository taiKhoanRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;

    public AuthenticationRespone authenticate(AuthenticationRequest request){
        var taikhoan = taiKhoanRepository.findByTenDangNhap(request.getTenDangNhap())
                .orElseThrow(()-> new AppException(ErrorCode.TAIKHOAN_NOT_EXSITED));
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
        Boolean authenticated= passwordEncoder.matches(request.getMatKhau()
                ,taikhoan.getMatKhau());
        if(!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);
        var token= generateToken(request.getTenDangNhap());
        return AuthenticationRespone.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    String generateToken(String tenDangNhap){
        JWSHeader header= new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
                .subject(tenDangNhap)
                .issuer("App_Dev")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .build();

        Payload payload= new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject= new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            Logger log = null;
            log.error("Khong the tao token");
            throw new RuntimeException(e);
        }
    }

    public IntrospectRespone introspectRespone(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier jwsVerifier= new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT= SignedJWT.parse(token);
        Date expityTime =signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified= signedJWT.verify(jwsVerifier);
        return IntrospectRespone.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();
    }

}
