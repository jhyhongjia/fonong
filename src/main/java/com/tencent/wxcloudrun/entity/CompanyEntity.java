package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("company")
public class CompanyEntity {
    private final static long serialVersionUID = 1L;

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty(value = "主键")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private String latitude;
    //	private String seqNo; // 序号

    @ApiModelProperty(value = "客户名称")
    @TableField("cust_name")
    private String custName; // 客户名称

    @ApiModelProperty(value = "企业简称")
    @TableField("abbr")
    private String abbr;     // 企业简称

    @ApiModelProperty(value = "经营状态")
    @TableField("business_status")
    private String businessStatus; // 经营状态

    @ApiModelProperty(value = "法定代表人")
    @TableField("legal_representative")
    private String legalRepresentative; // 法定代表人

    @ApiModelProperty(value = "成立时间")
    @TableField("establishment_date")
    private String establishmentDate; // 成立时间

    @ApiModelProperty(value = "注册资本")
    @TableField("registered_capital")
    private Float registeredCapital; // 注册资本
//    private Integer employeeCount; // 员工数

    @ApiModelProperty(value = "统一社会信用代码")
    @TableField("credit_code")
    private String creditCode; // 统一社会信用代码

    @ApiModelProperty(value = "联系方式")
    @TableField("contact")
    private String contact; // 联系方式

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email; // 邮箱

    @ApiModelProperty(value = "注册地址")
    @TableField("registered_address")
    private String registeredAddress; // 注册地址

    @ApiModelProperty(value = "所属区县")
    @TableField("district")
    private String district;

    @ApiModelProperty(value = "园区")
    @TableField("park")
    private String park;

    @ApiModelProperty(value = "园区内厂房占地面积（亩）")
    @TableField("land_area")
    private Double landArea;

    /** 园区内厂房建筑面积（亩） */
    @ApiModelProperty(value = "园区内厂房占地面积（亩）")
    @TableField("factory_area")
    private Double factoryArea;
    /** 园区内厂房产权（自有/租赁/混合） */
    @ApiModelProperty(value = "园区内厂房产权（自有/租赁/混合）")
    @TableField("factory_ownership")
    private String factoryOwnership;
    /** 园区内厂房评估价值（万元） */
    @ApiModelProperty(value = "园区内厂房评估价值（万元）")
    @TableField("factory_value")
    private Double factoryValue;
//    private String marketingRecommendationLevel; // 营销推荐等级 (v1-v12)
    @ApiModelProperty(value = "融资意向")
    @TableField("financing_intention")
    private String financingIntention; // 融资意向 (L1-L6)

    @ApiModelProperty(value = "行业门类")
    @TableField("industry_category")
    private String industryCategory; // 行业门类
//    private String nationalEconomicIndustry; // 国民经济行业

    @ApiModelProperty(value = "企业规模")
    @TableField("company_scale")
    private String companyScale; // 企业规模

//    private Double taxStatus; // 纳税情况
    @ApiModelProperty(value = "实际控制人")
    @TableField("actual_controller")
    private String actualController; // 实际控制人
//    private String shareholderOwnership; // 股东持股穿透

    @ApiModelProperty(value = "经营范围")
    @TableField("business_scope")
    private String businessScope; // 经营范围
//    private Integer patentCount; // 专利次数

    @ApiModelProperty(value = "所属集团")
    @TableField("parent_group")
    private String parentGroup; // 所属集团

    @ApiModelProperty(value = "实缴资本")
    @TableField("paid_capital")
    private Double paidCapital; // 实缴资本

    @ApiModelProperty(value = "是否专精特新")
    @TableField("is_specialized_and_new")
    private Boolean isSpecializedAndNew; // 是否专精特新

    @ApiModelProperty(value = "是否高新技术企业")
    @TableField("is_high_tech_enterprise")
    private Boolean isHighTechEnterprise; // 是否高新技术企业
    private Boolean isStateOwned; // 是否国资

    private Boolean hasImportExportQualification; // 进出口资质
//    private String recentCaseInfo; // 近两年立案信息
//    private Integer recentAdminPenaltyCount; // 近连年行政处罚次数

    /** 近一年营收（万元） */
    @ApiModelProperty(value = "近一年营收（万元）")
    @TableField("last_year_revenue")
    private Double lastYearRevenue;

    /** 近一年利润（万元） */
    @ApiModelProperty(value = "近一年利润（万元）")
    @TableField("last_year_profit")
    private Double lastYearProfit;

    /** 产业链节点（原材料/半成品/成品/混合） */
    @ApiModelProperty(value = "产业链节点（原材料/半成品/成品/混合）")
    @TableField("industry_chain_node")
    private String industryChainNode;

    /** 进出口量（美元/万元） */
    @ApiModelProperty(value = "进出口量（美元/万元）")
    @TableField("import_and_export_volume")
    private Double importAndExportVolume;

    @ApiModelProperty(value = "是否我行信贷客户")
    @TableField("credit_customer")
    private boolean creditCustomer;

    @ApiModelProperty(value = "主营业务")
    @TableField("main_business")
    private String mainBusiness;

    @ApiModelProperty(value = "营销建议")
    @TableField("marketing_suggestions")
    private String marketingSuggestions;

    @ApiModelProperty(value = "他行贷款情况")
    @TableField("other_loan")
    private String otherLoan;

    @ApiModelProperty(value = "资产负债率")
    @TableField("debt_ratio")
    private Double debtRatio;



}
