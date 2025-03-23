package com.tencent.wxcloudrun.vo;

import com.tencent.wxcloudrun.entity.CompanyEntity;
import lombok.Data;

import java.util.Map;

@Data
public class CompanyVO extends CompanyEntity {
    Double longtitudeNortheast;
    Double longtitudeSouthwest;
    Double latitudeNortheast;
    Double latitudeSouthwest;
    Map<String,Double> northeast;
    Map<String,Double> southwest;
    Integer pageNum;
    Integer pageSize;
}
