package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("company")
public class CompanyEntity {
//    private Long id;

    private String name;

    private Integer status;

    private Double registeredCapital;

    private Date startTime;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;




}
