package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.vo.CompanyVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService  {
    IPage<CompanyEntity> selectCompanyPage(Page<CompanyEntity> page, CompanyVO companyVO);

    void importExcel(MultipartFile multipartFile, Long serviceId);

    boolean deleteCompany(Long id);

    boolean deleteCompanyList(List<Long> ids);

}
