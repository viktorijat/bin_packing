package com.bin.packing.repository;

import com.bin.packing.model.Activity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends ReactiveMongoRepository<Activity, String> {
}