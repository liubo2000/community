package com.liub.community.community.service;

import com.liub.community.community.dto.PaginationDTO;
import com.liub.community.community.dto.QuestionDTO;
import com.liub.community.community.mapper.QuestionMapper;
import com.liub.community.community.mapper.UserMapper;
import com.liub.community.community.model.Question;
import com.liub.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO pagination = new PaginationDTO();
        Integer totalcount = questionMapper.count();
        pagination.setPagination(totalcount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > pagination.getTotalPage()) {
            page = pagination.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);

        return pagination;
    }
}
