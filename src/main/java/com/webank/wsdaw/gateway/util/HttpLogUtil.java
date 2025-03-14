package com.webank.wsdaw.gateway.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpLogUtil {

    public static void printLogs(HttpRequest request, String requestBody, HttpResponse response) {
        log.info("Request Body: {}", requestBody);
        printLogs(request, response);
    }

    public static void printLogs(HttpRequest request, HttpResponse response) {
        printLogs(request);
        printLogs(response);
    }

    public static void printLogs(HttpRequest request) {
        // 打印完整的 HTTP 请求信息
        log.info("Request Method: {}", request.getMethod());
        log.info("Request URL: {} ", request.getUrl());
        log.info("Request Headers: {}", request.headers());
    }

    public static void printLogs(HttpResponse response) {
        // 打印完整的 HTTP 响应信息
        log.info("Response Status: {}", response.getStatus());
        log.info("Response Headers: {}", response.headers());
        log.info("Response Body: {}", JSONUtil.toJsonPrettyStr(response.body()));
    }
}
