package com.testfbs.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.testfbs.entity.TaskConfig;
import com.testfbs.mappers.TaskConfigMapper;
import com.testfbs.service.ITaskConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskConfigServiceImpl extends ServiceImpl<BaseMapper<TaskConfig>, TaskConfig> implements ITaskConfigService {

    private final TaskConfigMapper taskConfigMapper;

    @Override
    public boolean saveObj(TaskConfig taskConfig) {
        return taskConfigMapper.insert(taskConfig) > 0;
    }

}
