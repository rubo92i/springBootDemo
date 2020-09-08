package com.example.demo.service;


import com.example.demo.model.Comment;
import com.example.demo.model.exceptions.NotFoundException;

import java.util.List;

public interface CommentService {

    List<Comment> getByUserId(int userId) throws NotFoundException;

    void add(Comment comment) ;

    void delete(int id) ;

    List<Comment> search(Comment sample);

}
