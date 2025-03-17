package com.shop.cafe.interceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
	private static final int MAX_REQUESTS = 10;  // 허용 요청 횟수
    private static final int TIME_WINDOW = 5;   // 제한 시간(초)
    
	private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();
	 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        System.out.println("Hi, I'm interceptor.");
        
        return true;
    }  
}
