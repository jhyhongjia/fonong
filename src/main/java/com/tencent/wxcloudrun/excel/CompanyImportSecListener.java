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
public class CompanyImportSecListener extends AnalysisEventListener<CompanyExportExcel>  {
    private final CompanyService companyService;
    // 构造器注入
    public CompanyImportSecListener(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    private static final int BATCH_SIZE = 100;
    private List<CompanyEntity> cachedList = new ArrayList<>(BATCH_SIZE);

    // 用于校验
    @Override
    public void invoke(CompanyExportExcel data, AnalysisContext context) {
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
            companyService.setCompanyId(cachedList);
            companyService.saveOrUpdateBatch(cachedList);
        }
    }

    private void updateData(List<CompanyEntity> cachList){
        List<CompanyEntity> updateList = new ArrayList<>();
        List<CompanyEntity> addList = new ArrayList<>();
        //获取company的所有数据
        List<CompanyEntity> companylist = companyService.list();
        // 将companylist转换为以ID为key的Map，便于快速查找
        Map<Long, CompanyEntity> companyMap = companylist.stream()
                .collect(Collectors.toMap(CompanyEntity::getId, entity -> entity));

        for (CompanyEntity cacheEntity : cachList) {
            Long cacheId = cacheEntity.getId();
            if (companyMap.containsKey(cacheId)) {
                // ID存在，比较两个对象是否完全相同
                CompanyEntity companyEntity = companyMap.get(cacheId);
                if (!cacheEntity.equals(companyEntity)) {
                    // 对象内容不同，添加到updateList
                    updateList.add(cacheEntity);
                }
                // 如果相同，则不进行任何操作
            } else {
                // companylist中没有该ID，添加到addList
                addList.add(cacheEntity);
            }
        }
        companyService.updateBatchById(updateList);
        companyService.saveBatch(addList);
    }
}
