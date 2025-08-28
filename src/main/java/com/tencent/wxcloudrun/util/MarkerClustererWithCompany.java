package com.tencent.wxcloudrun.util;

import com.tencent.wxcloudrun.entity.*;
import com.tencent.wxcloudrun.vo.ClusterCompanyResponseVO;
import com.tencent.wxcloudrun.vo.ClusterCompanyVO;
import com.tencent.wxcloudrun.vo.CompanyDetailVO;

import java.util.*;
import java.util.stream.Collectors;

//聚合工具类
public class MarkerClustererWithCompany {
    public static ClusterCompanyResponseVO cluster(ClusterCompanyRequest request) {
        // 参数解析
        //窗口的可视经纬度范围
        Bounds bounds = request.getBounds();
        //坐标点
//        List<Point> allPoints = request.getPoints();
        List<CompanyDetailVO> companyList = request.getCompanyDetailVOList();
        int viewWidth = request.getViewWidth();
        int viewHeight = request.getViewHeight();
        int clusterRadius = request.getClusterRadius();

        // 边界坐标
        double swLng = bounds.getSw().getLng();//西南经度
        double swLat = bounds.getSw().getLat();//西南纬度
        double neLng = bounds.getNe().getLng();//东北经度
        double neLat = bounds.getNe().getLat();//东北纬度

        // 1. 过滤出可见点
//        List<Point> visiblePoints = allPoints.stream()
//                .filter(p -> isPointVisible(p, swLng, swLat, neLng, neLat))
//                .collect(Collectors.toList());
        List<CompanyDetailVO> companyPoints = companyList.stream()
                .filter(p -> isPointVisible(p, swLng, swLat, neLng, neLat))
                .collect(Collectors.toList());

        // 2. 计算网格尺寸
        double lngSpan = neLng - swLng;
        double latSpan = neLat - swLat;
        if (lngSpan <= 0 || latSpan <= 0 || viewWidth <= 0 || viewHeight <= 0) {
            return new ClusterCompanyResponseVO(Collections.emptyList(), companyPoints);
        }

        double gridLng = (lngSpan / viewWidth) * clusterRadius * 2; // 经度跨度
        double gridLat = (latSpan / viewHeight) * clusterRadius * 2; // 纬度跨度

        // 3. 分配点到网格
        Map<String, List<CompanyDetailVO>> gridMap = new HashMap<>();
        for (CompanyDetailVO point : companyPoints) {
            int x = (int) Math.floor((point.getLongitude() - swLng) / gridLng);
            int y = (int) Math.floor((point.getLatitude() - swLat) / gridLat);
            String gridKey = x + "," + y;

            gridMap.computeIfAbsent(gridKey, k -> new ArrayList<>()).add(point);
        }

        // 4. 生成聚类结果
        List<ClusterCompanyVO> clusters = new ArrayList<>();
        List<CompanyDetailVO> singlePoints = new ArrayList<>();

        for (Map.Entry<String, List<CompanyDetailVO>> entry : gridMap.entrySet()) {
            List<CompanyDetailVO> gridPoints = entry.getValue();
            String[] coord = entry.getKey().split(",");
            int x = Integer.parseInt(coord[0]);
            int y = Integer.parseInt(coord[1]);

            // 计算网格中心坐标
            double centerLng = swLng + (x + 0.5) * gridLng;
            double centerLat = swLat + (y + 0.5) * gridLat;

            if (gridPoints.size() > 1) { // 聚合点
                clusters.add(new ClusterCompanyVO(centerLng, centerLat,gridPoints,gridPoints.size()));
            } else { // 单点
                singlePoints.addAll(gridPoints);
            }
        }

        return new ClusterCompanyResponseVO(clusters, singlePoints);
    }

    // 判断点是否在可视区域内
    private boolean isPointVisible(Point p, double swLng, double swLat, double neLng, double neLat) {
        return p.getLng() >= swLng && p.getLng() <= neLng
                && p.getLat() >= swLat && p.getLat() <= neLat;
    }

    // 判断点是否在可视区域内
    private static boolean isPointVisible(CompanyDetailVO p, double swLng, double swLat, double neLng, double neLat) {
        return p.getLongitude() >= swLng && p.getLongitude() <= neLng
                && p.getLatitude() >= swLat && p.getLatitude() <= neLat;
    }
}
