package com.zhuang.hotel.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhuang.hotel.mapper.HotelMapper;
import com.zhuang.hotel.pojo.Hotel;
import com.zhuang.hotel.pojo.HotelDoc;
import com.zhuang.hotel.service.IHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public void deleteById(Long id) {
        try {
            // 1.准备Request
            DeleteRequest request = new DeleteRequest("hotel", id.toString());
            // 2.发送请求
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertById(Long id) {
        try {
            // 0.根据id查询酒店数据
            Hotel hotel = getById(id);
            // 转换为文档类型
            HotelDoc hotelDoc = new HotelDoc(hotel);
            // 1.准备Request对象
            IndexRequest request = new IndexRequest("hotel").id(hotel.getId().toString());
            // 2.准备Json文档
            request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
            // 3.发送请求
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
