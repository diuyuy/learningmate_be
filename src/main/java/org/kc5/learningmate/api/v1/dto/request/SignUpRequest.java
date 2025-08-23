package org.kc5.learningmate.api.v1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SignUpRequest(@NotNull @Email String email, @Size(min = 8) String password) {
}
