package com.iokays.dispatch.core.adapter.persistence.message.table;

import javax.annotation.processing.Generated;

/**
 * GroupToMessage is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class GroupToMessage {

    private String groupKey;

    private String messageId;

    private String region;

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}

