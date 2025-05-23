package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.group.Group;
import com.iokays.authorization.core.domain.group.GroupRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;

@DrivenAdapter
public interface GroupJpaRepository extends JpaRepository<Group, Long>, GroupRepository {
}
