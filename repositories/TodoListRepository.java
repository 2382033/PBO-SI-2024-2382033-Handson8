package repositories;

import Entities.TodoList;

public interface TodoListRepository {
    TodoList[] getAll();
    void add(TodoList todoList);
    Boolean remove(Integer id);
    Boolean edit(TodoList todoList);

}
