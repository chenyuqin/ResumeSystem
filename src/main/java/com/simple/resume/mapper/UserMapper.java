package com.simple.resume.mapper;

import com.simple.resume.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void saveUser(User user);

    User findByUserID(int userID);

    User findByUserIDandPassword(@Param("userID") int userID, @Param("password") String password);

    User findByEmail(String email);

    void updateUser(User user);


}
