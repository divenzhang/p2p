package com.eloan.business.mapper;

import com.eloan.business.domain.Realauth;
import com.eloan.business.query.RealAuthQueryObject;

import java.util.List;

public interface RealauthMapper {

	int insert(Realauth record);

	Realauth selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Realauth record);

	int queryForCount(RealAuthQueryObject qo);

	List<Realauth> query(RealAuthQueryObject qo);
}