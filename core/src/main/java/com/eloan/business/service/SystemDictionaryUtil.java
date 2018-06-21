package com.eloan.business.service;

import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据字典工具类
 * @author Administrator
 *
 */
@Component
public class SystemDictionaryUtil {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	public List<SystemDictionaryItem> list(String sn) {
		return systemDictionaryService.queryBySn(sn);
	}

}
