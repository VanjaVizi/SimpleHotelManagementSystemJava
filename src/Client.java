
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client {
    
    MY_CONNECTION myConn= new MY_CONNECTION();
    //function adds a clientt
    public boolean addClient(String fname, String lname, String phone, String email){
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `clients`(`firstName`, `lastName`, `phone`, `email`) VALUES (?,?,?,?)";
        
        try {
            st=myConn.createConnection().prepareStatement(addQuery);
            
            st.setString(1, fname);
            st.setString(2,lname);
            st.setString(3,phone);
            st.setString(4,email);
            
           return (st.executeUpdate()>0);
        
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // function edits selected client
    public boolean editClient(int id, String fname, String lname, String phone, String email){
        PreparedStatement st;

        String editQuery="UPDATE `clients` SET `firstName`=?,`lastName`=?,`phone`=?,`email`=? WHERE `id`=?";
        
        try {
            st=myConn.createConnection().prepareStatement(editQuery);
            
            st.setString(1, fname);
            st.setString(2,lname);
            st.setString(3,phone);
            st.setString(4,email);
            st.setInt(5,id);
            return (st.executeUpdate()>0);
        
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    //function removes  selected client
    public boolean removeClient(int id){
        PreparedStatement st;
        String deleteQuery="DELETE FROM `clients` WHERE `id`=?";
        
        try {
            st=myConn.createConnection().prepareStatement(deleteQuery);
           
            
           st.setInt(1,id);
           return (st.executeUpdate()>0);
        
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    //function returns a list of  all clients
    public void fillClientJTable(JTable table){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT * FROM `clients`";
        try {
            ps= myConn.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Object [] row;
            while(rs.next()){
                row = new Object[5];
                //Inicijalizovali smo na 5 jer imamo 5 polja u tabeli
                row[0]= rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
               tableModel.addRow(row);
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
}
