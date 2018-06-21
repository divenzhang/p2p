package com.eloan.base.mapper;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SystemDictionary record);

	SystemDictionary selectByPrimaryKey(Long id);

	List<SystemDictionary> selectAll();

	int updateByPrimaryKey(SystemDictionary record);

	int queryForCount(SystemDictionaryQueryObject qo);

	List<SystemDictionary> query(SystemDictionaryQueryObject qo);
}