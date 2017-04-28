package main.sun.bk.server.daily.service.impl;

import main.sun.bk.server.daily.dao.Impl.DailyImpl;
import main.sun.bk.server.daily.model.Daily;
import main.sun.bk.server.daily.service.DailyService;

import java.util.List;

/**
 * Created by SUN on 2017/4/28.
 */
public class DailyServiceImpl implements DailyService{
    DailyImpl dailyImpl = new DailyImpl();
    @Override
    public void addDaily(Daily daily)
    {
        dailyImpl.addDeily(daily);
    }

    @Override
    public List<Daily> getAllDaily()
    {
        List<Daily> dailyList = dailyImpl.getAllDaily();
        return dailyList;
    }
}
