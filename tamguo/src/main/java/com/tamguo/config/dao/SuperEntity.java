package com.tamguo.config.dao;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * 实体父类
 */
public class SuperEntity<T extends Model<?>> extends Model<T> {

	private static final long serialVersionUID = 1L;

    @TableId("uid")
    private String uid;

    @Override
    protected Serializable pkVal() {
        return this.getUid();
    }

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
