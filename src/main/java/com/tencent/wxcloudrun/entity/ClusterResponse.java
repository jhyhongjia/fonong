package com.tencent.wxcloudrun.entity;

import lombok.Data;

import java.util.List;

// 聚类响应类
@Data
public class ClusterResponse {
    private List<Cluster> clusters; //聚合点
    private List<Point> points; //单点

    // getters and setters

    public ClusterResponse(List<Cluster> clusters, List<Point> points) {
        this.clusters = clusters;
        this.points = points;
    }
}
