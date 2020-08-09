package com.i.lubov.controller;

import com.i.lubov.annotation.Log;
import com.i.lubov.entity.OperationLog;
import com.i.lubov.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class OperationLogController {
    @Autowired
    private OperationLogService operationLogService;

    @Log("获取全部日志")
    @GetMapping("/all")
    public List<OperationLog> getAll() {
        return operationLogService.list();
    }
}
