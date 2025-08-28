package com.tencent.wxcloudrun.vo;

import com.tencent.wxcloudrun.entity.Cluster;
import com.tencent.wxcloudrun.entity.Point;
import lombok.Data;

import java.util.List;

// 聚类响应类
@Data
public class ClusterCompanyResponseVO {
    private List<ClusterCompanyVO> clusters; //聚合点
    private List<CompanyDetailVO> points; //单点

    // getters and setters

    public ClusterCompanyResponseVO(List<ClusterCompanyVO> clusters, List<CompanyDetailVO> points) {
        this.clusters = clusters;
        this.points = points;
    }
}
