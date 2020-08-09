package com.i.lubov.comps;

import java.io.Serializable;

public class OperationLogEvent implements Serializable {
    private String username;
    private String description;
    private String method;
    private String params;
    private String logType;
    private String requestIp;
    private String browser;
    private Long executeTime;
    private String exceptionDetail;

    public OperationLogEvent() {}

    public String getUsername() {
        return this.username;
    }

    public OperationLogEvent setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public OperationLogEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMethod() {
        return this.method;
    }

    public OperationLogEvent setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getParams() {
        return this.params;
    }

    public OperationLogEvent setParams(String params) {
        this.params = params;
        return this;
    }

    public String getLogType() {
        return this.logType;
    }

    public OperationLogEvent setLogType(String logType) {
        this.logType = logType;
        return this;
    }

    public String getRequestIp() {
        return this.requestIp;
    }

    public OperationLogEvent setRequestIp(String requestIp) {
        this.requestIp = requestIp;
        return this;
    }

    public String getBrowser() {
        return this.browser;
    }

    public OperationLogEvent setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public Long getExecuteTime() {
        return this.executeTime;
    }

    public OperationLogEvent setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
        return this;
    }

    public String getExceptionDetail() {
        return this.exceptionDetail;
    }

    public OperationLogEvent setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
        return this;
    }

    public String toString() {
        return "OperationLogEvent{username='" + this.username + '\'' + ", description='" + this.description + '\'' + ", method='" + this.method + '\'' + ", params='" + this.params + '\'' + ", logType='" + this.logType + '\'' + ", requestIp='" + this.requestIp + '\'' + ", browser='" + this.browser + '\'' + ", executeTime=" + this.executeTime + ", exceptionDetail='" + this.exceptionDetail + '\'' + '}';
    }
}
