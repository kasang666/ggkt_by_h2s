package com.h2s.ggkt.service_order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.order.OrderInfo;
import com.h2s.ggkt.service_order.mapper.OrderInfoMapper;
import com.h2s.ggkt.service_order.service.OrderInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

}
