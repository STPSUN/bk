package main.sun.bk.server.commentReply.service.impl;

import main.sun.bk.server.commentReply.dao.impl.CommentReplyImpl;
import main.sun.bk.server.commentReply.model.CommentReply;
import main.sun.bk.server.commentReply.service.CommentReplyService;

/**
 * Created by SUN on 2017/5/2.
 */
public class CommentReplyServiceImpl implements CommentReplyService{
    private CommentReplyImpl commentReplyImpl = new CommentReplyImpl();
    @Override
    public void addCommentReply(CommentReply commentReply)
    {
        if(commentReply != null)
        {
            commentReplyImpl.addCommentReplyDao(commentReply);
        }
    }

    @Override
    public CommentReply getCommentReplyById(int commentId)
    {
        CommentReply commentReply = commentReplyImpl.findCommentReplyById(commentId);
        if(commentReply != null)
        {
            return commentReply;
        }
        return null;
    }
}
