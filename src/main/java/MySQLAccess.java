import java.sql.*;

public class MySQLAccess {

    public static final String COURSE_NAME = "java";

    public static void main(String[] args) {
        // create object of class so we can call non-static readDataBase() method
        MySQLAccess example = new MySQLAccess();
        try {
            example.readDataBase(COURSE_NAME, "some programming course", 60, "Software");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readDataBase(String course_name, String desc, int credits, String department) throws Exception {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(createConnectionUrl());

            resultSet = connection.createStatement().executeQuery("select * from college.courses;");
            writeResultSet(resultSet);


            preparedStatement = connection.prepareStatement("insert into  college.courses (course_name, description, credits, department) " +
                    "values (?, ?, ?, ?)");
            preparedStatement.setString(1, course_name);
            preparedStatement.setString(2, desc);
            preparedStatement.setInt(3, credits);
            preparedStatement.setString(4, department);
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("SELECT course_name, description, credits, department from college.courses");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

            //deleteCourse(connection);

            resultSet = connection.createStatement().executeQuery("select * from college.courses");
            writeMetaData(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

    }

    private void deleteCourse(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("delete from college.courses where course_name = ? ; ");
        preparedStatement.setString(1, COURSE_NAME);
        preparedStatement.executeUpdate();
    }

    private String createConnectionUrl() {
        String host = "localhost";
        String dbName = "college";
        String user = "andrea";
        String password = "andrea";
        return "jdbc:mysql://" + host + "/" + dbName + "?" + "user=" + user + "&password=" + password + "&useSSL=false&allowPublicKeyRetrieval=true";
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        System.out.println("The columns in the table are: ");
        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("---------------------------------");
            System.out.println("Course: " + resultSet.getString("course_name"));
            System.out.println("Description: " + resultSet.getString("description"));
            System.out.println("Credits: " + resultSet.getInt("credits"));
            System.out.println("Department: " + resultSet.getString("department"));
            System.out.println("---------------------------------");

        }
    }

}