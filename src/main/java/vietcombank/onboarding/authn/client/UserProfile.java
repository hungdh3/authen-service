package vietcombank.onboarding.authn.client;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfile extends KeycloakProfile {
    private String companyName;
    private String departmentName;
}
