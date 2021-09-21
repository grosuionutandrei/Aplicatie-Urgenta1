package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Alimente.Alimente;
import siit.model.Alimente.TipAliment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TipAlimentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


   public List<Alimente> getAliments(int comunaId){
       String query ="select a.id, a.tip_aliment,a.calories,a.grams,q.quantity\n" +
               " from alimente_comune ac " +
               " join comune c on ac.comune_id=c.id " +
               " join alimente_cantitate q on ac.alimente_id=q.aliment_id " +
               " join alimente a on ac.alimente_id=a.id " +
               " where c.id=? ";
       return jdbcTemplate.query(query,this::getAlimente,comunaId);

   }

   private void deleteFromAlimenteCantitate(int alimentId){
       String delete="DELETE FROM ALIMENTE_CANTITATE WHERE ALIMENT_ID=?";
       jdbcTemplate.update(delete,alimentId);
   }
   private void deleteFromAlimenteComune(int alimentId){
       String delete="DELETE FROM ALIMENTE_COMUNE WHERE ALIMENTE_ID=?";
       jdbcTemplate.update(delete,alimentId);
   }
   public void deleteAliment(int alimentId){
       deleteFromAlimenteCantitate(alimentId);
       deleteFromAlimenteComune(alimentId);
       String delete="DELETE FROM ALIMENTE WHERE ID=? ";
       jdbcTemplate.update(delete,alimentId);
   }

   public void updateAlimenteCantitate(int alimentId,int quantity){
       String update="UPDATE ALIMENTE_CANTITATE SET QUANTITY=? WHERE ALIMENT_ID=?";
       jdbcTemplate.update(update,quantity,alimentId);
   }

   public void insertNewAliment(TipAliment tipAliment,int comuneId){
       System.out.println(tipAliment);
       String insert="INSERT INTO ALIMENTE (TIP_ALIMENT,CALORIES)VALUES(?,?)";
       jdbcTemplate.update(insert,tipAliment.getTipAliment(),tipAliment.getCalories());
       insertIntoAlimenteCantitate(tipAliment.getTipAliment(),tipAliment.getQuantity());
       insertIntoAlimenteComune(comuneId,tipAliment.getTipAliment());

   }

   private void insertIntoAlimenteComune(int comuneId,String tipAliment){
       String insert="insert into alimente_comune(comune_id,alimente_id)values(?,select a.id from alimente a where tip_aliment=?)";
       jdbcTemplate.update(insert,comuneId,tipAliment);
   }
   private void insertIntoAlimenteCantitate(String tipAliment,int quantity){
       System.out.println(quantity+"quantity");
       String insert = "INSERT INTO ALIMENTE_CANTITATE (ALIMENT_ID,QUANTITY)VALUES((select a.id from alimente a where tip_aliment=?),?)";
       jdbcTemplate.update(insert,tipAliment,quantity);
   }



    private TipAliment getAlimente(ResultSet rs,int rowMapper) throws SQLException {
        TipAliment tipAliment = new TipAliment();
        tipAliment.setId(rs.getInt("id"));
        tipAliment.setTipAliment(rs.getString("tip_aliment"));
        tipAliment.setCalories(rs.getInt("calories"));
        tipAliment.setGrams(rs.getInt("grams"));
        tipAliment.setQuantity(rs.getInt("quantity"));
        return tipAliment;
    }
}
