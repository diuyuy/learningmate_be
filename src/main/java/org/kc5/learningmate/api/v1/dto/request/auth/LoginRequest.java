package org.kc5.learningmate.api.v1.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record LoginRequest(@NotNull @Email String email, @NotNull String password) {
}
