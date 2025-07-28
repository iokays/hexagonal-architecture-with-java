package com.iokays.authorization.core.adapter.domain.service;

import com.iokays.authorization.core.domain.group.Group;
import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.domain.group.GroupRepository;
import com.iokays.authorization.core.domain.groupmember.GroupMember;
import com.iokays.authorization.core.domain.groupmember.GroupMemberDomainService;
import com.iokays.authorization.core.domain.groupmember.GroupMemberRepository;
import com.iokays.authorization.core.domain.user.Username;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class GroupMemberDomainServiceImpl implements GroupMemberDomainService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public Map<GroupCode, List<String>> getGroupAuthorities(Username username) {
        if (null == username) {
            return Map.of();
        }
        final var groupIds = groupMemberRepository.findByUsername(username)
                .stream()
                .map(GroupMember::groupCode).toList();

        if (CollectionUtils.isEmpty(groupIds)) {
            return Map.of();
        }

        return CollectionUtils.emptyIfNull(groupRepository.findByGroupIdIn(groupIds))
                .stream()
                .collect(Collectors.toMap(Group::groupCode, Group::authorities));
    }

    @Override
    public void create(Username username, List<GroupCode> groupIds) {
        Validate.notNull(username, "username must not be null");

        CollectionUtils.emptyIfNull(groupIds).forEach(groupId -> {
            var groupMember = this.groupMemberRepository.findByGroupIdAndUsername(groupId, username);
            if (null == groupMember) {
                groupMember = new GroupMember(groupId, username);
                this.groupMemberRepository.save(groupMember);
            }
        });
    }

    @Override
    public void delete(Username username) {
        this.groupMemberRepository.deleteByUsername(username);
    }

}
