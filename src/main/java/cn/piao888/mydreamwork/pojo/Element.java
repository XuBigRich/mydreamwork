package cn.piao888.mydreamwork.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Element {
    private Integer element_id;
    private String code;
    private String name;
    private String icon;
    private String description;
    private String status;
    private String open_flag;
    private Date create_time;
    private Date update_time;
    private Integer create_user;
    private Integer update_user;
}
