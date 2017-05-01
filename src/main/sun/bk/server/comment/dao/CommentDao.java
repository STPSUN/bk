package main.sun.bk.server.comment.dao;

import main.sun.bk.server.comment.model.Comment;

import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
public interface CommentDao {
    public boolean addComment(Comment comment);

    public List<Comment> findCommentByEssayId(int essayId);

    public void deleteCommentById(int commentId);
}
