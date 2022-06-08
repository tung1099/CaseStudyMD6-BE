package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {
}
