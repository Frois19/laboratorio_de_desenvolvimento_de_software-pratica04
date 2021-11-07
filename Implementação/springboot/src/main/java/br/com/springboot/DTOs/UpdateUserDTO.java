package br.com.springboot.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
    private String login;
    private String password;
}
