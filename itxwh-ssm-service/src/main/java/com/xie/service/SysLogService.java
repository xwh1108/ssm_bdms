package com.xie.service;

import com.xie.domain.SysLog;

import java.util.List;

public interface SysLogService {
    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(int page,int pageSize) throws Exception;
}
