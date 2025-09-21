package com.tencent.wxcloudrun.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.entity.Bounds;
import com.tencent.wxcloudrun.entity.ClusterCompanyRequest;
import com.tencent.wxcloudrun.entity.Point;
import com.tencent.wxcloudrun.excel.*;
import com.tencent.wxcloudrun.mapper.CompanyMapper;
import com.tencent.wxcloudrun.dto.CompanyDTO;
import com.tencent.wxcloudrun.entity.CompanyEntity;
import com.tencent.wxcloudrun.service.CompanyService;
import com.tencent.wxcloudrun.tool.utils.GCJ02ToWGS84;
import com.tencent.wxcloudrun.util.ExcelUtil;
import com.tencent.wxcloudrun.util.MarkerClustererWithCompany;
import com.tencent.wxcloudrun.vo.ClusterCompanyResponseVO;
import com.tencent.wxcloudrun.vo.CompanyDetailVO;
import com.tencent.wxcloudrun.vo.CompanyVO;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper,CompanyEntity> implements CompanyService {
    private final CompanyMapper companyMapper;
    // 定义线程池，限制并发数为 3
//    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static final Semaphore semaphore = new Semaphore(3);
    private static final int RANDOM_BITS = 4; // 最后4位随机数
    @Override
    public IPage<CompanyEntity> selectCompanyPage(Page<CompanyEntity> page, CompanyVO companyVO) {
//        return page.setRecords(companyMapper.selectCompanyPage(page, companyVO));
        return companyMapper.selectCompanyPage(page, companyVO);
    }

    @Override
    public List<CompanyEntity> selectCompanyList(CompanyVO companyVO) {
        return companyMapper.selectCompanyList(companyVO);
    }

    @Override
    public List<CompanyDetailVO> selectCompanyListByLongitudeAndLatitude(Map<String,Double> northeast, Map<String,Double> southwest) {
        if(CollectionUtils.isEmpty(northeast) || CollectionUtils.isEmpty(southwest)){
            return Collections.emptyList();
        }
        Double longtitudeNortheast = northeast.get("longitude");
        Double longtitudeSouthwest = southwest.get("longitude");;
        Double latitudeNortheast = northeast.get("latitude") ;
        Double latitudeSouthwest = southwest.get("latitude");
        List<CompanyEntity> companyEntities = companyMapper.selectCompanyByLongitudeAndLatitude(longtitudeNortheast, longtitudeSouthwest, latitudeNortheast, latitudeSouthwest);
        GCJ02ToWGS84(companyEntities);
        List<CompanyDetailVO> companyVOList = companyEntities.stream().map(companyEntity -> {
            CompanyDetailVO companyDtailVO = new CompanyDetailVO();
            BeanUtils.copyProperties(companyEntity,companyDtailVO);
            companyDtailVO.setLongitude(companyEntity.getLongitude());
            companyDtailVO.setLatitude(companyEntity.getLatitude());
            return companyDtailVO;
        }).collect(Collectors.toList());

        return companyVOList;
    }

    public ClusterCompanyResponseVO getClusterByLongitudeAndLatitude(Map<String,Double> northeast, Map<String,Double> southwest,Integer viewWidth,Integer viewHeight,int clusterRadius) {
        Double longtitudeNortheast = northeast.get("longitude");//东北经度
        Double longtitudeSouthwest = southwest.get("longitude");//西南经度
        Double latitudeNortheast = northeast.get("latitude") ;//东北纬度
        Double latitudeSouthwest = southwest.get("latitude");//西南纬度
        List<CompanyEntity> companyEntities = companyMapper.selectCompanyByLongitudeAndLatitude(longtitudeNortheast, longtitudeSouthwest, latitudeNortheast, latitudeSouthwest);
        GCJ02ToWGS84(companyEntities);
        List<CompanyDetailVO> companyVOList = companyEntities.stream().map(companyEntity -> {
            CompanyDetailVO companyDtailVO = new CompanyDetailVO();
            BeanUtils.copyProperties(companyEntity,companyDtailVO);
            companyDtailVO.setLongitude(companyEntity.getLongitude());
            companyDtailVO.setLatitude(companyEntity.getLatitude());
            return companyDtailVO;
        }).collect(Collectors.toList());
        ClusterCompanyRequest clusterRequest = new ClusterCompanyRequest();
        clusterRequest.setCompanyDetailVOList(companyVOList);
        clusterRequest.setViewWidth(viewWidth);
        clusterRequest.setViewHeight(viewHeight);
        clusterRequest.setClusterRadius(clusterRadius);
        clusterRequest.setBounds(new Bounds(new Point(longtitudeSouthwest, latitudeSouthwest), new Point(longtitudeNortheast, latitudeNortheast)));
        ClusterCompanyResponseVO cluster = MarkerClustererWithCompany.cluster(clusterRequest);
        return cluster;
    }

    @Override
    public void importExcel(MultipartFile multipartFile, Long serviceId) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream, CompanyExcel.class, new CompanyImportListener(this))
                    .sheet()
                    .doRead();//			return Result.success("导入成功");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public void importNewExcel(MultipartFile multipartFile, Long serviceId) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream, CompanyNewExcel.class, new CompanyImportNewListener(this))
                    .sheet()
                    .doRead();
            //			return Result.success("导入成功");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
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

    public List<CompanyEntity> setLatitudeAndLongitude(List<CompanyEntity> companyList){
        String url = "https://restapi.amap.com/v3/geocode/geo";
        String key = "225e7c65011f0f3abda13e40b963fdc9"	;
        String city = "佛山";
//        List<CompanyEntity> companyEntities = companyMapper.selectCompanyList(null);
        List<CompanyEntity> collect = companyList.stream().map(companyEntity -> {
            companyEntity.setId(generate());
            try {
                semaphore.acquire();
                Map<String, String> latitudeAndLongitude = getLatitudeAndLongitude(url, key, companyEntity.getRegisteredAddress(), city);
                companyEntity.setLongitude(latitudeAndLongitude.get("longitude"));
                companyEntity.setLatitude(latitudeAndLongitude.get("latitude"));
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
            return companyEntity;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void setCompanyId(List<CompanyEntity> companyList) {
//        companyList.forEach( companyEntity->companyEntity.setId(generate()));
        companyList.forEach(companyEntity-> {
            if(companyEntity.getId()==null){
                companyEntity.setId(generate());
            }
        });
    }

    @Override
    public boolean exportCompany(HttpServletResponse response) {
        List<CompanyExportExcel> companyExcelList = new ArrayList<>();
        List<CompanyEntity> list = list();
        if(list.size()>0){
            list.forEach(companyEntity->{
                CompanyExportExcel companyExcel = new CompanyExportExcel();
                BeanUtils.copyProperties(companyEntity,companyExcel);
                companyExcelList.add(companyExcel);
            });
        }
        //输出到文件
        ExcelUtil.export(response, "用户数据" , "用户数据表", companyExcelList, CompanyExportExcel.class);

        return true;
    }

    @Override
    public boolean importCompanyExcel(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream, CompanyExportExcel.class, new CompanyImportSecListener(this))
                    .sheet()
                    .doRead();//			return Result.success("导入成功");
            log.debug("导入成功");
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.debug("导入失败");
            return false;
        }
    }


    @Override
    public boolean updateCompany(CompanyDTO companyEntity) {
        return updateById(companyEntity);
    }

    private Map<String, String> getLatitudeAndLongitude(String url,String key,String address,String city) {
        OkHttpClient client = new OkHttpClient();
        String fullUrl = String.format("%s?key=%s&address=%s&city=%s", url, key, address, city);
        Request request = new Request.Builder()
                .url(fullUrl)
                .get()
                .build();
        Map<String, String> location = new HashMap<>();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())throw new RuntimeException("Unexpected code " + response);
            String result = response.body().string();
            location = extractLocation(result);
            System.out.println("Longitude: " + location.get("longitude"));
            System.out.println("Latitude: " + location.get("latitude"));

        }catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }

    private static Map<String, String> extractLocation(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        if (!jsonObject.has("geocodes")) {
            throw new JSONException("Key 'geocodes' not found in JSON object.");
        }else{
            JSONArray geocodesArray = jsonObject.getJSONArray("geocodes");
            if (geocodesArray.length() > 0) {
                JSONObject geocode = geocodesArray.getJSONObject(0);
                String location = geocode.getString("location");
                String[] coordinates = location.split(",");
                String longitude = coordinates[0];
                String latitude = coordinates[1];
                Map<String, String> locationMap = new HashMap<>();
                locationMap.put("longitude", longitude);
                locationMap.put("latitude", latitude);
                return locationMap;
            }
        }
        return null;
    }



    private long generate() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long timestampPart = (System.currentTimeMillis() % 10_000_000_000L) * 100; // 取时间戳后10位
        long randomPart = ThreadLocalRandom.current().nextInt(1 << RANDOM_BITS);
        return timestampPart + randomPart; // 10+2=12位
    }

    private void GCJ02ToWGS84(List<CompanyEntity> companyEntityList){
        companyEntityList.forEach( companyEntity -> {
            String longitude = companyEntity.getLongitude();
             String latitude = companyEntity.getLatitude();
            if(StringUtils.isNotBlank(longitude) && StringUtils.isNotBlank(latitude)) {
                double[] transform = GCJ02ToWGS84.transform(Double.parseDouble(longitude), Double.parseDouble(latitude));
                companyEntity.setLongitude(String.valueOf(transform[0]));
                companyEntity.setLatitude(String.valueOf(transform[1]));
            }
        });

    }

}
