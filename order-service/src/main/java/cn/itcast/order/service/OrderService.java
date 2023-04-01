package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;


    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2 利用RestTemplate发起http请求，查询用户
//        String url = "http://localhost:8081/user/" + order.getUserId();
//        String url = "http://userservice/user/" + order.getUserId();
        // Feign远程调用
        //   User user = restTemplate.getForObject(url, User.class);
        User user = userClient.findById(order.getUserId());
        // 封装user到 Order
        order.setUser(user);
        // 4.返回
        return order;
    }

    @SentinelResource("goodsgoods")
    public void queryGoods() {
        System.err.println("查询商品");
    }
}
