package data_source;

import models.Employee;

import java.sql.*;
import java.util.List;

public class DbManager {
    private String dbName;
    private String user;
    private String password;
    private int port;
    private String dbUrl;

    public DbManager(String dbName, String user, String password, int port) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.port = port;
        this.dbUrl = "jdbc:mariadb://localhost:" + this.port + "/" + this.dbName;
    }

    // create table of employees
    public void createTable(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "CREATE TABLE if not exists " + tableName + " (" +
                    "id int primary key," +
                    " first_name varchar(20)," +
                    " last_name varchar(20)," +
                    " email varchar(20)," +
                    " phone_number varchar(20)," +
                    " hire_date date," +
                    " job_id varchar(20)," +
                    " salary int" +
                    ");";
            System.out.println("QUERY: " + query + "\n");
            connection.createStatement().executeUpdate(query);
            System.out.println("Successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // insert list of employees
    public void insertEmployees(List<Employee> employeeList, String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            for (Employee employee : employeeList) {
                String query = "INSERT INTO " + tableName +
                        " (id, first_name, last_name, email, phone_number, hire_date, job_id, salary)" +
                        " VALUES (" +
                        employee.getEmployeeId() + ", " +
                        "'" + employee.getFirstName() + "', " +
                        "'" + employee.getLastName() + "', " +
                        "'" + employee.getEmail() + "', " +
                        "'" + employee.getPhoneNumber() + "', " +
                        "'" + employee.getHireDate() + "', " +
                        "'" + employee.getJobId() + "', " +
                        employee.getSalary() +
                        ");";
                System.out.println("QUERY: " + query + "\n");
                connection.createStatement().executeUpdate(query);
                System.out.println("Successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // select first_name, last_name where first_name contains 'b' or 'c'
    public void selectWithLetters(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT first_name, last_name" +
                    " FROM " + tableName +
                    " WHERE first_name LIKE '%b%'" +
                    " OR first_name LIKE '%c%';";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + "\t" + resultSetMetaData.getColumnLabel(2) + "\n");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // select first_name, last_name and hire_date where hire year is 1987
    public void selectWithYear(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT first_name, last_name, hire_date" +
                    " FROM " + tableName +
                    " WHERE hire_date >= '1987/01/01' AND hire_date <= '1987/12/31';";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + "\t" + resultSetMetaData.getColumnLabel(2) + "\t" + resultSetMetaData.getColumnLabel(3));
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getDate(3).toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // select max and min salary
    public void selectMaxAndMinSalary(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT MAX(salary)" +
                    " FROM " + tableName;
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                System.out.println("max salary is " + resultSet.getInt(1));
            }
            System.out.println("\n");
            query = "SELECT MIN(salary) " +
                    " FROM " + tableName;
            System.out.println("QUERY: " + query + "\n");
            resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                System.out.println("min salary is " + resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }

    // select all where id is even
    public void selectEven(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT id, first_name, last_name " +
                    " FROM " + tableName +
                    " WHERE (id % 2) = 0;";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + "\t" + resultSetMetaData.getColumnLabel(2) + "\t" + resultSetMetaData.getColumnLabel(3));
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }

    // select all where id is odd
    public void selectOdd(String tableName) {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            String query = "SELECT id, first_name, last_name " +
                    " FROM " + tableName +
                    " WHERE (id % 2) <> 0;";
            System.out.println("QUERY: " + query + "\n");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + "\t" + resultSetMetaData.getColumnLabel(2) + "\t" + resultSetMetaData.getColumnLabel(3));
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n");
    }
}
