package com.wdy.commons.base.enums;

/**
 * User: yanghongguang
 * Date: 2020/3/9
 * Time: 15:06
 * Description:
 */
public enum  ItemStautsEnum {
    UNAUDITED("0","未审核"),
    IN_AUDIT("1","审核中"),
    AUDIT_PASS("2","审核通过"),
    REJECT("3","驳回"),
    SHIELD("4","屏蔽");

    private String code;
    private String value;

    ItemStautsEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
