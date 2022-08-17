package com.testfbs.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * <p>
 * 任务配置表
 * </p>
 *
 * @author qianyifeng
 * @date 2022-05-13
 */
@Data
@TableName("task_config")
public class TaskConfig {

    private static final long serialVersionUID = 1L;

    private Long node;

    private String taskId;

    private String taskName;

    private Integer isMonitored;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}