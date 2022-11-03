package com.h2s.ggkt.service_order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.h2s.ggkt.model.order.OrderInfo;
import com.h2s.ggkt.vo.order.OrderInfoQueryVo;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
public interface OrderInfoService extends IService<OrderInfo> {

    Map<String, Object> findPageOrderInfo(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);
}
