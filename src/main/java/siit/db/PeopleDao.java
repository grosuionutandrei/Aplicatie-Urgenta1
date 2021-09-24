package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.*;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.apache.commons.codec.binary.Base64;
import java.util.List;

@Repository
public class PeopleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<People> getPeoples(String name) {
        String query = " select   l.* " +
                " from localitati_localnici lc " +
                " join localitati loc on loc .id = lc.localitate_id " +
                " join localnici l on l.id = lc.localnic_id " +
                " join comune_localitati cl on loc.id = cl.localitate_id " +
                " join comune c on c.id = cl.comune_id " +
                " where c.name= ? ";
        return jdbcTemplate.query(query, this::getPeople, name);
    }


    public List<People> getPeoplesPersettlement(String localitate) {
        String query = " select l.* " +
                " from localitati_localnici ll " +
                " join localnici l on l.id=ll.localnic_id " +
                " join localitati lo on lo.id=ll.localitate_id " +
                " where lo.localitate_name= ? ";
        return jdbcTemplate.query(query, this::getPeople, localitate);
    }

    public List<IsolatedPeople> getIsolatedPeople(String comune) {
        String query = " select li.id,li.localnic_id, l.name,l.state,l.dateofbirth,l.observatii,li.map,li.mijloc_de_acces " +
                " from localnici_izolati li " +
                " left join localnici l on l.id= li.localnic_id " +
                " join localitati_localnici ll on ll.localnic_id= l.id " +
                " join localitati lc  on lc.id = ll.localitate_id " +
                " join comune_localitati cl on cl.localitate_id=lc.id " +
                " join comune c on c.id = cl.comune_id " +
                " where c.name = ?";
        ;
        return jdbcTemplate.query(query, this::getIsolatedPeopleResultSet, comune);

    }

    public List<IsolatedPeople> getIsolatedPeoplePerSettlement(String localitate) {
        String query = " select li.id,li.localnic_id, l.name,l.state,l.dateofbirth,l.observatii,li.map,li.mijloc_de_acces" +
                " from localnici_izolati li " +
                " join localnici l on l.id = li.localnic_id " +
                " join localitati_localnici llc on llc.localnic_id=l.id " +
                " join localitati loc on loc.id=llc.localitate_id " +
                " where loc.localitate_name= ? ";
        return jdbcTemplate.query(query, this::getIsolatedPeopleResultSet, localitate);
    }

    public void updateState(String name, String observatii, int id) {
        String query = "Update localnici set state= ? ,observatii = ? where id=?";
        jdbcTemplate.update(query, name, observatii, id);
    }

    public void updateState(String name, int id) {
        String query = " Update localnici set state = ? where id = ?";
        jdbcTemplate.update(query, name, id);
    }

    public void updateStateObservatiiIsolated(String name, String observatii, int id) {
        String query = "update localnici " +
                " set state= ?,observatii = ? " +
                " where id in(select localnic_id from localnici_izolati li  where li.id = ?) ";
        jdbcTemplate.update(query, name, observatii, id);
    }

    public void updateStateIsolated(String name, int id) {
        String query = "update localnici " +
                " set state= ? " +
                " where id in(select localnic_id from localnici_izolati li  where li.id = ?) ";
        jdbcTemplate.update(query, name, id);
    }


    public List<HandicapPeople> getHandicapPeople(String comune) {
        String query = " select lh.id,lh.localnic_id, l.name,l.state,l.dateofbirth,l.observatii,lh.type " +
                " from localnici_handicap lh " +
                " left join localnici l on l.id= lh.localnic_id " +
                " join localitati_localnici ll on ll.localnic_id= l.id " +
                " join localitati lc  on lc.id = ll.localitate_id " +
                " join comune_localitati cl on cl.localitate_id=lc.id " +
                " join comune c on c.id = cl.comune_id " +
                " where c.name = ? ";
        return jdbcTemplate.query(query, this::getHandicapPeopleResultSet, comune);

    }

    public void updateStateObservatiiHandicap(String name, String observatii, int id) {
        String query = "update localnici " +
                " set state= ?,observatii = ? " +
                " where id in(select localnic_id from localnici_handicap lh  where lh.id = ?) ";
        jdbcTemplate.update(query, name, observatii, id);
    }

    public void updateStateHandicap(String name, int id) {
        String query = "update localnici " +
                " set state= ? " +
                " where id in(select localnic_id from localnici_handicap lh  where lh.id = ?) ";
        jdbcTemplate.update(query, name, id);
    }

    public List<HandicapPeople> getHandicapPeoplePerSettlement(String settlement) {
        String query = " select lh.id,lh.localnic_id, l.name,l.state,l.dateofbirth,l.observatii,lh.type" +
                " from localnici_handicap lh " +
                " join localnici l on l.id = lh.localnic_id " +
                " join localitati_localnici llc on llc.localnic_id=l.id " +
                " join localitati loc on loc.id=llc.localitate_id " +
                " where loc.localitate_name= ? ";
        return jdbcTemplate.query(query, this::getHandicapPeopleResultSet, settlement);
    }

    public void updateAlarm(String state, String localitate) {
        String query = " update localnici " +
                " set state= ? " +
                " where name  in (select loc.name " +
                " from comune c " +
                " join comune_localitati cl on cl.comune_id = c.id " +
                " join localitati l on l.id=cl.localitate_id " +
                " join localitati_localnici ll on l.id=ll.localitate_id " +
                " join localnici loc on loc.id=ll.localnic_id " +
                " where c.name=? ) ";
        jdbcTemplate.update(query, state, localitate);
    }

