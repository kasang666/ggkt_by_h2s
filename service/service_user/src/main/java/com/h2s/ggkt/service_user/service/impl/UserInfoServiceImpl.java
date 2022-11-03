package com.h2s.ggkt.service_user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.user.UserInfo;
import com.h2s.ggkt.service_user.mapper.UserInfoMapper;
import com.h2s.ggkt.service_user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
