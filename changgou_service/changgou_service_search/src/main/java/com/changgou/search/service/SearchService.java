package com.changgou.search.service;

import java.util.Map;

public interface SearchService {


    //按照查询条件进行查询
    //网络制式:4G
    Map search(Map<String,String> searchMap);
}
