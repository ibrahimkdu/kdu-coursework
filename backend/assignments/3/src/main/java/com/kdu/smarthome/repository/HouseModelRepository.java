package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.HouseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HouseModelRepository extends CrudRepository<HouseModel, Integer> {

}
