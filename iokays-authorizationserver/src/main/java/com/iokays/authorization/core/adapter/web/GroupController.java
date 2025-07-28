package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.model.EditGroupModel;
import com.iokays.authorization.core.adapter.web.model.PageGroupModel;
import com.iokays.authorization.core.adapter.web.model.SaveGroupModel;
import com.iokays.authorization.core.application.service.GroupApplicationService;
import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.adapter.DriverAdapter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
                        .groupId(v.groupId().code())
                        .groupName(v.groupName())
                        .authorities(v.authorities())
                        .createdDate(v.createdDate())
                        .build());
    }

    @PostMapping
    public void save(@RequestBody SaveGroupModel model) {
        groupApplicationService.save(model.groupName(), model.authorities());
    }

    @PutMapping
    public void editaAuthorities(@RequestBody EditGroupModel model) {
        groupApplicationService.editAuthority(GroupCode.of(model.groupId()), model.authorities());
    }

    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable("groupId") final String groupId) {
        groupApplicationService.delete(GroupCode.of(groupId));
    }

}
