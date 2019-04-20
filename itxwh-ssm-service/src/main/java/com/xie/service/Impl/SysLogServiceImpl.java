package com.xie.service.Impl;

import com.github.pagehelper.PageHelper;
import com.xie.dao.SysLogDao;
import com.xie.domain.SysLog;
import com.xie.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return sysLogDao.findAll();
    }
}
