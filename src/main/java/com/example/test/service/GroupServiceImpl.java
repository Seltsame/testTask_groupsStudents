package com.example.test.service;

import com.example.test.dto.GroupResponseDto;
import com.example.test.dto.request.GroupDto;
import com.example.test.model.Group;
import com.example.test.repository.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    /**
     * <b>Метод для добавления группы</b>
     *
     * @param groupDto
     */
    @Override
    public void addGroup(GroupDto groupDto) {
        groupRepository.save(getGroupFromGroupDto(groupDto));
    }

    /**
     * <b>Метод для получения груп с помощью пангинации</b>
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<GroupResponseDto> getGroups(Pageable pageable) {
        Page<Group> all = groupRepository.findAll(pageable);
        Page<GroupResponseDto> groupResponseDtoPage = new PageImpl<>(all.stream()
                .map(group -> getGroupResponseDtoFromGroup(group))
                .collect(Collectors.toList()));
        return groupResponseDtoPage;

    }


    /**
     * <b>Метод для конвертации GroupDto в Group entity</b>
     *
     * @param groupDto
     * @return
     */
    private Group getGroupFromGroupDto(GroupDto groupDto) {
        return Group.builder()
                .name(groupDto.getGroupName())
                .creationDate(LocalDateTime.now())
                .build();
    }

    /**
     * <b>Метод для конвертации entity Group в Dto для ответа GroupResponseDto</b>
     *
     * @param group
     * @return
     */
    private GroupResponseDto getGroupResponseDtoFromGroup(Group group) {
        return GroupResponseDto.builder()
                .groupName(group.getName())
                .studentQuantity(group.getStudentSet() != null ? group.getStudentSet().size() : 0)
                .build();
    }
}
