package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.model.PageGroupModel;
import com.iokays.authorization.core.application.service.GroupApplicationService;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.adapter.DriverAdapter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupApplicationService groupApplicationService;

    @GetMapping
    @PreAuthorize("hasAuthority('authorization:groups:page')")
    public Page<PageGroupModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                groupApplicationService.findAll(pageable),
                v -> PageGroupModel.builder()
                        .groupId(v.groupId().id())
                        .groupName(v.groupName())
                        .authorities(v.authorities())
                        .createdDate(v.createdDate())
                        .build());
    }

}
