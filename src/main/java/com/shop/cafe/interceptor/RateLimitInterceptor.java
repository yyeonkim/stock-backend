package com.shop.cafe.interceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
	// 5초 동안 10회 허용
	private static final int MAX_REQUESTS = 10;  
    private static final int TIME_WINDOW = 5;   
    
	private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public RateLimitInterceptor() {
        // TIME_WINDOW(초)마다 요청 카운트 기록 초기화
        scheduler.scheduleAtFixedRate(requestCounts::clear, TIME_WINDOW, TIME_WINDOW, TimeUnit.SECONDS);
    }
	 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String clientIP = request.getRemoteAddr();
		
		requestCounts.putIfAbsent(clientIP, 0);
		
		int currCount = requestCounts.get(clientIP);
		
		// 요청 횟수 초과
		if (currCount >= MAX_REQUESTS) {
			response.sendError(429, "Blocked. Retry later.");
			return false;
		}
		
		requestCounts.put(clientIP, currCount + 1);
		System.out.println("API count: " + (currCount + 1));
        
        return true;
    }  
}
