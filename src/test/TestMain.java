package test;

import main.sun.bk.server.comment.model.Comment;
import main.sun.bk.server.comment.service.impl.CommentServiceImpl;
import main.sun.bk.server.common.SMSUtils;
import main.sun.bk.server.daily.model.Daily;
import main.sun.bk.server.daily.service.impl.DailyServiceImpl;
import main.sun.bk.server.essay.model.Essay;
import main.sun.bk.server.essay.model.EssayDetail;
import main.sun.bk.server.essay.service.impl.EssayServiceImpl;
import main.sun.bk.server.msg.model.Msg;
import main.sun.bk.server.msg.service.impl.MsgServiceImpl;
import main.sun.bk.server.users.model.User;
import main.sun.bk.server.users.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SUN on 2017/4/24.
 */
public class TestMain {
//    private UserService userService;
    private UserServiceImpl userService = new UserServiceImpl();
    private EssayServiceImpl essayService = new EssayServiceImpl();
    private DailyServiceImpl dailyService = new DailyServiceImpl();
    private CommentServiceImpl commentService = new CommentServiceImpl();
    private MsgServiceImpl msgService = new MsgServiceImpl();
    public static void main(String[] args)
    {
        System.out.println("hello");
        TestMain test = new TestMain();
//        test.getUserByUserName();
//        test.addUser();
//        test.addEssay();
//        test.getAllEssayByRecommend();
//        test.getAllEssayLikeTilte();
//        test.getAllEssayByType();
//        test.getAllEssayByZanDesc();
//        test.getAllEssay();
//        test.addDaily();
//        test.getAllDaily();
//        test.sendSMS();
//        test.updateUser();
//        test.addComment();
//        test.findCommentByEssayId();
//        test.getEssayById();
//        test.getEssayDetailById();
//        test.deleteCommentById();
        test.addMsg();
    }

    public void addMsg()
    {
        Msg msg = new Msg();
        msg.setUserName("111");
        msg.setContent("good");

        msgService.addMsg(msg);
    }

    public void deleteCommentById()
    {
        commentService.deleteCommentById(6);
    }

    public void getEssayDetailById()
    {
        EssayDetail essayDetail = essayService.getEssayDetailById(7);
        JSONObject json = JSONObject.fromObject(essayDetail);
        System.out.println(json);
    }

    public void getEssayById()
    {
        Essay essay = essayService.getEssayById(6);
        System.out.println(essay.getTitle());
    }

    public void findCommentByEssayId()
    {
        List<Comment> commentList = commentService.findCommentByEssayId(2);
        for (Comment comment : commentList)
        {
            System.out.println(comment.getCommentId());
        }
    }

    public void addComment()
    {
        Comment comment = new Comment();
        comment.setEssayId(3);
        comment.setComment("苏");
        commentService.addComment(comment);
    }

    public void updatePasswordByCode()
    {
        String code = "000";
        SMSUtils.sendCheckCodeSMS(code, "18850705207");
    }

    public void updateUser()
    {
    }

    public void sendSMS()
    {
        String code = "0000";
        String mobile = "18850705207";
        SMSUtils smsUtils = new SMSUtils();
        smsUtils.sendCheckCodeSMS(code, mobile);
    }

    public void getAllDaily()
    {
        List<Daily> dailyList = dailyService.getAllDaily();
        for (Daily daily : dailyList)
        {
            System.out.println(daily.getDailyId());
        }
    }

    public void addDaily()
    {
        Daily daily = new Daily();
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        daily.setCreateTime(df.format(new Date()));
        daily.setContent("dwaaa");
        daily.setZan(4);

        dailyService.addDaily(daily);
    }

    public void getUserByUserName()
    {
        User user = new User();
        user = userService.findUserByUserName("admin");
        System.out.println("name: " + user.getUserName());
    }

    public void addUser()
    {
        User user = new User();
        user.setUserName("111");
        user.setPassword("111");
        userService.addUser(user);
    }

    public void addEssay()
    {
        Essay essay = new Essay();
        essay.setAuthor("sun");
        essay.setContent("aaa");
        essay.setTitle("qqq");
        essayService.addEssay(essay);
    }

    public void getAllEssayByRecommend()
    {
        List<Essay> essayList = essayService.getAllEssayByRecommend("1");
        for(Essay essay : essayList)
        {
            System.out.println("title: " + essay.getTitle());
        }
    }

    public void getAllEssayLikeTilte()
    {
        List<Essay> essayList = essayService.getAllEssayLikeTitle("s");
        for(Essay essay : essayList)
        {
            System.out.println("id: " + essay.getEssayId());
        }
    }

    public void getAllEssayByType()
    {
        List<Essay> essayList = essayService.getAllEssayByType("a");
        for (Essay essay : essayList)
        {
            System.out.println("type: " + essay.getEtype());
        }
    }

    public void getAllEssayByZanDesc()
    {
        List<Essay> essayList = essayService.getAllEssayByZanDesc();
        for (Essay essay : essayList)
        {
            System.out.println("zan: " + essay.getZan());
        }
    }

    public void getAllEssay()
    {
        List<Essay> essayList = essayService.getAllEssay();
        for(Essay essay : essayList)
        {
            System.out.println("id: " + essay.getEssayId());
        }
    }
}
