package com.ayush.foodiez.dto;

import com.ayush.foodiez.Custom.annotations.Adult;
import com.ayush.foodiez.Custom.annotations.PasswordMatchers;
import com.ayush.foodiez.model.Address;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatchers
public class UserSignUpDto {
    @Email(regexp =  "^(?:(?:(?:[^<>()[\\\\]\\\\.,;:\\s@\"]+(?:\\.[^<>()[\\\\]\\\\.,;:\\s@\"]+)*)|(?:\".+\"))@(?:(?:\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(?:(?:[a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,})))$"
            ,message = "Format: abcxyz123@provider_name.com")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotNull(message = "Must Not be Empty")
    @Range(min=1111111111,max = 9999999999L,message = "and must contain only have 10 digits")
    private Long phoneNumber;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @Adult
    private int age;

    @NotBlank
    @Size(min = 8,max = 30,message = "Min 8 characters,Max 30 Characters")
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one digit")
    private String password;

    @NotBlank
    @Size(min = 8,max = 30,message = "Min 8 characters,Max 30 Characters")
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one digit")
    private String rePass;
    private Address address;

}
