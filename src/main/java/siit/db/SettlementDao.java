package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Settlement;
import siit.model.Settlementview.UserSettlementView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SettlementDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Settlement> dataViewObjectSettlement(String commune){
        String query  = " select l.* from comune_localitati cl " +
                " join localitati  l on l.id = cl.localitate_id join comune c on c.id = cl.comune_id " +
                " where c.name =? "  ;

        return jdbcTemplate.query(query,this::dataObjectSettlemnet,commune);
    }

    public List<UserSettlementView> dataUserSettlementView (){
        String query = "select  c.*,l.localitate_name,l.id as settlement_id " +
                "from comune_localitati cl " +
                "join localitati l on  cl.localitate_id=l.id " +
                "join comune c on c.id=cl.comune_id ";
        return jdbcTemplate.query(query,this::getUserSettlementView);
    }

    public void insertNewSettlement(String localitate,String comuna){
        String insert="insert into localitati (localitate_name)values(?)";
        jdbcTemplate.update(insert,localitate);
        insertIntoComune_Localitati(comuna,localitate);
    }
    private void insertIntoComune_Localitati(String comune,String localitate){
        String insert="INSERT INTO COMUNE_LOCALITATI(COMUNE_ID,LOCALITATE_ID)VALUES(SELECT ID FROM COMUNE WHERE NAME=?,SELECT ID FROM LOCALITATI WHERE LOCALITATE_NAME=?)";
        jdbcTemplate.update(insert,comune,localitate);
    }

    private Settlement dataObjectSettlemnet(ResultSet rs, int rownum) throws SQLException {
        Settlement settlement = new Settlement();
        settlement.setId(rs.getInt("id"));
        settlement.setName(rs.getString("localitate_name"));
        settlement.setLatitude(rs.getFloat("lat"));
        settlement.setLongitude(rs.getFloat("long"));
        return settlement;
    }

    private UserSettlementView getUserSettlementView(ResultSet rs,int rowNr) throws SQLException {
        UserSettlementView userSettlementView = new UserSettlementView();
        userSettlementView.setIdSettlement(rs.getInt("settlement_id"));
        userSettlementView.setSettlement(rs.getString("localitate_name"));
        userSettlementView.setIdCommune(rs.getInt("id"));
        userSettlementView.setCommune(rs.getString("name"));
        return userSettlementView;
    }
}
