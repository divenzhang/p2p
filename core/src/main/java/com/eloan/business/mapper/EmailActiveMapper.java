package com.eloan.business.mapper;

import com.eloan.business.domain.EmailActive;

import java.util.List;

public interface EmailActiveMapper {
	int deleteByPrimaryKey(Long id);

	int insert(EmailActive record);

	EmailActive selectByPrimaryKey(Long id);

	List<EmailActive> selectAll();

	int updateByPrimaryKey(EmailActive record);
	
	EmailActive selectByCode(String code);
}