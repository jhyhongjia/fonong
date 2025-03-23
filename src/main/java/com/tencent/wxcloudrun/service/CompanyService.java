package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.vo.CompanyVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService  {
    IPage<CompanyVO> selectCompanyPage(IPage<CompanyVO> page, CompanyVO companyVO);

    void importExcel(MultipartFile multipartFile, Long serviceId);

    boolean deleteCompany(Long id);

    boolean deleteCompanyList(List<Long> ids);

}
