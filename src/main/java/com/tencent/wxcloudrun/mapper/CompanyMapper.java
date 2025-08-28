package com.tencent.wxcloudrun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.vo.CompanyVO;

import java.util.List;


public interface CompanyMapper extends BaseMapper<CompanyEntity> {

    IPage<CompanyEntity> selectCompanyPage(Page<CompanyEntity> page, CompanyVO companyVO);

    List<CompanyEntity> selectCompanyList(CompanyVO companyVO);

    List<CompanyEntity> selectCompanyByLongitudeAndLatitude(Double longtitudeNortheast,
    Double longtitudeSouthwest,
    Double latitudeNortheast,
    Double latitudeSouthwest);

}
