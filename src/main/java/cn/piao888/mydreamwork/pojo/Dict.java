package cn.piao888.mydreamwork.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Dict {
    private Integer dict_id;
    private Integer dict_type_id;
    private String code;
    private String name;
    private Integer parent_id;
    private String parent_ids;
    private String status;
    private Integer sort;
    private String description;
    private Date create_time;
    private Date update_time;
    private Integer create_user;
    private Integer update_user;
}
