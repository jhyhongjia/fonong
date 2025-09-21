package com.tencent.wxcloudrun.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class CompanyExportExcel {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = {"id"}, index = 0)
    private Long id;

    @ExcelProperty(value = {"企业名称"}, index = 1)
    private String custName;

    @ExcelProperty(value = {"所属区县"}, index = 2)
    private String district;

    @ExcelProperty(value = {"注册地址"}, index = 3)
    private String registeredAddress;

    @ExcelProperty(value = {"经度"}, index = 4)
    private String longitude;

    @ExcelProperty(value = {"纬度"}, index = 5)
    private String latitude;

    @ExcelProperty(value = {"园区"}, index = 6)
    private String park;

    @ExcelProperty(value = {"园区内厂房占地面积"}, index = 7)
    private Double landArea;

    @ExcelProperty(value = {"园区内厂房建筑面积"}, index = 8)
    private Double factoryArea;

    @ExcelProperty(value = {"厂房产权"}, index = 9)
    private String factoryOwnership;

    @ExcelProperty(value = {"厂房评估价值（万元）"}, index = 10)
    private Double factoryValue;

    @ExcelProperty(value = {"近一年营收(万元)"}, index = 11)
    private Double lastYearRevenue;

    @ExcelProperty(value = {"近一年利润(万元)"}, index = 12)
    private Double lastYearProfit;

    @ExcelProperty(value = {"资产负债率"}, index = 13)
    private Double debtRatio;

    @ExcelProperty(value = {"产业链节点"}, index = 14)
    private String industryChainNode;

    @ExcelProperty(value = {"进口/出口规模（万元）"}, index = 15)
    private Double importAndExportVolume;

    @ExcelProperty(value = {"他行贷款情况"}, index = 16)
    private String otherLoan;

    @ExcelProperty(value = {"简称"}, index = 17)
    private String abbr;
}
