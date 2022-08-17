package com.testfbs.controller;

import com.testfbs.entity.TaskConfig;
import com.testfbs.service.ITaskConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskConfig")
@RequiredArgsConstructor
public class TaskConfigController {

    private final ITaskConfigService taskConfigService;

    /**
     * <p>Title:新增</p>
     * <p>Description:</p>
     *
     * @param body 请求体(需参数校验请在实体属性上自行添加校验注解，并指定校验组)
     * @author qianyifeng
     * @date 2022-05-13
     */
    @PostMapping("add")
    public boolean add(@Validated @RequestBody TaskConfig body){
        return taskConfigService.saveObj(body);
    }
}
