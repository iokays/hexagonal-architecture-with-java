package com.iokays.common.integration.jdbc.store.channel;

public class MyPostgresChannelMessageStoreQueryProvider implements MyChannelMessageStoreQueryProvider {

    @Override
    public String getPollFromGroupExcludeIdsQuery() {
        return """
                   update %PREFIX%CHANNEL_MESSAGE
                   set STATUS = 0
                where CTID = (select CTID
                				from %PREFIX%CHANNEL_MESSAGE
                				where %PREFIX%CHANNEL_MESSAGE.GROUP_KEY = :group_key
                				and %PREFIX%CHANNEL_MESSAGE.REGION = :region
                				and %PREFIX%CHANNEL_MESSAGE.MESSAGE_ID not in (:message_ids)
                				and %PREFIX%CHANNEL_MESSAGE.STATUS = 1
                			order by CREATED_DATE, MESSAGE_SEQUENCE
                			limit 1 for update skip locked)
                returning MESSAGE_ID, MESSAGE_BYTES;
                """;
    }

    @Override
    public String getPollFromGroupQuery() {
        return """
                update %PREFIX%CHANNEL_MESSAGE
                   set STATUS = 0
                where CTID = (select CTID
                				from %PREFIX%CHANNEL_MESSAGE
                				where %PREFIX%CHANNEL_MESSAGE.GROUP_KEY = :group_key
                				and %PREFIX%CHANNEL_MESSAGE.REGION = :region
                				and %PREFIX%CHANNEL_MESSAGE.STATUS = 1
                			order by CREATED_DATE, MESSAGE_SEQUENCE
                			limit 1 for update skip locked)
                returning MESSAGE_ID, MESSAGE_BYTES;
                """;
    }

    @Override
    public String getPriorityPollFromGroupExcludeIdsQuery() {
        return """
                update %PREFIX%CHANNEL_MESSAGE
                   set STATUS = 0
                where CTID = (select CTID
                				from %PREFIX%CHANNEL_MESSAGE
                				where %PREFIX%CHANNEL_MESSAGE.GROUP_KEY = :group_key
                				and %PREFIX%CHANNEL_MESSAGE.REGION = :region
                				and %PREFIX%CHANNEL_MESSAGE.MESSAGE_ID not in (:message_ids)
                				and %PREFIX%CHANNEL_MESSAGE.STATUS = 1
                			order by MESSAGE_PRIORITY DESC NULLS LAST, CREATED_DATE, MESSAGE_SEQUENCE
                			limit 1 for update skip locked)
                returning MESSAGE_ID, MESSAGE_BYTES;
                """;
    }

    @Override
    public String getPriorityPollFromGroupQuery() {
        return """
                update %PREFIX%CHANNEL_MESSAGE
                   set STATUS = 0
                where CTID = (select CTID
                				from %PREFIX%CHANNEL_MESSAGE
                				where %PREFIX%CHANNEL_MESSAGE.GROUP_KEY = :group_key
                				and %PREFIX%CHANNEL_MESSAGE.REGION = :region
                				and %PREFIX%CHANNEL_MESSAGE.STATUS = 1
                			order by MESSAGE_PRIORITY DESC NULLS LAST, CREATED_DATE, MESSAGE_SEQUENCE
                			limit 1 for update skip locked)
                returning MESSAGE_ID, MESSAGE_BYTES;
                """;
    }

    @Override
    public boolean isSingleStatementForPoll() {
        return true;
    }

}
