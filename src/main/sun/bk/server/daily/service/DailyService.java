package main.sun.bk.server.daily.service;

import main.sun.bk.server.daily.model.Daily;

import java.util.List;

/**
 * Created by SUN on 2017/4/28.
 */
public interface DailyService {
    public void addDaily(Daily daily);

    public List<Daily> getAllDaily();
}
