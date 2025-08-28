package com.tencent.wxcloudrun.entity;

import com.tencent.wxcloudrun.vo.CompanyDetailVO;
import lombok.Data;

import java.util.List;

// 聚类请求类
@Data
public class ClusterRequest {
    //窗口的可视经纬度范围
    private Bounds bounds;
    //当前地图的缩放级别
    private int zoom;
    //坐标点
    private List<Point> points;
    private int viewWidth;
    private int viewHeight;
    private int clusterRadius = 20;

    // getters and setters
}
