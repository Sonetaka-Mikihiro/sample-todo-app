package com.sample.todo.controller;

import java.util.List;

import com.sample.todo.entity.TodoApp;
import com.sample.todo.service.TodoAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ブラウザからのリクエストはここにくる
 */
@Controller
public class TodoAppController {

    @Autowired
    private TodoAppService service;

    /**
     * valueの部分がURL<br>
     * POSTを許可しているのは、{@code #register(TodoApp, Model)} からredirectしてくるため
     */
    @RequestMapping(value = { "/", "index" }, method = { RequestMethod.GET, RequestMethod.POST })
    String index(Model model) {
        List<TodoApp> todoList = service.getTodoAppList();
        model.addAttribute("todoList", todoList);// ここの"todoList"というキーがindex.htmlで参照されている
        return "index";// resources/index.htmlを指している
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    String add(@ModelAttribute TodoApp todoApp) {
        return "detail";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    String register(@ModelAttribute @Validated TodoApp todoApp, BindingResult result, Model model) {
        if (result.hasErrors()) { //入力チェック
            return "detail";
          }
        service.register(todoApp.getCategory(),todoApp.getTitle(), todoApp.getDetail());
        return "redirect:index";// 登録したらindexに移る
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST )
    String delete(@ModelAttribute TodoApp todoApp, Model model) {
        service.delete(todoApp.getDeleteId());
        return "redirect:index";// resources/index.htmlを指している
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    String edit(@ModelAttribute TodoApp todoApp, Model model) {
        /**
         * html側の記述方法を変えることでaddAttributeは不要になる。
         * 完全には理解してないのでコメントアウトで一旦残しておく
        model.addAttribute("todoId", todoApp.getTodoId());
        model.addAttribute("title", todoApp.getTitle());
        model.addAttribute("detail", todoApp.getDetail());
        */
        return "edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    String update(@ModelAttribute @Validated TodoApp todoApp, BindingResult result, Model model) {
        if (result.hasErrors()) { //入力チェック
            return "edit";
          }
        service.update(todoApp.getTodoId(),todoApp.getCategory(), todoApp.getTitle(), todoApp.getDetail());
        return "redirect:index";// 登録したらindexに移る
    }

    @RequestMapping(value =  "/trashbox", method = { RequestMethod.GET, RequestMethod.POST })
    String trashBox(Model model) {
        List<TodoApp> trashList = service.getTrashList();
        model.addAttribute("trashList", trashList);// ここの"todoList"というキーがindex.htmlで参照されている
        return "trashbox";// resources/index.htmlを指している
    }

    @RequestMapping(value = "/restore", method = RequestMethod.POST )
    String restore(@ModelAttribute TodoApp todoApp, Model model) {
        service.restore(todoApp.getDeleteId());
        return "redirect:index";// resources/index.htmlを指している
    }
}
