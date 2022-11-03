package com.h2s.ggkt.service_order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.order.PaymentInfo;
import com.h2s.ggkt.service_order.mapper.PaymentInfoMapper;
import com.h2s.ggkt.service_order.service.PaymentInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付信息表 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

}
