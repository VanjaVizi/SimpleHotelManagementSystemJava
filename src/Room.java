
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Room {
    MY_CONNECTION myConn = new MY_CONNECTION();
    
    //create a function to display all types in jtable
    public void fillRoomsTypeJTable(JTable table){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT * FROM `type`";
        try {
            ps= myConn.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Object [] row;
            while(rs.next()){
                row = new Object[3];
                //Inicijalizovali smo na 5 jer imamo 5 polja u tabeli
                row[0]= rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                
               tableModel.addRow(row);
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //create a function to fill comboBox with room_type id
    public void fillRoomsTypeJComboBox (JComboBox cb){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT * FROM `type`";
        try {
            ps= myConn.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                cb.addItem(rs.getInt(1));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //create a function to add a new room
    public boolean addRoom(int number, int type, String phone){
        PreparedStatement ps;
        String addQuery ="INSERT INTO `rooms`(`r_number`, `type`, `phone`, `reserved`) VALUES (?,?,?,?)";
        
        try {
            ps=myConn.createConnection().prepareStatement(addQuery);
            ps.setInt(1,number);
            ps.setInt(2,number);
            ps.setString(3, phone);
            //when we add a new room -> reserved = no
            ps.setString(4, "NO");
          
            return (ps.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
    
    //create function to edit selected room
    public boolean editRoom(int type,String phone, String reserved, int rNumber){
        
        PreparedStatement ps;
        String editQuery="UPDATE `rooms` SET `type`=?,`phone`=?,`reserved`=? WHERE `r_number`=?";
        
        try {
            ps= myConn.createConnection().prepareStatement(editQuery);
            ps.setInt(1,type);
            ps.setString(2, phone);
            ps.setString(3, reserved);
            ps.setInt(4,rNumber);
            
            return (ps.executeUpdate()>0);
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    //create a function to remove selected room
    public boolean removeRoom(int rNumber){
        PreparedStatement ps;
        String removeQuery="DELETE FROM `rooms` WHERE `r_number`=?";
        try {
            ps=myConn.createConnection().prepareStatement(removeQuery);
            ps.setInt(1,rNumber);
            
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }
    
    
    //create a function to list all rooms in jTable
    public void fillRoomTable(JTable table){
        PreparedStatement ps;
        ResultSet rs;
        
        String query="SELECT * FROM `rooms`";
        
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        
        try {
            ps=myConn.createConnection().prepareStatement(query);
            rs=ps.executeQuery();
            Object [] row;
            while(rs.next()){
                row = new Object[4];
                row[0]=rs.getInt(1);
                row[1]=rs.getInt(2);
                row[2]= rs.getString(3);
                row[3]=rs.getString(4);
                
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //create a function to set a room reserved or not
    public boolean setRoomToReserved(int rNumber,String reserved){
        PreparedStatement ps;
        String editQuery="UPDATE `rooms` SET `reserved`=? WHERE `r_number`=?";
        
        try {
            ps= myConn.createConnection().prepareStatement(editQuery);
           
            ps.setString(1, reserved);
            ps.setInt(2,rNumber);
            
            return (ps.executeUpdate()>0);
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //create a function to check if room is already reserved
     public String isRoomReserved(int number){
        PreparedStatement ps;
        ResultSet rs;
        String editQuery="SELECT `reserved` FROM `rooms` WHERE `r_number`=?";
        
        try {
            ps= myConn.createConnection().prepareStatement(editQuery);
           
            ps.setInt(1,number);
            rs = ps.executeQuery();
           if(rs.next()){
               return rs.getString(1);
           }else{
               return "";
           }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    
    
    
    
    
    
    
    
    
}
