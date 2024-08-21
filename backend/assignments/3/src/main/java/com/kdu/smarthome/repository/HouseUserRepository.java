package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.HouseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseUserRepository extends JpaRepository<HouseUser,Integer> {
    @Query("SELECT house FROM HouseUser house WHERE house.user.username = ?1")
    List<HouseUser> findAllByUserUsername(String username);
    @Query("SELECT house FROM HouseUser house WHERE house.user.username = :username AND house.house.id = :houseID")
    HouseUser findAllByUserUsernameAndHouseId(@Param("username") String username, @Param("houseID") Integer houseID);
    @Query("SELECT house FROM HouseUser house WHERE house.house.id = :houseID")
    List<HouseUser> findAllByHouseId( @Param("houseID") Integer houseID);

}
