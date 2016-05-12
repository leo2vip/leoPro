package net.shopin.active.mapper;

import java.util.List;

import net.shopin.active.model.security.user;

public interface userMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
    
    List<user> selectByModel(user user);
}