package com.liub.community.community.mapper;

import com.liub.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(title,description,gmt_Create,gmt_modified,creator)  values (#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creator})")
    public void create(Question question);

}
