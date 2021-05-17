package com.sample.todo.entity;

import java.io.Serializable;

import javax.validation.constraints.Size;

/**
 * TODO_APPテーブルに該当するエンティティクラス<br>
 * JavaBeansのルールに従っています。
 */
public class TodoApp implements Serializable {
    /**
     * おまじない
     */
    private static final long serialVersionUID = 1L;

    private int todoId;
    @Size(min=1, max=30)
    private String title;
    @Size(max=100)
    private String detail;
    private int deleteId;

    public TodoApp() {
    }

    public int getTodoId() {
        return this.todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDeleteId(int deleteId) {
        this.deleteId = deleteId;
    }

    public int getDeleteId() {
        return deleteId;
    }
}