//    public void insertNewPeople(PeopleDTO peopleDto) throws IOException {
//        String query = "INSERT into localnici(name,state,observatii,dateofbirth,image)values(?,?,?,?,?)";
//        jdbcTemplate.update(query, peopleDto.getName(), peopleDto.getState(), peopleDto.getObservatii(), peopleDto.getDate(), peopleDto.getPicture());
//    }

//    public void insertNewPeople1(PeopleDTO peopleDto) throws IOException {
//        String query = "INSERT into localnici(name,state,observatii,dateofbirth,picture)values(?,?,?,?,?)";
//
//        jdbcTemplate.update(query, peopleDto.getName(), peopleDto.getState(), peopleDto.getObservatii(), peopleDto.getDate(),peopleDto.getPicture());
//    }

    public int getAddedPeopleId(PeopleDTO people) {
        String query = "SELECT l.id from localnici l where name=?";
        return jdbcTemplate.queryForObject(query, this::getIntId, people.getName());
    }

    public void insertIntoLocalniciLocalitati(int localitateId, int localnicId) {
        String query = "INSERT into localitati_localnici (localitate_id,localnic_id)values(?,?)";
        jdbcTemplate.update(query, localitateId, localnicId);
    }

    public void insertIntoLocalniciIzolati(int id, PeopleAdapter peopleAdapter) {
        String query = "INSERT into localnici_izolati (localnic_id,map,mijloc_de_acces)values(?,?,?)";
        jdbcTemplate.update(query, id, peopleAdapter.getMap(), peopleAdapter.getMijlocDeAcces());
    }


    public void insertIntoLocalniciHandicap(int id, PeopleAdapter peopleAdapter) {
        String query = "INSERT into LOCALNICI_HANDICAP (LOCALNIC_ID,TYPE)VALUES(?,?)";
        jdbcTemplate.update(query, id, peopleAdapter.getTipHandicap());
    }

    public LocalDate getBirthDate(int id) {
        String query = "select l.dateofbirth from localnici l where id=? ";
        return jdbcTemplate.queryForObject(query, this::getDateBirth, id);
    }

    public String getSettlement(int id) {
        String query = " select l.localitate_name " +
                "from localitati l " +
                "join localitati_localnici lloc on lloc.localitate_id=l.id " +
                "join localnici loc on lloc.localnic_id=loc.id " +
                "where loc.id= ? ";
        return jdbcTemplate.queryForObject(query, this::getLocalitate, id);
    }

    public void deleteFromIsolated(int localinicId) {
        String delete = "DELETE FROM LOCALNICI_IZOLATI WHERE LOCALNIC_ID=?";
        jdbcTemplate.update(delete, localinicId);
    }

    public void deleteFromHandicap(int localnicId) {
        String delete = "DELETE FROM LOCALNICI_HANDICAP WHERE LOCALNIC_ID=?";
        jdbcTemplate.update(delete, localnicId);
    }

    public void insertIntoHandicap(int id, String type) {
        String query = "INSERT into LOCALNICI_HANDICAP (LOCALNIC_ID,TYPE)VALUES(?,?)";
        jdbcTemplate.update(query, id, type);
    }

    public void insertIntoIsolated(int id, String mijlocDeAcces) {
        String query = "INSERT into localnici_izolati (localnic_id,mijloc_de_acces)values(?,?)";
        jdbcTemplate.update(query, id, mijlocDeAcces);
    }

    public void editPeopleSettlement(int localitateId, int localnicId) {
        String edit = "UPDATE localitati_localnici set localitate_id =? where localnic_id=?";
        jdbcTemplate.update(edit, localitateId, localnicId);
    }

    public void editUpdatePeople(PeopleAdapterEdit adapterEdit) {
        String edit = "UPDATE LOCALNICI L SET NAME=?,STATE=?,OBSERVATII=?,DATEOFBIRTH=? WHERE L.ID=?";
        jdbcTemplate.update(edit, adapterEdit.getName(), adapterEdit.getState(), adapterEdit.getObservatii(), adapterEdit.getBirthDate(), adapterEdit.getId());
    }

    public void deleteFromLocalniciLocalitati(int localnicId) {
        String delete = "DELETE FROM LOCALITATI_LOCALNICI WHERE LOCALNIC_ID=?";
        jdbcTemplate.update(delete, localnicId);
    }

    public void deleteFromLocalnici(int id) {
        String delete = "DELETE FROM LOCALNICI WHERE ID=?";
        jdbcTemplate.update(delete, id);
    }

    private int getIntId(ResultSet rs, int rowNR) throws SQLException {
        return rs.getInt("id");
    }

    private LocalDate getDateBirth(ResultSet rs, int rowNr) throws SQLException {
        return rs.getDate("dateofbirth").toLocalDate();
    }

    private String getLocalitate(ResultSet rs, int rowNr) throws SQLException {
        return rs.getString("localitate_name");
    }


    private People getPeople(ResultSet rs, int rowNr) throws SQLException{

        People people = new People();
        people.setId(rs.getInt("id"));
        people.setName(rs.getString("name"));
        people.setState(rs.getString("state"));
        people.setAge(rs.getDate("dateofbirth").toLocalDate());
        people.setObservatii(rs.getString("observatii"));

       if(rs.getBlob("picture")!=null){
           Blob blob = rs.getBlob("picture");
           int length = (int)blob.length();
           byte[] arra = blob.getBytes(1,length);
           String temp = Base64.encodeBase64String(arra);
           people.setImage(temp);
       }
       else{
           String image = "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";
            people.setImage(image);
        }
        return people;
    }


    private IsolatedPeople getIsolatedPeopleResultSet(ResultSet rs, int rowNr) throws SQLException {
        IsolatedPeople isolatedPeople = new IsolatedPeople();
        isolatedPeople.setId(rs.getInt("id"));
        isolatedPeople.setName(rs.getString("name"));
        isolatedPeople.setState(rs.getString("state"));
        isolatedPeople.setAge(rs.getDate("dateofbirth").toLocalDate());
        isolatedPeople.setObservatii(rs.getString("observatii"));
        isolatedPeople.setMap(rs.getString("map"));
        isolatedPeople.setMijlocDeAcces(rs.getString("mijloc_de_acces"));
        isolatedPeople.setLocalnicId(rs.getInt("localnic_id"));
        return isolatedPeople;
    }

    private HandicapPeople getHandicapPeopleResultSet(ResultSet rs, int rowNr) throws SQLException {
        HandicapPeople handicapPeople = new HandicapPeople();
        handicapPeople.setId(rs.getInt("id"));
        handicapPeople.setName(rs.getString("name"));
        handicapPeople.setState(rs.getString("state"));
        handicapPeople.setAge(rs.getDate("dateofbirth").toLocalDate());
        handicapPeople.setObservatii(rs.getString("observatii"));
        handicapPeople.setType(rs.getString("type"));
        handicapPeople.setLocalnicId(rs.getInt("localnic_id"));
        return handicapPeople;
    }
}
