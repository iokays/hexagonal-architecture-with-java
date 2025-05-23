package com.iokays.authorization.core.domain.user;

import com.iokays.common.core.service.DomainService;

import java.util.List;

public interface GroupDomainService extends DomainService {

    List<String> getGroupAuthorities(final Username username);

}
