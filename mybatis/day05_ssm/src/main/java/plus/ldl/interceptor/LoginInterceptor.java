package plus.ldl.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ldl.plus
 * @date 2020年03月24日  17:59
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            //未登录
            request.setAttribute("msg", "您尚未登陆，请登陆");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        } else {
            return true;
        }
    }
}
