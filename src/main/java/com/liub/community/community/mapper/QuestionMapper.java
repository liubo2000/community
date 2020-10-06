package com.liub.community.community.mapper;

import com.liub.community.community.dto.QuestionDTO;
import com.liub.community.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(title,description,gm" +
            "t_Create,gmt_modified,creator)  values (#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creator})")
    public void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    @Results({
            @Result(column = "GMT_CREATE", property = "gmt_Create", jdbcType = JdbcType.BIGINT),
            @Result(column = "GMT_MODIFIED", property = "gmt_Modified", jdbcType = JdbcType.BIGINT)
    })
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId")Integer userId);
}
