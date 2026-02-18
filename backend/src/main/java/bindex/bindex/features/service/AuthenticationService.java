package bindex.bindex.features.service;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;
import bindex.bindex.features.authentication.controller.repository.AuthenticationUserRepository;
import bindex.bindex.features.authentication.controller.model.AuthenticationUser;
import bindex.bindex.features.authentication.dto.AuthenticationResponseBody;
import bindex.bindex.features.authentication.dto.AuthenticationRequestBody;
// import bindex.bindex.features.authentication.utils.EmailService;
import bindex.bindex.features.authentication.utils.Encoder;
import bindex.bindex.features.authentication.utils.JsonWebToken;
import jakarta.mail.MessagingException;



@Service
public class AuthenticationService {
    private final JsonWebToken jsonWebToken;
    private final Encoder encoder;
    private final AuthenticationUserRepository authenticationUserRepository;
    // private final EmailService emailService;
   

    public AuthenticationService(JsonWebToken jsonWebToken, Encoder encoder, AuthenticationUserRepository authenticationUserRepository){
        this.jsonWebToken = jsonWebToken;
        this.encoder = encoder;
        this.authenticationUserRepository = authenticationUserRepository;
        // this.emailService = emailService;
        
    }

    // public static String generateEmailVerificationToken(){
    //     SecureRandom random = new SecureRandom();
    //     StringBuilder token = new StringBuilder(5);
    //     for (int i=0;i<5;i++){
    //         token.append(random.nextInt(10));
    //     }
    //     return token.toString();
    // }

    

    public AuthenticationUser getUser (String email){
        return authenticationUserRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("User not found"));

    }
    
    public AuthenticationResponseBody register(AuthenticationRequestBody registerRequestBody){
        authenticationUserRepository.save(new AuthenticationUser(registerRequestBody.getEmail(), encoder.encode(registerRequestBody.getPassword())));
        String token = jsonWebToken.generateToken(registerRequestBody.getEmail());
        // emailService.sendEmail(registerRequestBody.getEmail(), "Some Subject", "Some Body");
        return new AuthenticationResponseBody(token, "User registered successfully");
    }

    public AuthenticationResponseBody login(AuthenticationRequestBody loginRequestBody){
        AuthenticationUser user = authenticationUserRepository.findByEmail(loginRequestBody.getEmail()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!encoder.matches(loginRequestBody.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Password is incorrect");
        }

        String token = jsonWebToken.generateToken(loginRequestBody.getEmail());
        return new AuthenticationResponseBody(token, "Authentication succeeded");
    }
}
