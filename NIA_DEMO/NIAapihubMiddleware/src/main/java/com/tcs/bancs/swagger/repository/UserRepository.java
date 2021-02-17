package com.tcs.bancs.swagger.repository;

import java.util.List;
import com.tcs.bancs.swagger.model.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	
	//@Query("SELECT u FROM User u WHERE u.userid = :userid")
	List<User> findByUserid( String userid);

}
