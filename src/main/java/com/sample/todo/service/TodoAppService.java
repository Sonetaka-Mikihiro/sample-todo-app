package com.sample.todo.service;

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

    public void register(String title, String detail) {
        int nextId;
        try{
            nextId = dao.getNextId();
        }catch(NullPointerException e){//レコードが0の時にgetNextIdがNullpointerExceptionを起こすため
            nextId = 1;
        }
        dao.insert(nextId, title, detail);
    }

    public void delete(int deleteId){
        dao.delete(deleteId);
    }

    public void update(int todoId, String title, String detail) {
        dao.update(todoId, title, detail);
    }
}
