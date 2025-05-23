package com.iokays.authorization.core.adapter.domain.service;

import com.iokays.authorization.core.domain.group.Group;
import com.iokays.authorization.core.domain.group.GroupRepository;
import com.iokays.authorization.core.domain.user.GroupDomainService;
import com.iokays.authorization.core.domain.user.Username;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GroupDomainServiceImpl implements GroupDomainService {

    private final GroupRepository groupRepository;

    @Override
    public List<String> getGroupAuthorities(Username username) {
        if (null == username) {
            return List.of();
        }
        final List<Group> groups = groupRepository.findByMembersUsername(username);
        return CollectionUtils.emptyIfNull(groups)
                .stream()
                .flatMap(v -> v.authorities().stream())
                .distinct()
                .toList();
    }
}
