package main.sun.bk.server.comment.service.impl;

import main.sun.bk.server.comment.dao.impl.CommentImpl;
import main.sun.bk.server.comment.model.Comment;
import main.sun.bk.server.comment.service.CommentService;

import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
public class CommentServiceImpl implements CommentService{
    private CommentImpl commentImpl = new CommentImpl();
    @Override
    public void addComment(Comment comment)
    {
        if(comment != null)
        {
            commentImpl.addComment(comment);
        }
    }

    @Override
    public List<Comment> findCommentByEssayId(int essayId) {
        List<Comment> commentList = commentImpl.findCommentByEssayId(essayId);
        return commentList;
    }

    @Override
    public void deleteCommentById(int commentId) {
        commentImpl.deleteCommentById(commentId);
    }
}
