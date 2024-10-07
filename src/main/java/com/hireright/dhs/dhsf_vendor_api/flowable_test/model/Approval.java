package com.hireright.dhs.dhsf_vendor_api.flowable_test.model;


public class Approval {

    private String taskId;
    private boolean status;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
