package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.dto.CompanyDTO;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.service.CompanyService;
import com.tencent.wxcloudrun.tool.api.R;
import com.tencent.wxcloudrun.vo.CompanyDetailVO;
import com.tencent.wxcloudrun.vo.CompanyVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 导入
     */
    @PostMapping("/company/importNew")
    public void importExcelNew(MultipartFile multipartFile) {
        companyService.importNewExcel(multipartFile,null);
    }

    @PostMapping(value = "/company/page")
    public R<IPage<CompanyEntity>> page(@RequestBody CompanyVO companyVO) {
        Page<CompanyEntity> page = new Page(companyVO.getPageNum(), companyVO.getPageSize());
        IPage<CompanyEntity> companyEntityIPage = companyService.selectCompanyPage(page, companyVO);
        return R.data(companyEntityIPage);
    }

    /**
     * 查询经纬度
     * @param companyVO
     * @return
     */
    @PostMapping(value = "/company/getByLongitudeAndLatitude")
    public R<List<CompanyDetailVO>> getCompanyByLongitudeAndLatitude(@RequestBody CompanyVO companyVO) {
        List<CompanyDetailVO> companyEntityIPage = companyService.selectCompanyListByLongitudeAndLatitude(companyVO.getNortheast(), companyVO.getSouthwest());
        return R.data(companyEntityIPage);
    }

    @PostMapping(value = "/company/updateCompany")
    public R<Boolean> updateCompany(@RequestBody CompanyDTO company) {
        return R.status(companyService.updateCompany(company));
    }

    @PostMapping(value = "/company/delCompany")
    public R<Boolean> delCompany(@RequestParam List<Long> ids) {
        return R.status(companyService.deleteCompanyList(ids));
    }



}
