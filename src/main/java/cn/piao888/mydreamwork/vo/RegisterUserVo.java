package cn.piao888.mydreamwork.vo;

import lombok.Data;

import java.util.Date;
@Data
public class RegisterUserVo {
    private String account;
    private String password;
    private String name;
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
}
