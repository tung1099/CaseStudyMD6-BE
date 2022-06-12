package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query(value = "select * from user_info where user_id = ?1", nativeQuery = true)
    UserInfo findByUserId(Long id);

    @Query(value = "select user_id from user_info where id=?1", nativeQuery = true)
    Long findUserByUserInfo(Long id);
}
