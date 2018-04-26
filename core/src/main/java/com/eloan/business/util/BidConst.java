package com.eloan.business.util;

import java.math.BigDecimal;

public class BidConst {
    public static final int DISPLAY_SCALE = 2;//显示精度
    public static final int CAL_SCALE = 8;//计算精度
    public static final int STORE_SCALE = 4;//保存精度

    public static final BigDecimal ZERO = new BigDecimal("0.0000");//系统中需要的zero
    public static final BigDecimal DEFALUT_BORROWLIMITAMOUNT = new BigDecimal(
            "2000.0000");//初始用户授信额度


    public static final String DEFAULT_ADMIN_NAME="admin";
    public static final String DEFAULT_ADMIN_PASSWORD="1111";

    public static final int CREDIT_BORROW_SCORE=30;//信用信用分数
}
