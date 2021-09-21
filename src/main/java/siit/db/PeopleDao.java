package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public void insertNewPeople(PeopleDTO peopleDto) throws IOException {
        String query = "INSERT into localnici(name,state,observatii,dateofbirth,image)values(?,?,?,?,?)";
        jdbcTemplate.update(query, peopleDto.getName(), peopleDto.getState(), peopleDto.getObservatii(), peopleDto.getDate(), peopleDto.getPicture());
    }

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


    private People getPeople(ResultSet rs, int rowNr) throws SQLException {

        People people = new People();
        people.setId(rs.getInt("id"));
        people.setName(rs.getString("name"));
        people.setState(rs.getString("state"));
        people.setAge(rs.getDate("dateofbirth").toLocalDate());
        people.setObservatii(rs.getString("observatii"));
        people.setImage(rs.getString("image"));
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
