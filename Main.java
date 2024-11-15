import config.DataBase;
import repositories.TodoListRepository;
import repositories.TodoListRepositoryDblmpl;
import repositories.TodoListRepositoryImpl;
import services.TodoListServices;
import services.TodoListServicesImpl;
import views.TodoListTerminalViews;
import views.TodoListViews;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("databaseku", "root", "", "Localhost", "3306");
        dataBase.setup();

        TodoListRepository todoListRepository = new TodoListRepositoryDblmpl(dataBase);
        TodoListServices todoListServices = new TodoListServicesImpl(todoListRepository);
        TodoListViews todoListViews = new TodoListTerminalViews(todoListServices);
        todoListViews.run();

    }
}
