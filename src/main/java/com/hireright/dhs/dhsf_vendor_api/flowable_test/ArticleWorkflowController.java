package com.hireright.dhs.dhsf_vendor_api.flowable_test;

import com.hireright.dhs.dhsf_vendor_api.flowable_test.model.Approval;
import com.hireright.dhs.dhsf_vendor_api.flowable_test.model.Article;
import com.hireright.dhs.dhsf_vendor_api.flowable_test.model.Author;
import com.hireright.dhs.dhsf_vendor_api.flowable_test.service.ArticleWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleWorkflowController {
    @Autowired
    private ArticleWorkflowService service;

    @PostMapping("/submit")
    public void submit(@RequestBody Article article) {
        service.startProcess(article);
    }

    @GetMapping("/tasks")
    public List<Article> getTasks() {
        return service.getTasks();
    }

    @PostMapping("/review")
    public void review(@RequestBody Approval approval) {
        service.submitReview(approval);
    }

    @PostMapping("/reject")
    public String reject(@RequestBody Author author) {
        System.out.printf("Rejection email sent to %s%n", author.toString());
        return String.format("Rejection email sent to %s", author.getAuthor());
    }
}
