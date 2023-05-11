package com.changgou.search.service;

public interface EsManagerService {


    //创建索引库结构
    void createMappingAndIndex();

    //跟局spuid导入数据
    void importDataBySpuId(String spuId);

    //导入全部数据
    void importAll();

    void delDataBySpuId(String spuId);
}
