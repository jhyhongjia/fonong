package com.tencent.wxcloudrun.entity;

import lombok.Data;

// 地理范围类
@Data
public class Bounds {
    private Point sw;
    private Point ne;

    // getters and setters
    public Bounds(Point sw, Point ne) {
        this.sw = sw;
        this.ne = ne;
    }
}

