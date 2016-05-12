package net.shopin.active.mapper;

import java.util.List;

import net.shopin.active.model.security.role_privilege;

public interface role_privilegeMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(role_privilege record);

    int insertSelective(role_privilege record);

    role_privilege selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(role_privilege record);

    int updateByPrimaryKey(role_privilege record);
    
    List<role_privilege> selectByModel(role_privilege role_privilege);
}