package vietcombank.onboarding.authn.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class UserProfileController {

    @Operation(summary = "User Profile", description = "")
    @GetMapping("/{name}")
    public String sayHello(@RequestHeader HttpHeaders headers,
                           @PathVariable String name) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String jwtToken = token.replaceFirst("Bearer", "").trim();
        log.info("token : " + jwtToken);
        log.info("input : " + name);
        return "Hello " + name;
    }
}
