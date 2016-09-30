import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DataService {

    Connection connect = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String result = "";

    public DataService() throws Exception {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/devices?"
                            + "user=root&password=root");
            statement = connect.createStatement();
    }

    public String selectAll(int offset, int range) throws Exception{
            resultSet = statement
                    .executeQuery("select * from deviceTokens where id > " + offset + " AND id < " + (range+offset));
        while (resultSet.next()) {
            result += resultSet.getString("token")+",";
        }
        return result;
    }
}