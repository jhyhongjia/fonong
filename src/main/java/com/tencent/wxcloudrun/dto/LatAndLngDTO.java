package com.tencent.wxcloudrun.dto;

import lombok.Data;

import java.util.Map;

@Data
public class LatAndLngDTO {
    private Map<String,Double> northeast;
    private Map<String,Double> southwest;
    private Integer viewWidth;
    private Integer viewHeight;

    private int clusterRadius;//聚合半径
}
