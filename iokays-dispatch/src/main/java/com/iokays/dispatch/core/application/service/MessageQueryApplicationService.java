package com.iokays.dispatch.core.application.service;

import com.iokays.common.core.service.ApplicationService;
import com.iokays.dispatch.core.adapter.persistence.message.MessageDao;
import com.iokays.dispatch.core.adapter.persistence.message.table.ChannelMessage;
import com.iokays.dispatch.core.adapter.persistence.message.table.TLocalMessage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class MessageQueryApplicationService implements ApplicationService {

    private final MessageDao messageDao;

    public Page<ChannelMessage> page(String category, Pageable pageable) {
        return messageDao.page(category, pageable);
    }

}
