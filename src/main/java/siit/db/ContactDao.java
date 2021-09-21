package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.CommuneSettlements;
import siit.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContactDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Contact> getContacts(String name) {
        String query = " select cu.id,cu.phone_number,cu.rol,cu.name,loc.localitate_name " +
                " from contacte_localitati cl " +
                " join contacte_utile cu on cu.id=cl.contact_id " +
                " join localitati loc on loc.id=cl.localitate_id " +
                " join comune c on c.id=cl.comune_id " +
                " where c.name = ? " ;

        return jdbcTemplate.query(query,this::getContact,name);
    }

    public void insertNewContact(String name ,String phoneNumber,String rol,String settlement,String commune){
        String insert = "insert into contacte_utile (phone_number,rol,name)values(?,?,?)";
        jdbcTemplate.update(insert,phoneNumber,rol,name);
        insertIntoContacteLocalitati(settlement,name,commune);
    }
    private void insertIntoContacteLocalitati(String settlement,String name, String commune){

        String insert1 ="INSERT INTO CONTACTE_LOCALITATI (LOCALITATE_ID,CONTACT_ID,COMUNE_ID)VALUES" +
                "(SELECT ID FROM LOCALITATI WHERE LOCALITATE_NAME=?" +
                ",SELECT ID FROM CONTACTE_UTILE WHERE NAME =?," +
                "SELECT ID FROM COMUNE WHERE NAME=?)";
          jdbcTemplate.update(insert1,settlement,name,commune);
    }
    public void deleteFormContacteUtile(int id){
        deletefromContacteLocalitati(id);
        String delete="DELETE FROM CONTACTE_UTILE WHERE ID=?";
        jdbcTemplate.update(delete,id);
    }
    private void deletefromContacteLocalitati(int id){
        String delete ="DELETE FROM CONTACTE_LOCALITATI WHERE CONTACT_ID =?";
        jdbcTemplate.update(delete,id);
    }



    private Contact getContact(ResultSet rs, int rowNumber) throws SQLException {
         Contact contact = new Contact();
         contact.setId(rs.getInt("id"));
         contact.setName(rs.getString("name"));
         contact.setPhoneNumber(rs.getString("phone_number"));
         contact.setRol(rs.getString("rol"));
         contact.setLocalitate(rs.getString("localitate_name"));
         return contact;
    }
}
