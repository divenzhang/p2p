package com.eloan.business.mapper;

import com.eloan.business.domain.Userinfo;

import java.util.List;

public interface UserinfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Userinfo record);

	Userinfo selectByPrimaryKey(Long id);

	List<Userinfo> selectAll();

	int updateByPrimaryKey(Userinfo record);
}