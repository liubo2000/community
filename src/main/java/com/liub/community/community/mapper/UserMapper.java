package com.liub.community.community.mapper;

import com.liub.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO  USER(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED) VALUES(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    public void  insert(User user);
    @Select("SELECT * FROM USER WHERE TOKEN=#{token}")
    User selectByToken(@Param("token") String token);
}
