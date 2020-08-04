# PROJECT DESCRIPTION
- This is a simple project for hotel management written in NetBeans using Java (GUI) and MySQL database
- Project contains LogIn form, Client form, Room form, Main form, Reservation form and Registration form(soon to be done)
- LogIn form allows user to proceed to Main Form (when user enters correct username and password)
	- If incorrect username or password is entered error message will show (JOptionPane)
- Form MainForm user can select to which form he wants to go
- When user selects "Clients" form MainForm, ClientForm opens
	- When this form load all clients will be shown in jTable
	- In this form is possible to add new client, to delete client or to edit client's data by clicking on right button
	- Depending of whether any of the above actions if succesfully completed or not, the message will be shown
- When user selects "Rooms" from menu in MainForm, RoomForm opens
	- In this foorm user can add, edit or delete room
	- This form shows all rooms from database
	- It has a button "Room Types" which (when clicked) opens new TypesForm in which are listed all room types
- When user selects "Reservation" from menu in MainForm, ReservationForm opens
	- In this form user can add, edit or remove reservation
	- Once reservation is added, room is no longer available (it is reserved)
	- Once reservation is deleted, room is no longer reserved 
	- It is not possible to choose date before today
				 
				 
*********************************************************************************************************************************************************************
# TOOLS USED FOR THIS PROJECT  
 - Java (GUI)
 - NetBeans IDE 8.2
 - MySQL database
 - phpMyAdmin
 - xampp
 
 
 *********************************************************************************************************************************************************************
# DATABASE DESCRIPTION
- Database contains 6 tables:
	- table 'users' : stores user's username and password
	- table 'client' : stores client's data such as first name, last name, phone, email
	- table 'room_types' : stores hotel's room types such as single, double, triple, family, suite
	- table 'rooms' : stores room's data such as room number, phone and availability (is it reserved or not)
	- table 'reservation' : stores reservation data (room number, client id, date in - date from which is room reserved, date out - date to which is room reserved)
	- table 'registration' : soon to be done


*********************************************************************************************************************************************************************
# WHAT NEEDS TO BE DONE
- [ ] change colors in some forms
- [ ] create registration form
- [ ] create registration table





