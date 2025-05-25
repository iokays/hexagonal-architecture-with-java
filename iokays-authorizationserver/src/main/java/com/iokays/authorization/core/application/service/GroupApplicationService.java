package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.domain.group.Group;
import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.group.GroupInfo;
import com.iokays.authorization.core.domain.group.GroupRepository;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class GroupApplicationService implements ApplicationService {

    private final GroupRepository groupRepository;

    public GroupId save(String groupName, List<String> authorities) {
        Group group = new Group(groupName, authorities);
        return groupRepository.save(group).groupId();
    }

    public GroupInfo findGroupInfo(String groupName) {
        final Group group = groupRepository.findByGroupName(groupName);
        if (null == group) {
            return null;
        }
        return group.info();
    }

    public void addAuthority(GroupId groupId, final String authority) {
        final Group group = Objects.requireNonNull(groupRepository.findByGroupId(groupId));
        group.addAuthorities(authority);
    }

    public void addMember(final GroupId groupId, final Username username) {
        final Group group = Objects.requireNonNull(groupRepository.findByGroupId(groupId));
        group.addMember(username);
    }

    public Page<GroupInfo> findAll(Pageable pageable) {
        final Page<Group> page = groupRepository.findAll(pageable);
        return Pages.toNewPage(pageable, page, Group::info);
    }

}
