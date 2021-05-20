package com.sample.todo.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * TODO_APPテーブルに該当するエンティティクラス<br>
 * JavaBeansのルールに従っています。
 */
public class TodoApp implements Serializable {
    /** おまじない */
    private static final long serialVersionUID = 1L;

    private int todoId;
    @Size(max = 30)
    private String category;
    @Size(min = 1, max = 30)
    private String title;
    @Size(max = 100)
    private String detail;
    private int deleteId;
    private int restoreId;
    private java.sql.Date deadline;
    @Pattern(regexp = "TODO_ID|CATEGORY|TITLE|DETAIL|DEADLINE")
    private String sortColumn;
    @Pattern(regexp = "ASC|DESC")
    private String sortType;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRestoreId() {
        return restoreId;
    }

    public void setRestoreId(int restoreId) {
        this.restoreId = restoreId;
    }

    public java.sql.Date getDeadline() {
        return deadline;
    }

    public void setDeadline(java.sql.Date deadline) {
        this.deadline = deadline;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
