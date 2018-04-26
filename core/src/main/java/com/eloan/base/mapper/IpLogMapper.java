package com.eloan.base.mapper;

import com.eloan.base.domain.IpLog;

import java.util.List;

public interface IpLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IpLog record);

    IpLog selectByPrimaryKey(Long id);

    List<IpLog> selectAll();

    int updateByPrimaryKey(IpLog record);

//    int queryForCount(IpLogQueryObject qo);
//
//    List<IpLog> query(IpLogQueryObject qo);
}
