package ru.mitroshkinam.journalLpv.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ru.mitroshkinam.journalLpv.model.VP;

@ManagedBean
@ApplicationScoped
public class Service {
    
    public List<VP> searchVP(){
        List<VP> result = new ArrayList<VP>();
        MySqlStorage mySqlStorage = new MySqlStorage();
        Connection connection = mySqlStorage.getConnection();
        try (Statement statement = connection.createStatement();) {
            String sql = "SELECT pkvp, name, regNumber, formVP FROM vp";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                do {
                    VP vp = new VP();
                    vp.setPkVP(rs.getInt("pkvp"));
                    vp.setName(rs.getString("name"));
                    vp.setRegNumber(rs.getString("regNumber"));
                    vp.setFormVP(rs.getInt("formVP"));
                    result.add(vp);
                } while(rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
