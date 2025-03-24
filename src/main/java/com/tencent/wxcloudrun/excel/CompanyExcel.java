package com.tencent.wxcloudrun.excel;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class CompanyExcel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = {"cust_name"}, index = 1)
    private String custName;

//	@ExcelProperty(value = {"经营状态"}, index = 2)
//	private Integer businessStatusInteger;

    @ExcelProperty(value = {"经营状态"}, index = 2)
    private String businessStatus;

    @ExcelProperty(value = {"法定代表人"}, index = 3)
    private String legalRepresentative;

    @ExcelProperty(value = {"成立时间"}, index = 4)
    private String establishmentDate;

    @ExcelProperty(value = {"注册资本"}, index = 5)
    private Float registeredCapital;

    @ExcelProperty(value = {"员工数"}, index = 6)
    private Integer employeeCount;

    @ExcelProperty(value = {"统一社会信用代码"}, index = 7)
    private String creditCode;

    @ExcelProperty(value = {"联系方式"}, index = 8)
    private String contact;

    @ExcelProperty(value = {"邮箱"}, index = 9)
    private String email;

    @ExcelProperty(value = {"注册地址"}, index = 10)
    private String registeredAddress;

    @ExcelProperty(value = {"营销推荐等级（v1-v12）"}, index = 11)
    private String marketingRecommendationLevel;

    @ExcelProperty(value = {"融资意向（L1-L6）"}, index = 12)
    private String financingIntention;

    @ExcelProperty(value = {"行业门类"}, index = 13)
    private String industryCategory;
    //
    @ExcelProperty(value = {"国民经济行业"}, index = 14)
    private String nationalEconomicIndustry;

//	@ExcelProperty(value = {"企业规模"}, index = 15)
//	private String companyScale;

    @ExcelProperty(value = {"企业规模"}, index = 15)
    private String companyScale;

    @ExcelProperty(value = {"纳税情况"}, index = 16)
    private Double taxStatus;

    @ExcelProperty(value = {"实际控制人"}, index = 17)
    private String actualController;

    @ExcelProperty(value = {"股东持股穿透"}, index = 18)
    private String shareholderOwnership;

    @ExcelProperty(value = {"经营范围"}, index = 20)
    private String businessScope;

    @ExcelProperty(value = {"专利次数"}, index = 21)
    private Integer patentCount;

    @ExcelProperty(value = {"所属集团"}, index = 22)
    private String parentGroup;

    @ExcelProperty(value = {"实缴资本"}, index = 23)
    private Double paidCapital;

    @ExcelProperty(value = {"是否专精特新"}, index = 24)
    private Boolean isSpecializedAndNew;

    @ExcelProperty(value = {"是否高新技术企业"}, index = 25)
    private Boolean isHighTechEnterprise;

    @ExcelProperty(value = {"是否国资"}, index = 26)
    private Boolean isStateOwned;

    @ExcelProperty(value = {"进出口资质"}, index = 27)
    private String importExportQualification;

    @ExcelProperty(value = {"近两年立案信息"}, index = 28)
    private String recentCaseInfo;
}
