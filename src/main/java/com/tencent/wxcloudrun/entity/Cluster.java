package com.tencent.wxcloudrun.entity;

import lombok.Data;

// 聚类结果类
@Data
public class Cluster {
    private double lng;
    private double lat;
    private int count;

    // 构造方法及getters/setters
    public Cluster(double lng, double lat, int count) {
        this.lng = lng;
        this.lat = lat;
        this.count = count;
    }
}