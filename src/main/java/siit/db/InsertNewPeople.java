package siit.db;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


@Repository
@Qualifier("insertNewPeople")
public class InsertNewPeople implements  InsertPeople{
    private final String URL = "jdbc:h2:file:./db/store" ;
    private final String user = "sa";
    private final String password = "";





    @Override
    public void insert() throws SQLException {
        String insert = "INSERT INTO LOCALNICI(name,state,observatii,dateofbirth,picture)VALUES(?,?,?,?,?)";
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password", password);
        Connection conn  = DriverManager.getConnection(URL,props);
        PreparedStatement pstmt = conn.prepareStatement(insert);





    }
}
