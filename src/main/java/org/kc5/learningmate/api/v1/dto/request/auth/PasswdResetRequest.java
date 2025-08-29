package org.kc5.learningmate.api.v1.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PasswdResetRequest(@NotEmpty @Email String email,
                                 @NotEmpty @Size(min = 8, max = 128) String password,
                                 @NotEmpty String authToken) {
}
