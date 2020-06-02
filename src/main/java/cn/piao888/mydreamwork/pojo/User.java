package cn.piao888.mydreamwork.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer user_id;
    private String avatar;
    private String account;
    private String password;
    private String salt;
    private String name;
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private Integer status;
    private Date create_time;
    private Integer create_user;
    private Date update_time;
    private Integer update_user;
    private Integer version;
}
