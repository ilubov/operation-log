package com.i.lubov.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("operation_log")
public class OperationLog extends Model<OperationLog> implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 方法
     */
    @TableField("method")
    private String method;

    /**
     * 参数
     */
    @TableField("params")
    private String params;

    /**
     * 日志类型
     */
    @TableField("log_type")
    private String logType;

    /**
     * 请求ip
     */
    @TableField("request_ip")
    private String requestIp;

    /**
     * 浏览器信息
     */
    @TableField("browser")
    private String browser;

    /**
     * 时间
     */
    @TableField("execute_time")
    private Date executeTime;

    /**
     * 异常信息
     */
    @TableField("exception_detail")
    private String exceptionDetail;
}