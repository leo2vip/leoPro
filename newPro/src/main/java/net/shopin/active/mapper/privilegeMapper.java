package net.shopin.active.mapper;

import net.shopin.active.model.security.privilege;

public interface privilegeMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(privilege record);

    int insertSelective(privilege record);

    privilege selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(privilege record);

    int updateByPrimaryKey(privilege record);
}