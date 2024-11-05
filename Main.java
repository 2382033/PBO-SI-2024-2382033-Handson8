import repositories.TodoListRepository;
import repositories.TodoListRepositoryImpl;
import services.TodoListServices;
import services.TodoListServicesImpl;
import views.TodoListTerminalViews;
import views.TodoListViews;

public class Main {
    public static void main(String[] args) {
        TodoListRepository todoListRepository = new TodoListRepositoryImpl();
        TodoListServices todoListServices = new TodoListServicesImpl(todoListRepository);
        TodoListViews todoListViews = new TodoListTerminalViews(todoListServices);
        todoListViews.run();

    }
}
