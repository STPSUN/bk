package main.sun.bk.server.commentReply.dao;

import main.sun.bk.server.commentReply.model.CommentReply;

/**
 * Created by SUN on 2017/5/2.
 */
public interface CommentReplyDao {
    public boolean addCommentReplyDao(CommentReply commentReply);

    public CommentReply findCommentReplyById(int commentId);
}
