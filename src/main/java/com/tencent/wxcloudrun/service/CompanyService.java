package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.dto.CompanyDTO;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.vo.ClusterCompanyResponseVO;
import com.tencent.wxcloudrun.vo.CompanyDetailVO;
import com.tencent.wxcloudrun.vo.CompanyVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CompanyService extends IService<CompanyEntity> {
    IPage<CompanyEntity> selectCompanyPage(Page<CompanyEntity> page, CompanyVO companyVO);

    List<CompanyEntity> selectCompanyList(CompanyVO companyVO);

    List<CompanyDetailVO> selectCompanyListByLongitudeAndLatitude(Map<String,Double> northeast, Map<String,Double> southwest);

    /**
     * 获取公司聚合
     * @param northeast 东北角
     * @param southwest 西南角
     * @return
     */
    ClusterCompanyResponseVO getClusterByLongitudeAndLatitude(Map<String,Double> northeast, Map<String,Double> southwest,Integer viewWidth,Integer viewHeight,int clusterRadius);

    void importExcel(MultipartFile multipartFile, Long serviceId);

    void importNewExcel(MultipartFile multipartFile, Long serviceId);

    boolean deleteCompany(Long id);

    boolean deleteCompanyList(List<Long> ids);

    List<CompanyEntity> setLatitudeAndLongitude(List<CompanyEntity> companyList);

    boolean updateCompany(CompanyDTO companyEntity);

    void setCompanyId(List<CompanyEntity> companyList);

    boolean exportCompany(HttpServletResponse response) ;

    boolean importCompanyExcel(MultipartFile multipartFile);

}
