package com.example.bitnbuild.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //this ensures that only non-null fields are included in the JSON output.
public class Response {

    private int statuscode;
    private String message;
    private String token;
    private String role;
    private String expirationTime;
    private String bookingconfirmationcode;

    private UserDto user;


    private List<UserDto> userlist;





}
