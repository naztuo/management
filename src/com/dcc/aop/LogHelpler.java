/**
 * 
 */
package com.dcc.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.dcc.base.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @ClassName:LogAspect
 * @Description: 日志工具
 * @Author: zhouhc
 * @Date: 2020/1/7 10:49
 * @Version:1.0
 **/
public class LogHelpler {

	private static final Logger logger = LoggerFactory.getLogger(LogHelpler.class);

	/**
	 * 日志开始触发动作
	 * 
	 * @param classMethod
	 * @param reqContent
	 */
	public static void logStart(String classMethod, String reqContent) {
		String requestId = String.valueOf(UUID.randomUUID());
		MDC.put("requestId", requestId);

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assert attributes != null;
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		String url = request.getRequestURL().toString();// 请求url
		String httpMethod = request.getMethod();// 请求类型POST、GET
		String ip = request.getRemoteAddr();// 访问IP

		// 日志规则：请求类型 URL 访问IP 请求参数
		logger.info("{} {}  ip:{}   args:{} {}", httpMethod, url, ip, reqContent, classMethod);
	}
	

	/**
	 * 日志结束触发动作
	 * 
	 * @param rsp
	 * @param startTime
	 */
	public static void logEnd(Response rsp, long startTime) {

		long costs = System.currentTimeMillis() - startTime;// 请求耗时
		// 处理完请求，返回内容,打印耗时
		logger.info("costs:{}ms",costs);
		try {
			logger.info("response:{}", JSON.toJSONString(rsp));
		} catch (JSONException e) {
			logger.info("response:{}", rsp);
		}

		MDC.clear();
	}
}
