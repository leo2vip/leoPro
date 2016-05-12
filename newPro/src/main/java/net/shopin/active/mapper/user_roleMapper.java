package net.shopin.active.mapper;

import java.util.List;

import net.shopin.active.model.security.user_role;

public interface user_roleMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(user_role record);

    int insertSelective(user_role record);

    user_role selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(user_role record);

    int updateByPrimaryKey(user_role record);
    
    List<user_role> selectByModel(user_role userRole);
}