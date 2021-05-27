package ru.nata.diploma.cookies.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InstanceResult {
    private String id;
    private String businessKey;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String processDefinitionName;
    private Integer processDefinitionVersion;
    private String startTime;
    private String endTime;
    private String removalTime;
    private Number durationInMillis;
    private String startUserId;
    private String startActivityId;
    private String deleteReason;
    private String rootProcessInstanceId;
    private String superProcessInstanceId;
    private String superCaseInstanceId;
    private String caseInstanceId;
    private String tenantId;
    private String state;
}
