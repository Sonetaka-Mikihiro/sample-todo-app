package com.sample.todo.service;

import java.util.Calendar;
import java.util.List;

import com.sample.todo.dao.TodoAppDao;
import com.sample.todo.entity.TodoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ロジックを記述するクラス<br>
 *
 * @Componentと書いておくと、他からはは@Autowiredと記述すれば利用できる。Spring Beanという概念。
 */
@Component
public class TodoAppService {

    /**
     * TodoAppDaoは@Componentを持っているので、@Autowiredで利用できる（裏でSpringがこっそりセットしています）
     */
    @Autowired
    private TodoAppDao dao;

    public List<TodoApp> getTodoAppList() {
        return dao.getTodoAppList();
    }

    public void register(String category, String title, String detail, java.sql.Date deadline) {
        int nextId;
        try {
            nextId = dao.getNextId();
        } catch (NullPointerException e) {// レコードが0の時にgetNextIdがNullpointerExceptionを起こすため
            nextId = 1;
        }
        dao.insert(nextId, category, title, detail, deadline);
    }

    public void delete(int deleteId) {
        dao.delete(deleteId);
    }

    public void update(int todoId, String category, String title, String detail) {
        dao.update(todoId, category, title, detail);
    }

    public List<TodoApp> getTrashList() {
        return dao.getTrashList();
    }

    public void restore(int restoreId) {
        dao.restore(restoreId);
    }

    public List<TodoApp> sort(String sortColumn, String sortType) {
        return dao.sort(sortColumn, sortType);
    }

    public String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        String todaysDate = calendarToString(cal);
        return todaysDate;

    }

    public String getNextFriday() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysByNextFriday;
        int friday = 6;
        int saturday = 7; // カレンダークラスでは曜日をintで保持しており日曜から1スタート。金曜は6,土曜は7
        int NextFriday = 13; // 上のルールに従うと翌週の金曜日は13と表せられる
        if (dayOfWeek == friday || dayOfWeek == saturday) {
            daysByNextFriday = NextFriday - dayOfWeek;
        } else {
            daysByNextFriday = friday - dayOfWeek;
        }
        cal.add(Calendar.DATE, daysByNextFriday);
        String nextFriday = calendarToString(cal);
        return nextFriday;
    }

    /**
     * HTMLで表示できるようにCalendarクラスの日付をStringクラスに変換するメソッド
     * 
     * @param cal
     * @return
     */
    public String calendarToString(Calendar cal) {
        String year = String.valueOf(cal.get(Calendar.YEAR));
        int monthInt = cal.get(Calendar.MONTH) + 1; // Calenderクラスで取得した月は1月が0,2月が1...なので1を加算
        String month;
        if (monthInt < 10) {// HTMLでは月や日付の表記が２桁表記でないと表示できない(ex.2021-5-2はだめ⇨2021-05-02と表記する)
            month = '0' + String.valueOf(monthInt);
        } else {
            month = String.valueOf(monthInt);
        }
        int dayInt = cal.get(Calendar.DATE);
        String day;
        if (dayInt < 10) {// HTMLでは月や日付の表記が２桁表記でないと表示できない(ex.2021-5-2はだめ⇨2021-05-02と表記する)
            day = '0' + String.valueOf(dayInt);
        } else {
            day = String.valueOf(dayInt);
        }

        String calenderString = year + '-' + month + "-" + day;
        return calenderString;
    }
}
