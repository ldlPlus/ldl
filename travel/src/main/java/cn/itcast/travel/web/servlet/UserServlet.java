package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.BeanFactory;
import cn.itcast.travel.util.JedisUtil;
import cn.itcast.travel.util.Md5Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年02月28日  16:47
 * 与User有关的请求
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = (UserService) BeanFactory.getBean("userService");

    public void usernameExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = userService.userExist(username);
        if (user == null) {
            resp.getWriter().write("false");
        } else {
            resp.getWriter().write("true");
        }
    }

    public void checkCodeDecide(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String checkCodeServer = (String) req.getSession().getAttribute("CHECKCODE_SERVER");
        String checkCode = req.getParameter("checkCode");
        if (checkCodeServer != null && checkCodeServer.equalsIgnoreCase(checkCode)) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String checkCode = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkCodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        if (checkCodeServer == null || !checkCodeServer.equalsIgnoreCase(checkCode)) {
            //验证码错误
            info.setFlag(false);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), info);
            return;
        }

        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //加密密码
        String password = req.getParameter("password");
        try {
            user.setPassword(Md5Util.encodeByMd5(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserServiceImpl userService = new UserServiceImpl();
        boolean flag = userService.saveUser(user);
        if (flag) {
            //注册成功
            info.setFlag(true);
            info.setErrorMsg("注册成功，请激活");
        } else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), info);
    }

    public void activeEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null && code.length() == 32) {
            User user = userService.active(code);

            String msg;
            if (user != null) {
                msg = "激活成功，以帮您自动登录";
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/active_ok.html");

            } else {
                msg = "激活失败，请联系管理员处理！";
                resp.getWriter().write(msg);
            }
        }
    }

    public void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("autoLogin".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String checkCode = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkCodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        if (checkCodeServer == null || !checkCodeServer.equalsIgnoreCase(checkCode)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), info);
            return;
        }

        //加密密码
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        try {
            password = Md5Util.encodeByMd5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = userService.login(username, password);
        if (user == null) {
            //没有此账号
            info.setFlag(false);
            info.setErrorMsg("帐号或密码错误，请重试");
        }
        if (user != null) {
            if ("Y".equals(user.getStatus())) {
                //登录成功，并已激活
                req.getSession().setAttribute("user", user);
                info.setFlag(true);
                //设置自动登录
                String autoLogin = req.getParameter("autoLogin");
                if ("on".equals(autoLogin)) {
                    //已经勾选自动登录
                    String autoLoginCook = null;
                    try {
                        autoLoginCook = Md5Util.encodeByMd5(user.getUsername() + user.getPassword());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //设置自动登录cookie
                    Cookie cookie = new Cookie("autoLogin", autoLoginCook);
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    Jedis jedis = JedisUtil.getJedis();
                    jedis.setex(autoLoginCook, 60 * 60 * 24 * 30, user.getCode());
                    resp.addCookie(cookie);
                    System.err.println("设置自动登录cookie。。。");
                }
            } else {
                //未激活
                info.setFlag(false);
                info.setErrorMsg("未激活！已发送新激活邮件，请激活后登录");
                userService.resendEmail(user);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getWriter(), info);
    }

    public void welcomeName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            resp.getWriter().write(user.getUsername());
        } else {
            resp.getWriter().write("");
        }
    }
}
