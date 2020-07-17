package com.astc.search.dao;


import com.astc.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuMapper extends ElasticsearchRepository<SkuInfo, Long> {

}
