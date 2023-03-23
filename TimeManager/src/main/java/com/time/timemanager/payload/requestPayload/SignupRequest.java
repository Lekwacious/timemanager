package com.time.timemanager.payload.requestPayload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Setter
@Getter
@NoArgsConstructor

public class SignupRequest {
    @NotBlank
    @Size(min = 4, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 50)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}
