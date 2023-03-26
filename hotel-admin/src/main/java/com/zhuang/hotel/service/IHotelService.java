package com.zhuang.hotel.service;

import com.zhuang.hotel.pojo.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IHotelService extends IService<Hotel> {
    // 删除业务
    void deleteById(Long id);
    // 新增业务
    void insertById(Long id);
}
