package org.kc5.learningmate.api.v1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SignUpRequest(@NotNull @Email String email, @Min(8) String password) {
}
