package com.testfbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.testfbs.entity.TaskConfig;

public interface ITaskConfigService extends IService<TaskConfig> {

    boolean saveObj(TaskConfig taskConfig);
}
