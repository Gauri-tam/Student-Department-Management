package com.StudDept.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @NotEmpty(message = "this filed not be empty!")
    @Size(min = 3, max = 20, message = "character should be between min 3 to max 20.")
    private String firstName;

    @NotEmpty(message = "this filed not be empty")
    @Size(min = 3, max = 20, message = "character should be between min 3 to max 20.")
    private String lastName;

    @NotEmpty(message = "this filed not be empty")
    @Email(message = "Invalid Email!")
    private String email;

    @NotEmpty(message = "this filed not be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Invalid Password! ")
    private String password;

    @NotEmpty(message = "this filed not be empty")
    @Pattern(regexp = "^[0-9\\-\\+]{9,15}$", message = "Phone Number Should be 10 Digit! ")
    private String phone;
}
