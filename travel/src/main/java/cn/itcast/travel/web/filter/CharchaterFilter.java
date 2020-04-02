package cn.itcast.travel.web.filter;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 *
 * @author ldl
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if ("post".equalsIgnoreCase(method)) {
            request.setCharacterEncoding("utf-8");
        }

        //自动登录功能
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //判断是否有cookie登陆信息
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("autoLogin".equals(name)) {
                        //存在cookie信息
                        String autoLoginCook = cookie.getValue();
                        Jedis jedis = JedisUtil.getJedis();
                        String code = jedis.get(autoLoginCook);
                        if (code != null && code.length() > 0) {
                            //Jedis中有数据，可以自动登录
                            UserDaoImpl userDao = new UserDaoImpl();
                            User u = userDao.findByCode(code);
                            request.getSession().setAttribute("user", u);
                            System.err.println("自动登录中。。。");
                        }
                        break;
                    }
                }
            }
        }

        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
