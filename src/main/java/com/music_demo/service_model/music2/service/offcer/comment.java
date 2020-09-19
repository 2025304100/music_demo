package com.music_demo.service_model.music2.service.offcer;

import com.music_demo.entity.Comment;

import java.util.List;

public interface comment {
    public List<Comment> getComment(Long id);

    public boolean setComment(Comment com);
}
