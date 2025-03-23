package com.tencent.wxcloudrun.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.service.CompanyService;
import com.tencent.wxcloudrun.service.impl.CompanyServiceImpl;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CompanyImportListener  extends AnalysisEventListener<CompanyExcel>  {
    private final CompanyService companyService;
    // 构造器注入
    public CompanyImportListener(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    private static final int BATCH_SIZE = 100;
    private List<CompanyEntity> cachedList = new ArrayList<>(BATCH_SIZE);

    // 用于校验
    @Override
    public void invoke(CompanyExcel data, AnalysisContext context) {
        // 生成实体类
        CompanyEntity company = new CompanyEntity();
        BeanUtils.copyProperties(data,company);
        cachedList.add(company);
    }

    /**
     * 解析后的操作
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!cachedList.isEmpty()) {
            List<CompanyEntity> companyEntities = companyService.setLatitudeAndLongitude(cachedList);
            companyService.saveBatch(companyEntities);
        }
    }
}
