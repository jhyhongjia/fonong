package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("company")
public class CompanyEntity {
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

    //	private String seqNo; // 序号
    private String custName; // 客户名称
    private String abbr;     // 企业简称
    //	private Integer businessStatus; // 经营状态
    private String businessStatus; // 经营状态
    private String legalRepresentative; // 法定代表人
    private String establishmentDate; // 成立时间
    private Float registeredCapital; // 注册资本
//    private Integer employeeCount; // 员工数
    private String creditCode; // 统一社会信用代码
    private String contact; // 联系方式
    private String email; // 邮箱
    private String registeredAddress; // 注册地址
//    private String marketingRecommendationLevel; // 营销推荐等级 (v1-v12)
    private String financingIntention; // 融资意向 (L1-L6)
    private String industryCategory; // 行业门类
//    private String nationalEconomicIndustry; // 国民经济行业
    //	private Integer companyScale; // 企业规模
    private String companyScale; // 企业规模
//    private Double taxStatus; // 纳税情况
    private String actualController; // 实际控制人
//    private String shareholderOwnership; // 股东持股穿透
    private String businessScope; // 经营范围
//    private Integer patentCount; // 专利次数
    private String parentGroup; // 所属集团
    private Double paidCapital; // 实缴资本
    private Boolean isSpecializedAndNew; // 是否专精特新
    private Boolean isHighTechEnterprise; // 是否高新技术企业
    private Boolean isStateOwned; // 是否国资
    private Boolean hasImportExportQualification; // 进出口资质
//    private String recentCaseInfo; // 近两年立案信息
//    private Integer recentAdminPenaltyCount; // 近连年行政处罚次数

    /** 土地面积（亩） */
    private Double landArea;

    /** 厂房面积（亩） */
    private Double factoryArea;

    /** 厂房产权（自有/租赁/混合） */
    private String factoryOwnership;

    /** 近一年营收（万元） */
    private Double lastYearRevenue;

    /** 近一年利润（万元） */
    private Double lastYearProfit;

    /** 产业链节点（原材料/半成品/成品/混合） */
    private String industryChainNode;

    /** 进出口量（美元/万元） */
    private Double importAndExportVolume;

    /**
     * 是否我行信贷客户
     */
    private boolean creditCustomer;

    /**
     * 主营业务
     */
    private String mainBusiness;

    /**
     * 营销建议
     */
    private String marketingSuggestions;



}
