package cn.piao888.mydreamwork.vo;

import lombok.Data;

@Data
public class UserVo {
    private String username; // 通道id
    private String password; // 通道名称
    private String remember; // 通道状态, authorized  已授权通道正常   authorizedExpired   已授权但失效
}