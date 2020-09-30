package com.example.test.service;

import com.example.test.dto.request.GroupDto;
import com.example.test.dto.GroupResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    /**<b>Метод для добавления группы</b>
     * @param groupDto
     */
    void addGroup(GroupDto groupDto);

    Page<GroupResponseDto> getGroups(Pageable pageable);
}

