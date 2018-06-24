package com.tamguo.model.enums;

import java.io.Serializable;

public enum TeacherStatus {
	
	APPLY("apply", "申请"),
	PASS("pass", "通过"),
	UNPASS("unpass", "不通过");

    private String value;
    private String desc;

    TeacherStatus(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Serializable getValue() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }
    
    @Override
    public String toString() {
    	return this.value;
    }
	
}
