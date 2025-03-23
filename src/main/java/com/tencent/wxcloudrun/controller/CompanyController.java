package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.service.CompanyService;
import com.tencent.wxcloudrun.vo.CompanyVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping(value = "/company/import")
    public void importExcel(MultipartFile multipartFile, Long serviceId) {
        companyService.importExcel(multipartFile,serviceId);
    }

    @GetMapping(value = "/company/page")
    public IPage<CompanyEntity> page(Integer pageNum, Integer pageSize) {
        Page<CompanyEntity> page = new Page<>(pageNum, pageSize);
        IPage<CompanyEntity> companyEntityIPage = companyService.selectCompanyPage(page, null);
        return companyEntityIPage;
    }

}
