/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： SellerServiceImpl.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.util.BeanFactory;

/**
 * @author ldl.plus
 * @date 2020年02月29日  19:49
 */
public class SellerServiceImpl implements SellerService {
    private SellerDao sellerDao = (SellerDao) BeanFactory.getBean("sellerDao");

    @Override
    public Seller findBySid(int sid) {
        return sellerDao.findBySid(sid);
    }
}
