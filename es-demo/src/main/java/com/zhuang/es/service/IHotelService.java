package com.zhuang.es.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuang.es.pojo.Hotel;
import com.zhuang.es.pojo.PageResult;
import com.zhuang.es.pojo.RequestParams;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params);
}
