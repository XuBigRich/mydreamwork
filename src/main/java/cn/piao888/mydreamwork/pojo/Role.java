package cn.piao888.mydreamwork.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer role_id;
    private Integer pid;
    private String name;
    private String description;
    private Integer sort;
    private Date create_time;
    private Integer create_user;
    private Date update_time;
    private Integer update_user;
    private Integer version;
}
