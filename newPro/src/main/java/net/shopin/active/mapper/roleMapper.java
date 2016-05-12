package net.shopin.active.mapper;

import java.util.List;

import net.shopin.active.model.security.role;

public interface roleMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(role record);

    int insertSelective(role record);

    role selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(role record);

    int updateByPrimaryKey(role record);
    
    List<role> selectByModel(role role);
}