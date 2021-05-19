package com.sample.todo.dao;

import java.util.List;

import com.sample.todo.entity.TodoApp;
import com.sample.todo.entity.TodoAppRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * データアクセスオブジェクト（DataAccessObject=Dao）<br>
 * データアクセス関連を記述するクラス
 */
@Component
public class TodoAppDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public List<TodoApp> getTodoAppList() {
        List<TodoApp> resultList = jdbcTemplate.query("SELECT * FROM TODO_APP WHERE DELETED_FLAG = 0",
                new MapSqlParameterSource(null),
                new TodoAppRowMapper());
        return resultList;
    }

    public int getNextId() {
        int maxTodoId = jdbcTemplate.queryForObject("SELECT MAX(TODO_ID) FROM TODO_APP;",
                new MapSqlParameterSource(null), Integer.class);
        return ++maxTodoId;
    }

    public void insert(int todoId, String category, String title, String detail, java.sql.Date deadline) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("todoId", todoId);
        paramMap.addValue("category", category);
        paramMap.addValue("title", title);
        paramMap.addValue("detail", detail);
        paramMap.addValue("deadline", deadline);
        jdbcTemplate.update("INSERT INTO TODO_APP VALUES(:todoId, :category, :title, :detail, :deadline, 0)", paramMap);
    }

    public void delete(int deleteId) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("deleteId", deleteId);
        jdbcTemplate.update("UPDATE TODO_APP SET DELETED_FLAG = 1 WHERE TODO_ID = :deleteId", paramMap);
    }

    public void update(int todoId, String category, String title, String detail) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("todoId", todoId);
        paramMap.addValue("category", category);
        paramMap.addValue("title", title);
        paramMap.addValue("detail", detail);
        jdbcTemplate.update(
                "UPDATE TODO_APP SET CATEGORY = :category, TITLE = :title, DETAIL = :detail WHERE TODO_ID = :todoId",
                paramMap);
    }

    public List<TodoApp> getTrashList() {
        List<TodoApp> trashList = jdbcTemplate.query("SELECT * FROM TODO_APP WHERE DELETED_FLAG = 1",
                new MapSqlParameterSource(null),
                new TodoAppRowMapper());
        return trashList;
    }

    public void restore(int restoreId) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("restoreId", restoreId);
        jdbcTemplate.update("UPDATE TODO_APP SET DELETED_FLAG = 0 WHERE TODO_ID = :restoreId", paramMap);
    }

    public List<TodoApp> sort(String sortColumn, String sortType) {
        List<TodoApp> sortedList = jdbcTemplate.query("SELECT * FROM TODO_APP ORDER BY " + sortColumn + " " + sortType,
                new MapSqlParameterSource(null),
                new TodoAppRowMapper());
        return sortedList;
    }
}
