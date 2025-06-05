package com.iokays.authorization.core.adapter.domain.service;

import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.group.GroupRepository;
import com.iokays.authorization.core.domain.groupmember.GroupMember;
import com.iokays.authorization.core.domain.groupmember.GroupMemberDomainService;
import com.iokays.authorization.core.domain.groupmember.GroupMemberRepository;
import com.iokays.authorization.core.domain.user.Username;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
@AllArgsConstructor
public class GroupMemberDomainServiceImpl implements GroupMemberDomainService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public List<String> getGroupAuthorities(Username username) {
        if (null == username) {
            return List.of();
        }
        final var groupIds = groupMemberRepository.findByUsername(username)
                .stream()
                .map(GroupMember::groupId).toList();

        if (CollectionUtils.isEmpty(groupIds)) {
            return List.of();
        }

        return CollectionUtils.emptyIfNull(groupRepository.findByGroupIdIn(groupIds))
                .stream()
                .flatMap(v -> v.authorities().stream())
                .distinct()
                .toList();
    }

    @Override
    public void create(GroupId groupId, Username... usernames) {
        if (ArrayUtils.isEmpty(usernames)) {
            return;
        }

        Arrays.stream(usernames).forEach(username -> {
            var groupMember = this.groupMemberRepository.findByGroupIdAndUsername(groupId, username);
            if (null == groupMember) {
                groupMember = new GroupMember(groupId, username);
                this.groupMemberRepository.save(groupMember);
            }
        });
    }

}
