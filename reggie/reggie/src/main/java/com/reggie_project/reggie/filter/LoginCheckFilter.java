package com.reggie_project.reggie.filter;


import com.alibaba.fastjson.JSON;
import com.reggie_project.reggie.Controller.R;
import com.reggie_project.reggie.common.MyThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter("/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取本次请求的URI
        String requestURL = request.getRequestURI();

        log.info("拦截到的请求: {}", requestURL);

        // 定义可以放行的资源
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/login",     //移动端登陆界面
                "/user/sendMsg",
                 "/doc.html",
                 "/webjars/**",
                 "/swagger-resources",
                 "/v2/api-docs",
                "/swagger-ui.html"
        };

        // 2. 判断本次请求, 是否需要登录, 才可以访问
        boolean check = check(urls, requestURL);

        // 3. 如果不需要，则直接放行
        if (check) {
            log.info("不需要处理的请求: {}", requestURL);
            filterChain.doFilter(request, response);
            return;
        }

        // 4. 判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null) {

            log.info("用户已登入，用户ID为: {}", request.getSession().getAttribute("employee"));
            Long empId = (Long) request.getSession().getAttribute("employee");
            MyThreadLocal.setValue(empId);

            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登入...");
        if (request.getSession().getAttribute("user") != null) {

            log.info("用户已登入，用户ID为: {}", request.getSession().getAttribute("user"));
            Long UserId = (Long) request.getSession().getAttribute("user");
            MyThreadLocal.setValue(UserId);

            filterChain.doFilter(request, response);
            return;
        }


        // 5. 如果未登录, 则返回未登录结果，通过输出流的方式向客户端响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            // URL匹配
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
