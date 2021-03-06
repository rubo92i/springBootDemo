package com.example.demo.service.impl;


import com.example.demo.model.Comment;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {



    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> getByUserId(int userId) throws NotFoundException {
        if (true){
            throw new NotFoundException("");
        }
        return commentRepository.getByUserId(userId);
    }


    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }


    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> search(Comment sample) {
        return commentRepository.findAll(Example.of(sample));
    }


}
