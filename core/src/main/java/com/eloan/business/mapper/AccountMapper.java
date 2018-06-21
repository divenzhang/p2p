package com.eloan.business.mapper;

import com.eloan.business.domain.Account;

import java.util.List;

public interface AccountMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Account record);

	Account selectByPrimaryKey(Long id);

	List<Account> selectAll();

	int updateByPrimaryKey(Account record);
}