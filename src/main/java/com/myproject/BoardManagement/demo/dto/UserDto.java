package com.myproject.BoardManagement.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zied.bagga
 * @since 04/06/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
}
