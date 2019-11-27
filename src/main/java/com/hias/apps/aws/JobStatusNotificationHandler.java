package com.hias.apps.aws;

public interface JobStatusNotificationHandler {

    public void handle(JobStatusNotification jobStatusNotification);
}
