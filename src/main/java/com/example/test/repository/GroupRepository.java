package com.example.test.repository;

import com.example.test.model.Group;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {

}
