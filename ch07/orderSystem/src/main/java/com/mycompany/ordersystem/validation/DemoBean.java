package com.mycompany.ordersystem.validation;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public class DemoBean {

    @Digits(integer = 2, fraction = 0, message = "나이는 두 자리 이상의 숫자를 갖지 못합니다.")
    @Min(value = 18, message = "최소 연령은 18세 입니다.")
    @Max(value = 50, message = "최대 연량은 50세 입니다.")
    private int age;

    @NotNull(message = "이름을 입력해 주세요.")
    private String name;

    @DecimalMax(value = "99999.999", message = "최대 99999.999 이상은 될 수 없습니다.")
    @DecimalMin(value = "1.00", message = "최소 1.00 보다 작을 수 없습니다.")
    private float amount = 0f;

    @NotNull
    @Pattern(regexp = "^\\d{6}", message = "우편번호는 6자리 이어야 합니다.")
    private String zipCode;

    // Hibernate 검증
    @CreditCardNumber(message = "유효한 신용카드 번호가 아닙니다.")
    private String creditCardNumber;

    @Email(message = "이메일을 잘못 입력했습니다.")
    private String email;

    @Length(max = 50, min = 10, message = "메시지는 10문자 이상, 50 문자까지 입력할 수 있습니다.")
    private String description;

    @NotBlank(message = "문자열에만 적용...")
    private String id;

    @NotEmpty(message = "제폼 목록이 있어야 합니다.")
    private List<String> productList;

    @URL(message = "유효한 웹사이트 주소가 아닙니다.")
    private String url;

    // 커스텀 어노테이션 검증...
    @Password
    private String password;

}
