package com.eloan.business.domain;

import com.eloan.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("EmailActive")
@Getter
@Setter
public class EmailActive extends BaseDomain {

	private String uuidcode;
	private Long logininfoId;
	private String email;
	private Date sendDate;
}
