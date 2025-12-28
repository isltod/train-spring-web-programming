package com.mycompany.ordersystem.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Customer {

    private long id;
    // @Size(min = 2, max = 10, message = "이름은 2자 이상 10자까지 입니다.")
    // @Pattern(regexp = "^[A-Za-z0-9가-힣]+$", message = "공백없이 숫자와 문자만 입력하세요.")
    // 국제화
    @Size(min = 2, max = 10, message = "{validate.customer.name}")
    @Pattern(regexp = "^[A-Za-z0-9가-힣]+$", message = "{validate.customer.name.pattern}")
    private String name;
    // @Size(max = 60, message = "주소는 60자까지 입력할 수 있습니다.")
    @Size(max = 60, message = "{validate.customer.address}")
    private String address;
    // @Pattern(
    //         regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}",
    //         message = "정확한 이메일 주소를 입력하세요."
    // )
    // @NotBlank(message = "이메일을 입력해주세요.")
    // @Email(message = "정확한 이메일 주소를 입력해주세요.")
    @NotBlank(message = "{validate.customer.email}")
    @Email(message = "{validate.customer.email.correct}")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
