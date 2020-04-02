package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.BeanFactory;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @author ldl.plus
 * @date 2020年02月27日  16:26
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = (UserDao) BeanFactory.getBean("userDao");

    @Override
    public User userExist(String username) {
        return userDao.findUserExist(username);
    }

    @Override
    public boolean saveUser(User user) {
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        boolean flag = userDao.saveUser(user);

        String text = "你好，" + user.getUsername() + "！<br>" +
                "<br>" +
                "欢迎加入ldl.plus！请点击以下链接验证电子邮箱来激活您的帐号：<br>" +
                "<br>" +
                "http://localhost/travel/user/activeEmail?code=" + user.getCode() +
                "<br><br>" +
                "若这不是您本人要求的，请忽略本邮件，一切如常。";
        MailUtils.sendMail(user.getEmail(), text, "【ldl.plus】邮箱验证激活");

        return flag;
    }

    @Override
    public User active(String code) {
        User user = userDao.findByCode(code);
        if (user != null && "N".equals(user.getStatus())) {
            //未激活
            userDao.updateStatus(user);
            return user;
        } else {
            //已经激活或者没有此激活码
            return null;
        }
    }

    @Override
    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public void resendEmail(User user) {
        user.setCode(UuidUtil.getUuid());
        userDao.updateActivationCode(user);

        String text = "你好，" + user.getUsername() + "！<br>" +
                "<br>" +
                "欢迎加入ldl.plus！之前链接已失效，请点击以下链接验证电子邮箱来激活您的帐号：<br>" +
                "<br>" +
                "http://localhost/travel/user/activeEmail?code=" + user.getCode() +
                "<br><br>" +
                "若这不是您本人要求的，请忽略本邮件，一切如常。";
        MailUtils.sendMail(user.getEmail(), text, "【ldl.plus】邮箱验证激活");
    }
}
