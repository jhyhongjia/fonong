package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.dto.CompanyDTO;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.vo.CompanyVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CompanyService extends IService<CompanyEntity> {
    IPage<CompanyEntity> selectCompanyPage(Page<CompanyEntity> page, CompanyVO companyVO);

    List<CompanyEntity> selectCompanyList(CompanyVO companyVO);

    List<CompanyEntity> selectCompanyListByLongitudeAndLatitude(Map<String,Double> northeast, Map<String,Double> southwest);

    void importExcel(MultipartFile multipartFile, Long serviceId);

    boolean deleteCompany(Long id);

    boolean deleteCompanyList(List<Long> ids);

    List<CompanyEntity> setLatitudeAndLongitude(List<CompanyEntity> companyList);

    boolean updateCompany(CompanyDTO companyEntity);

}
