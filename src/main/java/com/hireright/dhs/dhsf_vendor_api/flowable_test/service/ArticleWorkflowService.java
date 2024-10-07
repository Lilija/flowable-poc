package com.hireright.dhs.dhsf_vendor_api.flowable_test.service;


import com.hireright.dhs.dhsf_vendor_api.flowable_test.model.Approval;
import com.hireright.dhs.dhsf_vendor_api.flowable_test.model.Article;
import org.flowable.engine.RuntimeService;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleWorkflowService {

    private static final String processDefinitionKey = "approvalTest";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Transactional
    public void startProcess(Article article) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("author", article.getAuthor());
        variables.put("url", article.getUrl());
        var process = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        System.out.printf("Started process: %s, for author %s%n", process.getId(), article.getAuthor());
    }

    @Transactional
    public List<Article> getTasks() {
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .list();
        return tasks.stream()
                .map(task -> {
                    Map<String, Object> variables = taskService.getVariables(task.getId());
                    return new Article(task.getId(), (String) variables.get("author"), (String) variables.get("url"));
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void submitReview(Approval approval) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approval.isStatus());
        System.out.printf("Will we publish %s: %s%n", approval.getTaskId(), approval.isStatus());
        taskService.complete(approval.getTaskId(), variables);
    }
}