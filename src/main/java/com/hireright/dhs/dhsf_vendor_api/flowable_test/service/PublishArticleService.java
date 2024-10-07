package com.hireright.dhs.dhsf_vendor_api.flowable_test.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class PublishArticleService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.printf("Publishing article for author %s%n", execution.getVariable("author"));

    }
}
