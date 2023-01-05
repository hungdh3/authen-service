package vietcombank.onboarding.authn.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vietcombank.onboarding.authn.client.IdpClient;
import vietcombank.onboarding.authn.client.KeycloakProfile;
import vietcombank.onboarding.authn.model.UnathorizedResponse;

@Slf4j
@RestController
@RequestMapping("user-profile/v1")
public class UserProfileController {

    @Autowired
    private IdpClient idpClient;

    @Operation(summary = "User Profile", description = "")
    @GetMapping("/")
    public ResponseEntity<Object> sayHello(@RequestHeader HttpHeaders headers) {
        try {
            String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
            log.info("token: " + token);
            KeycloakProfile keycloakProfile = idpClient.retrieveUserProfile(token);
            log.info("keycloakProfile : " + keycloakProfile);
            return new ResponseEntity<>(keycloakProfile, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        UnathorizedResponse unathorizedResponse = new UnathorizedResponse();
        unathorizedResponse.setError("invalid_token");
        unathorizedResponse.setError_description("Token verification failed");
        return new ResponseEntity<>(unathorizedResponse, HttpStatus.UNAUTHORIZED);
    }
}
