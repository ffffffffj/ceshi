package com.changgou.search.dao;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface ESManagerMapper extends ElasticsearchCrudRepository<SkuInfo,Long> {
}
