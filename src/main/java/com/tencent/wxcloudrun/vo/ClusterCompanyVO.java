package com.tencent.wxcloudrun.vo;

import lombok.Data;

import java.util.List;

@Data
public class ClusterCompanyVO {
    private final static long serialVersionUID = 1L;

    private double lng;
    private double lat;
    private List<CompanyDetailVO> companyDetailVOList;
    private int count;

    // 构造方法及getters/setters
    public ClusterCompanyVO(double lng, double lat,List<CompanyDetailVO> companyDetailVOList, int count) {
        this.lng = lng;
        this.lat = lat;
        this.companyDetailVOList = companyDetailVOList;
        this.count = count;
    }
}