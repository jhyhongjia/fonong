package com.tencent.wxcloudrun.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.io.Serializable;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class CompanyNewExcel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = {"cust_name"}, index = 0)
    private String custName;

    @ExcelProperty(value = {"注册地址"}, index = 3)
    private String registeredAddress;

    @ExcelProperty(value = {"经度"}, index = 15)
    private String longitude;

    @ExcelProperty(value = {"纬度"}, index = 16)
    private String latitude;

    @ExcelProperty(value = {"简称"}, index = 17)
    private String abbr;

}
