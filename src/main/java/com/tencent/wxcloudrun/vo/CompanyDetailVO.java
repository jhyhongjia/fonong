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
    private String businessStatus; // 经营状态
    private String legalRepresentative; // 法定代表人
    private String establishmentDate; // 成立时间
    private Float registeredCapital; // 注册资本
    private String creditCode; // 统一社会信用代码
    private String contact; // 联系方式
    private String email; // 邮箱
    private String registeredAddress; // 注册地址
    private String industryCategory; // 行业门类
    private String companyScale; // 企业规模
    private String businessScope; // 经营范围
    private String parentGroup; // 所属集团
    private Boolean hasImportExportQualification; // 进出口资质

}
