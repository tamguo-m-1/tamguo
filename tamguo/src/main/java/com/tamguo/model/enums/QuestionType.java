package com.tamguo.model.enums;

import java.io.Serializable;
/**
 * 试题类型、、题目类型(1.单选题；2.多选题; 3.解答题)
 * 
 * @author tamguo
 *
 */
public enum QuestionType {
	
	DANXUANTI("1", "单选题"),
	DUOXUANTI("2", "多选题"),
	TIANKONGTI("3", "填空题"),
	PANDUANTI("4", "判断题"),
	WENDATI("5", "问答题");

    private String value;
    private String desc;

    QuestionType(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    public static QuestionType getQuestionType(String value) {
    	if("1".equals(value)) {
    		return DANXUANTI;
    	}else if("2".equals(value)) {
    		return DUOXUANTI;
    	}else if("3".equals(value)) {
    		return TIANKONGTI;
    	}else if("4".equals(value)) {
    		return PANDUANTI;
    	}else if("5".equals(value)) {
    		return WENDATI;
    	}
    	return null;
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
