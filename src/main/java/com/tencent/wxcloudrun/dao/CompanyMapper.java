package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.vo.CompanyVO;

import java.util.List;


public interface CompanyMapper extends BaseMapper<CompanyEntity> {

    List<CompanyVO> selectCompanyPage(IPage page, CompanyVO companyVO);

    List<CompanyVO> selectCompanyByGender(Page<CompanyEntity> page, CompanyVO companyVO);

}
