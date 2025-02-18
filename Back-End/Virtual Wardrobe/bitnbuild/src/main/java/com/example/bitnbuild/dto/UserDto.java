package com.example.bitnbuild.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private long id;
    private String email;
    private String name;
    private String phonenumber;
    private String role;


}
