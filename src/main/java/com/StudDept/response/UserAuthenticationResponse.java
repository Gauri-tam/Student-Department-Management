package com.StudDept.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationResponse {

    @JsonProperty("Access Token")
    private String accessToken;

    @JsonProperty("Refresh Token")
    private String refreshToken;
}
