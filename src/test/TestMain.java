package test;

import main.sun.bk.server.common.SMSUtils;
import main.sun.bk.server.daily.model.Daily;
import main.sun.bk.server.daily.service.impl.DailyServiceImpl;
import main.sun.bk.server.essay.model.Essay;
import main.sun.bk.server.essay.service.impl.EssayServiceImpl;
import main.sun.bk.server.users.model.User;
import main.sun.bk.server.users.service.impl.UserServiceImpl;

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
        test.sendSMS();
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
        user.setAuthCode("00");
        user.setSource("1");
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
