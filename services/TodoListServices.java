package services;

import Entities.TodoList;

public interface TodoListServices {
    TodoList[] getTodoList();
    void addTodoList(String todo);
    Boolean removeTodoList(Integer number);
    Boolean editTodoList(Integer number, String todo);
}
