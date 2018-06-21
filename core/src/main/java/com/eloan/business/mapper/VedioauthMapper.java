package com.eloan.business.mapper;

import com.eloan.business.domain.Vedioauth;
import com.eloan.business.query.VedioAuthQueryObject;

import java.util.List;

public interface VedioauthMapper {
	int insert(Vedioauth record);

	Vedioauth selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Vedioauth record);

	int queryForCount(VedioAuthQueryObject qo);

	List<Vedioauth> query(VedioAuthQueryObject qo);
}