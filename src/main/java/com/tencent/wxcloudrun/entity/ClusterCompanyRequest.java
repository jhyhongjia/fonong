package com.tencent.wxcloudrun.entity;

import com.tencent.wxcloudrun.vo.CompanyDetailVO;
import lombok.Data;

import java.util.List;

@Data
public class ClusterCompanyRequest extends ClusterRequest{
    private List<CompanyDetailVO> companyDetailVOList;

}
