package com.astc.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.astc.search.dao.SkuMapper;
import com.astc.search.pojo.SkuInfo;
import com.astc.search.service.SkuInfoService;
import com.astc.shangpin.feign.SkuFeign;
import com.astc.shangpin.pojo.Sku;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SkuInfoServiceImpl
 * @Description 将数据库数据导入索引库中
 * @Author 彭茜奇
 * @Date 0:16 2020/5/18
 * @Version 2.1
 **/
@Service
public class SkuInfoServiceImpl implements SkuInfoService {
    @Autowired
    private SkuMapper skuMapper;

    @Autowired(required = false)
    private SkuFeign skuFeign;

    @Override
    public void importSkuInfoToEs() {
        //通过feign接口调用商品微服务
        Result<List<Sku>> skuByStatus = skuFeign.findSkuByStatus("1");
        String text = JSON.toJSONString(skuByStatus.getData());
        //将数据转换成SkuInfo
        List<SkuInfo> skuInfos = JSON.parseArray(text, SkuInfo.class);
        //处理动态字段 (商品规格)
        for (SkuInfo skuInfo : skuInfos) {
            String spec = skuInfo.getSpec();
            Map<String, Object> specMap = JSON.parseObject(spec, Map.class);
            skuInfo.setSpecMap(specMap);
        }
        //将数据导入到es
        skuMapper.saveAll(skuInfos);
    }
}
