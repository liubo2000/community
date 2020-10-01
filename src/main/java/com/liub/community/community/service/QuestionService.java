package com.liub.community.community.service;

import com.liub.community.community.dto.QuestionDTO;
import com.liub.community.community.mapper.QuestionMapper;
import com.liub.community.community.mapper.UserMapper;
import com.liub.community.community.model.Question;
import com.liub.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private  QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList= new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
            return  questionDTOList;
    }
}
