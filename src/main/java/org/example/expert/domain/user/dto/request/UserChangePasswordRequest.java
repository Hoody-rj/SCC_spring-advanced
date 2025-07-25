package org.example.expert.domain.user.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordRequest {

    @NotBlank
    private String oldPassword;

    @Size(min =8, message = "새 비밀번호는 8자 이상이어야 합니다")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])[a-zA-Z\\d]{2,}$", message = "영어 알파벳과 숫자를 동시에 입력할 수 있습니다.")
    @NotBlank
    private String newPassword;
}
