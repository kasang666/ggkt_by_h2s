package com.h2s.ggkt.service_user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.user.UserLoginLog;
import com.h2s.ggkt.service_user.mapper.UserLoginLogMapper;
import com.h2s.ggkt.service_user.service.UserLoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登陆记录表 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
