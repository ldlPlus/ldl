/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： RouteImgServiceImpl.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.service.RouteImgService;
import cn.itcast.travel.util.BeanFactory;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月29日  19:34
 */
public class RouteImgServiceImpl implements RouteImgService {
    private RouteImgDao routeImgDao = (RouteImgDao) BeanFactory.getBean("routeImgDao");

    @Override
    public List<RouteImg> findByRid(String rid) {
        return routeImgDao.findByRid(Integer.parseInt(rid));
    }
}
