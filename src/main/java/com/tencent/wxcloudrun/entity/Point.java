package com.tencent.wxcloudrun.entity;

import lombok.Data;

// 坐标点类
@Data
public class Point {
    private double lng;
    private double lat;

    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    // getters and setters
}