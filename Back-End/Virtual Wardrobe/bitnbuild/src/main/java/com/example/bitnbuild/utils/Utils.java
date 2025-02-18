package com.example.bitnbuild.utils;



import com.example.bitnbuild.dto.UserDto;
import com.example.bitnbuild.entity.User;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final String ALPHANUMERIC_STRING="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final SecureRandom secureRandom=new SecureRandom();


    public static String generateRandomConfirmationCode(int length)
    {
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;i++)
        {
            int randomindex=secureRandom.nextInt();
            char randomChar=ALPHANUMERIC_STRING.charAt(randomindex);
            stringBuilder.append(randomChar);

        }
        return stringBuilder.toString();
    }

    public static UserDto mapUserEntityToUserDto(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhonenumber(user.getPhonenumber());
        userDto.setRole(user.getRole());

        return userDto;
    }



    public static List<UserDto>mapUserListEntityToUserListDTO(List<User> userList)
    {
        return userList.stream().map(Utils::mapUserEntityToUserDto).collect(Collectors.toList());
    }








}
