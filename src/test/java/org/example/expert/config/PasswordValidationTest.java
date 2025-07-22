package org.example.expert.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidationTest {
    @Test
    @DisplayName("Password Pattern and Range Checking")
    void pwdCheckTest(){
//        UserChangePasswordRequest userChangePasswordRequest = new UserChangePasswordRequest("asdas","asdasd");
//        assertThat(userChangePasswordRequest.getNewPassword()).isEqualTo("asdas");
        UserChangePasswordRequest request = new UserChangePasswordRequest("oldPwd", "abcasdasdasd");


        System.out.println("getNewPassword = " + request.getNewPassword());
        System.out.println("getOldPassword = " + request.getOldPassword());

        // When — 유효성 검사 실행
        Set<ConstraintViolation<UserChangePasswordRequest>> violations = validator.validate(request);

//         Then — 유효성 실패 확인
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v ->
                v.getPropertyPath().toString().equals("newPassword") &&
                        (v.getMessage().contains("8자") || v.getMessage().contains("알파벳과 숫자"))
        );
    }
}
