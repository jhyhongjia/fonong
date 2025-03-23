package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public void page(Integer pageNum, Integer pageSize) {
        Page<CompanyVO> page = new Page<>(pageNum, pageSize);
        companyService.selectCompanyPage(page,null);
    }

}
