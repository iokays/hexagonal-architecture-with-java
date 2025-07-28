package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.domain.group.Group;
import com.iokays.authorization.core.domain.group.GroupAuthInfo;
import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.domain.group.GroupRepository;
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

    public GroupCode save(String groupName, List<String> authorities) {
        Group group = groupRepository.findByGroupName(groupName);
        if (null == group) {
            group = new Group(groupName);
            groupRepository.save(group);
        }
        group.editAuthorities(authorities);
        return group.groupCode();
    }

    public GroupAuthInfo findGroupInfo(String groupName) {
        final Group group = groupRepository.findByGroupName(groupName);
        if (null == group) {
            return null;
        }
        return group.authInfo();
    }

    public void delete(GroupCode groupId) {
        final var group = groupRepository.findByGroupId(groupId);
        if (null == group) {
            return;
        }
        groupRepository.delete(group);

    }

    public void editAuthority(GroupCode groupId, final List<String> authority) {
        final Group group = Objects.requireNonNull(groupRepository.findByGroupId(groupId));
        group.editAuthorities(authority);
    }

    public Page<GroupAuthInfo> findAll(Pageable pageable) {
        final Page<Group> page = groupRepository.findAll(pageable);
        return Pages.toNewPage(pageable, page, Group::authInfo);
    }

}
