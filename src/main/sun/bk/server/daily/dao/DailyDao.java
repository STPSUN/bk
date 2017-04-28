package main.sun.bk.server.daily.dao;

import main.sun.bk.server.daily.model.Daily;

import java.util.List;

/**
 * Created by SUN on 2017/4/28.
 */
public interface DailyDao {
    public boolean addDeily(Daily daily);

    public List<Daily> getAllDaily();
}
