package vietcombank.onboarding.authn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vietcombank.onboarding.authn.client.IdpClient;
import vietcombank.onboarding.authn.client.KeycloakProfile;
import vietcombank.onboarding.authn.client.UserProfile;
import vietcombank.onboarding.authn.model.UnathorizedResponse;

@Slf4j
@RestController
@RequestMapping("user-profile/v1")
public class UserProfileController {

    @Autowired
    private IdpClient idpClient;

    @Operation(summary = "User Profile", description = "")
    @PostMapping("/")
    public ResponseEntity<Object> sayHello(@RequestHeader HttpHeaders headers) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
            log.info("token: " + token);
            KeycloakProfile keycloakProfile = idpClient.retrieveUserProfile(token);
            log.info("keycloakProfile : " + objectMapper.writeValueAsString(keycloakProfile));
            UserProfile userProfile = new UserProfile();
            userProfile.setCompanyName("Hoi So");
            userProfile.setDepartmentName("TT CNTT");
            userProfile.setDepartment(keycloakProfile.getDepartment());
            userProfile.setCompany(keycloakProfile.getCompany());
            userProfile.setCn(keycloakProfile.getCn());
            userProfile.setDescription(keycloakProfile.getDescription());
            userProfile.setEmail(keycloakProfile.getEmail());
            userProfile.setEmail_verified(keycloakProfile.getEmail_verified());
            userProfile.setFamily_name(keycloakProfile.getFamily_name());
            userProfile.setGiven_name(keycloakProfile.getGiven_name());
            userProfile.setMobile(keycloakProfile.getMobile());
            userProfile.setName(keycloakProfile.getName());
            userProfile.setPhysicalDeliveryOfficeName(keycloakProfile.getPhysicalDeliveryOfficeName());
            userProfile.setPreferred_username(keycloakProfile.getPreferred_username());
            userProfile.setSub(keycloakProfile.getSub());
            log.info("userProfile : " + objectMapper.writeValueAsString(userProfile));
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        UnathorizedResponse unathorizedResponse = new UnathorizedResponse();
        unathorizedResponse.setError("invalid_token");
        unathorizedResponse.setError_description("Token verification failed");
        return new ResponseEntity<>(unathorizedResponse, HttpStatus.UNAUTHORIZED);
    }
}
