
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vanja
 */
public class Reservation {
    
    // in reservation table we need to add two forgein keys:
        //one for the client ->   alter TABLE reservations ADD CONSTRAINT fk_client_id FOREIGN KEY (clientID) REFERENCES clients(id) on DELETE CASCADE
        //one for room -> alter TABLE reservations ADD CONSTRAINT fk_roomNumber FOREIGN KEY (roomNumber) REFERENCES rooms(r_number) on DELETE CASCADE
    
    
    
    //add another foreign key between tables types and rooms
        // -> alter TABLE rooms ADD CONSTRAINT fk_type_id FOREIGN KEY (type) REFERENCES type(id) on DELETE CASCADE
    
     MY_CONNECTION myConn= new MY_CONNECTION();
     Room room = new Room();
    //function adds a client
    public boolean addReservation(int clientID, int roomNumber, String dateIn, String dateOut){
        PreparedStatement st;
        String addQuery="INSERT INTO `reservations`(`clientID`, `roomNumber`, `dateIn`, `dateOut`) VALUES (?,?,?,?)";
        
        try {
            st=myConn.createConnection().prepareStatement(addQuery);
            st.setInt(1, clientID);
            st.setInt(2,roomNumber);
            st.setString(3, dateIn);
            st.setString(4, dateOut);
            
            
            //when we add new reservation, room must be reserved
            
           if(room.isRoomReserved(roomNumber).equals("NO")){
               if(st.executeUpdate()>0){
                    room.setRoomToReserved(roomNumber, "YES");
                    return true;
                }else{
               
                    return false;
                }
           }else{
               JOptionPane.showMessageDialog(null, "This room is already reserved", "Room reserved", JOptionPane.ERROR_MESSAGE);
               return false;
           }
        
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // function edits selected client
    public boolean editReservation(int id, int clientID, int roomNumber,String dateIn, String dateOut){
        PreparedStatement st;

        String editQuery="UPDATE `reservations` SET`clientID`=?,`roomNumber`=?,`dateIn`=?,`dateOut`=? WHERE `id`=?";
        
        try {
            st=myConn.createConnection().prepareStatement(editQuery);
            
            st.setInt(5, id);
            st.setInt(1, clientID);
            st.setInt(2, roomNumber);
            st.setString(3, dateIn);
            st.setString(4, dateOut);
            
            
            
            
            return (st.executeUpdate()>0);
        
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    //function removes  selected client
    public boolean removeReservation(int id){
        PreparedStatement st;
        String deleteQuery="DELETE FROM `reservations` WHERE `id`=?";
        
        try {
            st=myConn.createConnection().prepareStatement(deleteQuery);
             st.setInt(1,id);
             //we need to get a room number before we delete reservation
             int rumNum=getRoomNumberFromReservation(id);
             
           //when we delete reservation room is not longer reserved
           if(st.executeUpdate()>0){
               room.setRoomToReserved(rumNum, "NO");
               return true;
           }else{
               return false;
           }
        
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    //function returns a list of  all clients
    public void fillReservationJTable(JTable table){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT * FROM `reservations`";
        try {
            ps= myConn.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Object [] row;
            while(rs.next()){
                row = new Object[5];
                //Inicijalizovali smo na 5 jer imamo 5 polja u tabeli
                row[0]= rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
               tableModel.addRow(row);
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //create a function to get room number from reservation
    public int getRoomNumberFromReservation(int reservationID){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT  `roomNumber`FROM `reservations` WHERE `id`=?";
        try {
            ps= myConn.createConnection().prepareStatement(selectQuery);
            ps.setInt(1, reservationID);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }else{
                return 0;
            }
            
                
               
           
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    
    
}
