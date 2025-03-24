package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.service.CompanyService;
import com.tencent.wxcloudrun.vo.CompanyVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    /**
     * 导入
     */
    @PostMapping("/company/import")
    public void importExcel(MultipartFile multipartFile) {
        companyService.importExcel(multipartFile,null);
    }

    @PostMapping(value = "/company/page")
    public IPage<CompanyEntity> page(@RequestBody CompanyVO companyVO) {
        Page<CompanyEntity> page = new Page(companyVO.getPageNum(), companyVO.getPageSize());
        IPage<CompanyEntity> companyEntityIPage = companyService.selectCompanyPage(page, companyVO);
        return companyEntityIPage;
    }

    @PostMapping(value = "/company/getByLongitudeAndLatitude")
    public List<CompanyEntity> getCompanyByLongitudeAndLatitude(@RequestBody CompanyVO companyVO) {
        List<CompanyEntity> companyEntityIPage = companyService.selectCompanyListByLongitudeAndLatitude(companyVO.getNortheast(), companyVO.getSouthwest());
        return companyEntityIPage;
    }



}
