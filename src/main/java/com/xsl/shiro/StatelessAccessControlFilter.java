package com.xsl.shiro;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsl.bean.ResponseBean;
import org.apache.shiro.web.filter.AccessControlFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问控制过滤器
 */
public class StatelessAccessControlFilter extends AccessControlFilter {



    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * <p>
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * <p>
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，回往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();

        if (url.equals("/user/login") || url.equals("/") || url.contains("/resources/")|| url.contains("/static/")) {
            return true;
        }
        String jwt = req.getHeader("Authorization");
        if (com.xsl.util.ValidateUtil.isNullOrEmpty(jwt)) {
            onLoginFail(response);
            return false;
        }
        StatelessAuthenticationToken token = new StatelessAuthenticationToken(null, jwt);
        try {
            //5、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            //6、登录失败
            onLoginFail(response);
            return false;//就直接返回给请求者.
        }
        return true;
    }

    //登录失败时默认返回401 状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        ResponseBean responseBean = new ResponseBean(ResponseBean.CODE_NOAUTH, "no auth.");
        httpResponse.getWriter().write(mapper.writeValueAsString(responseBean));
    }

}
