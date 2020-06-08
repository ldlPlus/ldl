package com.changgou.search.mapper;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ldl.plus
 * @date 2020年06月07日  17:19
 */
public interface ESManagerMapper extends ElasticsearchRepository<SkuInfo, Long> {

}
