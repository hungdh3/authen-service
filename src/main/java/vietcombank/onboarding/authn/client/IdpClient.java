package vietcombank.onboarding.authn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "KeycloakClient", url = "${keycloak.auth-service}")
public interface IdpClient {
    @RequestMapping(method = RequestMethod.POST,
            value = "userinfo", consumes = "application/json")
    KeycloakProfile retrieveUserProfile(@RequestHeader("Authorization") String bearerToken);
}

