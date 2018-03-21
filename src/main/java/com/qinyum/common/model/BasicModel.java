package com.qinyum.common.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

public class BasicModel {
	
	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 创建人
	 */
	private String create_by;

	/**
	 * 创建时间
	 */
	private Date create_time;

	/**
	 * 删除人
	 */
	private String delete_by;

	/**
	 * 删除时间
	 */
	private Date delete_time;

	/**
	 * 删除标记
	 */
	private String del_flag;

	@Transient
	private Integer page = 1;

	@Transient
	private Integer rows = 10;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getDelete_by() {
		return delete_by;
	}

	public void setDelete_by(String delete_by) {
		this.delete_by = delete_by;
	}

	public Date getDelete_time() {
		return delete_time;
	}

	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}

	@Length(min = 1, max = 1)
	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
