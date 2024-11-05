package services;

import Entities.TodoList;
import repositories.TodoListRepository;
import repositories.TodoListRepositoryImpl;

public class TodoListServicesImpl implements TodoListServices{
   private final TodoListRepository todoListRepository;

    public TodoListServicesImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public TodoList[] getTodoList() {
        return todoListRepository.getAll();
    }

    @Override
    public void addTodoList(String todo) {
        if (todo.isEmpty() || todo.isBlank()) {
            System.out.println("Masukkan todo dengan benar");
            return;
        }
        TodoList todolist = new TodoList();
        todolist.setTodo(todo);
        todoListRepository.add(todolist);
    }

    @Override
    public Boolean removeTodoList(final Integer number) {
        return todoListRepository.remove(number);
    }

    @Override
    public Boolean editTodoList(final Integer number, final String todo) {
        TodoList todoList = new TodoList();
        todoList.setTodo(todo);
        todoList.setId(number);
        return todoListRepository.edit(todoList);
    }
}
