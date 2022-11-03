package com.h2s.ggkt.service_order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.order.OrderDetail;
import com.h2s.ggkt.service_order.mapper.OrderDetailMapper;
import com.h2s.ggkt.service_order.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
