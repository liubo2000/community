package com.liub.community.community.mapper;

import com.liub.community.community.dto.QuestionDTO;
import com.liub.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(title,description,gm" +
            "t_Create,gmt_modified,creator)  values (#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creator})")
    public void create(Question question);

@Select("select * from question ")
    List<Question> list();
}
