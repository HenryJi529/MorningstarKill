package com.morningstar.kill.mapper;

import com.morningstar.kill.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
* @author henry529
* @description 针对表【user】的数据库操作Mapper
* @Entity com.morningstar.pojo.entity.User
*/
public interface UserMapper {

    User selectByUsername(@Param("username") String username);

}
