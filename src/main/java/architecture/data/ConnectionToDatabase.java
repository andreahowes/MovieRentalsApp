package architecture.data;

public class ConnectionToDatabase {
    public static String createConnectionUrl() {
        String host = "localhost";
        String dbName = "blockbuster";
        String user = "andrea";
        String password = "andrea";
        return "jdbc:mysql://" + host + "/" + dbName + "?" + "user=" + user + "&password=" + password + "&useSSL=false&allowPublicKeyRetrieval=true";
    }
}
