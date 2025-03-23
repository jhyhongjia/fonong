package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.excel.CompanyExcel;
import com.tencent.wxcloudrun.dao.CompanyMapper;
import com.tencent.wxcloudrun.service.CompanyService;
import com.tencent.wxcloudrun.vo.CompanyVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
//    private static final ThreadLocal<Map<String, EmployeeExcelnfoDTO>> employeeInfosMapThreadLocal = new ThreadLocal<>();
//
//    private static final ThreadLocal<List<EmployeeImportErrorExcel>> errorListThreadLocal;
    @Override
    public IPage<CompanyVO> selectCompanyPage(IPage<CompanyVO> page, CompanyVO companyVO) {
        return page.setRecords(companyMapper.selectCompanyPage(page, companyVO));
    }

    @Override
    public void importExcel(MultipartFile multipartFile, Long serviceId) {
//        File file = FileUtil.multipartToFile(multipartFile);

    }

    @Override
    public boolean deleteCompany(Long id) {
        if (id != null) {
//            return removeById(id);
        }
        return false;
    }

    @Override
    public boolean deleteCompanyList(List<Long> ids) {
        if (ids != null && ids.size() > 0) {
//            return removeByIds(ids);
        }
        return false;
    }


}
