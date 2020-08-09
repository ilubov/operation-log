/**
 * Copyright (c) 2017-2019 Htwins
 * https://www.htwins.com.cn
 * All rights reserved.
 */
package com.i.lubov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i.lubov.entity.OperationLog;
import com.i.lubov.mapper.OperationLogMapper;
import com.i.lubov.service.OperationLogService;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
}