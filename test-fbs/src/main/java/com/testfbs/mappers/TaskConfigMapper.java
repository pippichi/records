package com.testfbs.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.testfbs.entity.TaskConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务配置表 Mapper 接口
 * </p>
 *
 * @author qianyifeng
 * @date 2022-05-13
 */
@Mapper
public interface TaskConfigMapper extends BaseMapper<TaskConfig> {

    boolean batchSaveOrUpdate(@Param("list") List<TaskConfig> list);
}
