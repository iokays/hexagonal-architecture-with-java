package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.groupmember.GroupMember;
import com.iokays.authorization.core.domain.groupmember.GroupMemberRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;

@DrivenAdapter
public interface GroupMemberJpaRepository extends JpaRepository<GroupMember, Long>, GroupMemberRepository {
}
