package com.zhuang.es.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuang.es.pojo.Hotel;
import com.zhuang.es.pojo.PageResult;
import com.zhuang.es.pojo.RequestParams;

import java.util.List;
import java.util.Map;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params);

    Map<String, List<String>> filters(RequestParams params);

    List<String> getSuggestions(String prefix);
}
