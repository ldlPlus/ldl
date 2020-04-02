/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： RouteImgService.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.service;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月29日  19:34
 */
public interface RouteImgService {
    List<RouteImg> findByRid(String rid);
}
