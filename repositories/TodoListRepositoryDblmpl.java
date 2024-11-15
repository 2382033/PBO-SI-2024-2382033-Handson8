package repositories;

import Entities.TodoList;
import config.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TodoListRepositoryDblmpl implements TodoListRepository {
    private final DataBase dataBase;

    public TodoListRepositoryDblmpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public TodoList[] getAll() {
        Connection connection = dataBase.getConnection();
        String sqlStatement = "SELECT * FROM todos";
        List<TodoList> todoList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoList todoList1 = new TodoList();
                int id = resultSet.getInt(1);
                String todo = resultSet.getString(2);
                todoList1.setId(id);
                todoList1.setTodo(todo);
                todoList.add(todoList1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return todoList.toArray(TodoList[]::new);
    }

    @Override
    public void add(final TodoList todoList) {
        String sqlStatement = "INSERT INTO(todo) values(?)";
        Connection conn = dataBase.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, todoList.getTodo());

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Insert successful!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean remove(final Integer id) {
        String sqlStatement = "DELETE FROM todo WHERE id = ?";
        Connection conn = dataBase.getConnection();
        var dbId = getDbId(id);
        if (dbId == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, dbId);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Delete successful!");
                return true;
            }
            return  false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Integer getDbId(final Integer id) {
        TodoList[] todoLists = getAll();
        Long countElement = Arrays.stream(todoLists).filter(Objects::nonNull).count();
        if (countElement.intValue() == 0) {
            return null;
        }
        var dbId = todoLists[id - 1].getId();
        return dbId;
    }

    @Override
    public Boolean edit(final TodoList todoList) {
        String sqlStatement = "UPDATE todo set todo = ? WHERE id = ?";
        Connection conn = dataBase.getConnection();
        var dbId = getDbId(todoList.getId());
        if (dbId == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, todoList.getTodo());
            preparedStatement.setInt(2, dbId);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Update successful!");
                return false;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}