package com.brs.library.dto;

import lombok.*;

import javax.validation.constraints.*;

@ToString
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

    @NotBlank(message = "form.invalid.username.blank")
    @Size(min=5, max = 13, message ="form.invalid.username.length" )
    private String username;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$",
            message = "form.invalid.email")
    private String email;

    @Size(min=5, max = 13, message = "form.invalid.phone.number.length")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "form.invalid.phone.number.digits")
    private String phoneNumber;

    @Pattern(regexp = "^((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})$", message = "form.invalid.password")
    private String password;
}
