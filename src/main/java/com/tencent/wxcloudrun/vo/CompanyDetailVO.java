package com.tencent.wxcloudrun.vo;

import lombok.Data;

@Data
public class CompanyDetailVO {
    private final static long serialVersionUID = 1L;

    private Long id;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    private String custName; // 客户名称
    private String abbr;     // 企业简称
    private String industryCategory; // 行业门类
    private String businessStatus; //经营状态

}
