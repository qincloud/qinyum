package com.qinyum.system.logs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qinyum.common.mapper.BaseMapper;
import com.qinyum.system.logs.model.SysLogs;

@Repository
public interface SysLogsMapper extends BaseMapper<SysLogs>{

	@Select("select * from sys_logs where del_flag='0'")
	public List<SysLogs> selectAll();
}
