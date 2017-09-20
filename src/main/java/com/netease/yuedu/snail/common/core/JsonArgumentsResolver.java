package com.netease.yuedu.snail.common.core;

import com.netease.yuedu.snail.common.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Component
public class JsonArgumentsResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Json.class);
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Json jsonAnno = methodParameter.getParameterAnnotation(Json.class);
        Class<?> parameterType = methodParameter.getParameterType();
        String requestParam = getAllRequestParams(nativeWebRequest);
        if (parameterType != null && StringUtils.isNotEmpty(requestParam)) {
            Object o = JsonUtil.toBean(requestParam, parameterType);
            return o;
        }
        return null;
    }

    private String getAllRequestParams(NativeWebRequest nativeWebRequest) throws IOException {
        String requestParam = null;
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String method = httpServletRequest.getMethod();
        if ("GET".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
            requestParam = httpServletRequest.getQueryString();
            requestParam = requestParam.replaceAll("%22", "\"");
        } else {
            StringBuilder buffer = new StringBuilder();
            String line;
            BufferedReader reader = httpServletRequest.getReader();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            requestParam = buffer.toString();
        }
        return requestParam;
    }

}
