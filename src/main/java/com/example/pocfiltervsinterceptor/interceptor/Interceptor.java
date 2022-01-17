package com.example.pocfiltervsinterceptor.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class Interceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod method = (HandlerMethod) handler; // handler : Obtenemos data a que controller y metodo le va a pegar dicho request
        System.out.println(method.getMethod().toString());

        if(!browserIsValid(request, "Chrome")) {
            log.error("{} ==== Browser is not valid desde HandlerInterceptor...", new Date());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

    private boolean browserIsValid(HttpServletRequest request, String browserNameValid) {
        // Actual validation logic
        String browser = request.getHeader(HttpHeaders.USER_AGENT);
        return  browser.contains(browserNameValid);
    }
}
