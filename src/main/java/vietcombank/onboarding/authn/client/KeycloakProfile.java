package vietcombank.onboarding.authn.client;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class KeycloakProfile {
    private String sub;
    private Boolean email_verified;
    private String physicalDeliveryOfficeName;
    private String mobile;
    private String description;
    private String cn;
    private String preferred_username;
    private String given_name;
    private String name;
    private String company;
    private String department;
    private String family_name;
    private String email;
}
