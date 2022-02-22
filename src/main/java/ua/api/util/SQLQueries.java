package ua.api.util;

public class SQLQueries {
    public static final String SELECT_FROM_EMPLOYEE = "SELECT * FROM employee";
    public static final String ADD_EMPLOYEE = "INSERT INTO employee (name,salary,department_id,chief_id) " +
            "VALUES (?,?,?,?)";
    public static final String SELECT_FROM_EMPLOYEE_WHERE_ID = "SELECT * FROM employee WHERE id=?";
    public static final String UPDATE_EMPLOYEE_WHERE_ID = "UPDATE employee " +
            "SET name=?,salary=?,department_id=?,chief_id=? WHERE id=?";
    public static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id=?";

    //department table
    public static final String ADD_DEPARTMENT = "INSERT INTO department (id,name) VALUES (?,?)";
    public static final String SELECT_ALL_FROM_DEPARTMENT = "SELECT * FROM department";
    public static final String SELECT_FROM_DEPARTMENT_WHERE_ID = "SELECT * FROM department WHERE id=?";
    public static final String UPDATE_DEPARTMENT_WHERE_ID = "UPDATE department SET name=? WHERE id=?";
    public static final String DELETE_DEPARTMENT = "DELETE FROM department WHERE id=?";
}
