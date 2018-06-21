package com.eloan.business.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class VedioAuthQueryObject extends BaseAuditQueryObject {

	private String keyword;

	public String getKeyword() {
		return StringUtils.hasLength(keyword) ? keyword : null;
	}
}
