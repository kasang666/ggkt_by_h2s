package com.h2s.ggkt.service_wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.h2s.ggkt.model.wechat.Menu;
import com.h2s.ggkt.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
public interface MenuService extends IService<Menu> {

    List<MenuVo> findMenuInfo();

    List<Menu> findMenuOneInfo();

    /**
     * 同步微信公众号菜单
     */
    void syncMenu();

    /**
     * 删除菜单
     */
    void removeMenu();

}
