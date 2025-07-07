package com.iokays.common.integration.jdbc.store.channel;

import org.springframework.integration.jdbc.store.channel.ChannelMessageStoreQueryProvider;

public interface MyChannelMessageStoreQueryProvider extends ChannelMessageStoreQueryProvider {

    /**
     * Get the query used to retrieve a count of all messages currently persisted
     * for a channel.
     *
     * @return query string
     */
    @Override
    default String getCountAllMessagesInGroupQuery() {
        return "SELECT COUNT(MESSAGE_ID) from %PREFIX%CHANNEL_MESSAGE where GROUP_KEY=? and REGION=?";
    }

    /**
     * Query that retrieves a message for the provided message id, channel and
     * region.
     *
     * @return query string
     */
    @Override
    default String getMessageQuery() {
        return """
                SELECT MESSAGE_ID, CREATED_DATE, MESSAGE_BYTES
                from %PREFIX%CHANNEL_MESSAGE
                where MESSAGE_ID=? and GROUP_KEY=? and REGION=? and STATUS=1
                """;
    }

    /**
     * Query that retrieve a count of all messages for a region.
     *
     * @return query string
     */
    @Override
    default String getMessageCountForRegionQuery() {
        return "SELECT COUNT(MESSAGE_ID) from %PREFIX%CHANNEL_MESSAGE where REGION=? and STATUS=1";
    }

    /**
     * Query to delete a single message from the database.
     *
     * @return query string
     */
    @Override
    default String getDeleteMessageQuery() {
        return "UPDATE %PREFIX%CHANNEL_MESSAGE SET STATUS = 2 where MESSAGE_ID=? and GROUP_KEY=? and REGION=? and STATUS=1";
    }

    /**
     * Query to add a single message to the database.
     *
     * @return query string
     */
    @Override
    default String getCreateMessageQuery() {
        return """
                INSERT into %PREFIX%CHANNEL_MESSAGE(
                	MESSAGE_ID,
                	GROUP_KEY,
                	REGION,
                	CREATED_DATE,
                	MESSAGE_PRIORITY,
                	MESSAGE_BYTES,
                	STATUS)
                values (?, ?, ?, ?, ?, ?, 1)
                """;
    }
}
