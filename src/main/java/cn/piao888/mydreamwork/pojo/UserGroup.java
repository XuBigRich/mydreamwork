package cn.piao888.mydreamwork.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class UserGroup {
    private Integer usergroup_id;
    private String name;
    private Integer sort;
    private Date create_time;
    private Integer create_user;
    private Date update_time;
    private Integer update_user;
    private Integer version;
}
