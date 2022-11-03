package com.h2s.ggkt.service_wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.BusinessException;
import com.h2s.ggkt.model.wechat.Menu;
import com.h2s.ggkt.service_wechat.mapper.MenuMapper;
import com.h2s.ggkt.service_wechat.service.MenuService;
import com.h2s.ggkt.vo.wechat.MenuVo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private WxMpService wxMpService;

    @Override
    public List<MenuVo> findMenuInfo() {
        // 以此向查询出所有的数据，减少数据库查询次数
        List<Menu> menus = list();
        // 一级菜单
        List<Menu> oneMenus = menus.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
        // 二级菜单
        List<Menu> twoMenus = menus.stream().filter(menu -> menu.getParentId() != 0).collect(Collectors.toList());
        // 最终结果列表
        List<MenuVo> menuVos = new ArrayList<>(oneMenus.size());

        oneMenus.forEach(menu -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menu, menuVo);
            setChildren(menuVo, twoMenus);
            menuVos.add(menuVo);
        });

        return menuVos;
    }

    /**
     * 给一级菜单设置子菜单
     * @param menuVo
     * @param menus
     */
    private void setChildren(MenuVo menuVo, List<Menu> menus){
        List<MenuVo> menuVos = new ArrayList<>();
        menus.forEach(menu -> {
            if (menu.getParentId().longValue() == menuVo.getId()){
                MenuVo menuVo1 = new MenuVo();
                BeanUtils.copyProperties(menu, menuVo1);
                menuVos.add(menuVo1);
            }
        });
        menuVo.setChildren(menuVos);
    }


    @Override
    public List<Menu> findMenuOneInfo() {
        LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Menu::getParentId, 0);
        return list(lqw);
    }

    /**
     * 说明：
     * 自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
     * 一级菜单最多4个汉字，二级菜单最多8个汉字，多出来的部分将会以“...”代替。
     * 创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
     */
    @Override
    public void syncMenu() {
        List<MenuVo> menuVoList = this.findMenuInfo();
        //菜单
        JSONArray buttonList = new JSONArray();
        for(MenuVo oneMenuVo : menuVoList) {
            JSONObject one = new JSONObject();
            one.put("name", oneMenuVo.getName());
            JSONArray subButton = new JSONArray();
            for(MenuVo twoMenuVo : oneMenuVo.getChildren()) {
                JSONObject view = new JSONObject();
                view.put("type", twoMenuVo.getType());
                if(twoMenuVo.getType().equals("view")) {
                    view.put("name", twoMenuVo.getName());
                    view.put("url", "http://ggkt2.vipgz1.91tunnel.com/#"
                            +twoMenuVo.getUrl());
                } else {
                    view.put("name", twoMenuVo.getName());
                    view.put("key", twoMenuVo.getMeunKey());
                }
                subButton.add(view);
            }
            one.put("sub_button", subButton);
            buttonList.add(one);
        }
        //菜单
        JSONObject button = new JSONObject();
        button.put("button", buttonList);
        try {
            this.wxMpService.getMenuService().menuCreate(button.toJSONString());
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BusinessException("同步菜单失败");
        }
    }

    @Override
    public void removeMenu() {
        try {
            wxMpService.getMenuService().menuDelete();
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BusinessException("删除菜单失败");
        }
    }
}
