package com.example.demo.repository;

import com.example.demo.entities.RequestToService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EWallServiceRepository extends CrudRepository<RequestToService,Long> {
    RequestToService findOneByObjectId(Long id);
}
