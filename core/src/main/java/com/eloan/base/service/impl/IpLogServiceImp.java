package com.eloan.base.service.impl;

import com.eloan.base.domain.IpLog;
import com.eloan.base.mapper.IpLogMapper;
import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.query.PageResult;
import com.eloan.base.service.IpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpLogServiceImp implements IpLogService {
    @Autowired
    private IpLogMapper ipLogMapper;
    @Override
    public void insert(IpLog ipLog) {

    }

    @Override
    public PageResult query(IpLogQueryObject qo) {
        int count =this.ipLogMapper.queryForCount(qo);
        if (count > 0) {
            List<IpLog> list = this.ipLogMapper.query(qo);
            return new PageResult(count,qo.getPageSize(),qo.getCurrentPage(),list);
        }
        return PageResult.empty(qo.getPageSize());
    }
}
