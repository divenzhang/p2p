package com.eloan.mgrtool.util;

import com.eloan.base.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CreateDefaultAdminListener implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ILogininfoService logininfoService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!logininfoService.hasAdmin()){
			logininfoService.createDefaultAdmin();
		}
	}

}
