package main.sun.bk.server.commentReply.service;

import main.sun.bk.server.commentReply.model.CommentReply;

/**
 * Created by SUN on 2017/5/2.
 */
public interface CommentReplyService {
    public void addCommentReply(CommentReply commentReply);

    public CommentReply getCommentReplyById(int commentId);
}
