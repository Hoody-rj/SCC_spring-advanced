package org.example.expert.config;



import jakarta.validation.*;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.annotation.Validated;

//import javax.xml.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
    }

    @Test
    @Validated
    @DisplayName("Password Pattern and Range Checking")
    void pwdCheckTest(){
//        UserChangePasswordRequest userChangePasswordRequest = new UserChangePasswordRequest("asdas","asdasd");
//        assertThat(userChangePasswordRequest.getNewPassword()).isEqualTo("asdas");
        UserChangePasswordRequest request2 = UserChangePasswordRequest.builder()
                .newPassword("ne123")
                .oldPassword("oldPwd")
                .build();

        System.out.println("getNewPassword = " + request2.getNewPassword());
        System.out.println("getOldPassword = " + request2.getOldPassword());

        // When — 유효성 검사 실행
        Set<ConstraintViolation<UserChangePasswordRequest>> violations = validator.validate(request2);
        System.out.println("violations = " + violations);
//         Then — 유효성 실패 확인
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v ->
                v.getPropertyPath().toString().equals("newPassword") &&
                        (v.getMessage().contains("8자") || v.getMessage().contains("알파벳과 숫자"))
        );
    }
}
