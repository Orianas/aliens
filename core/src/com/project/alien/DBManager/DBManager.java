package com.project.alien.DBManager;

import javax.xml.transform.Result;
import java.sql.*;

//import static com.project.alien.utils.Consts.PASSWORD;
import static com.project.alien.utils.Consts.URL;
//import static com.project.alien.utils.Consts.USERNAME;

import java.sql.*;

/**
 * Created by cade on 4/25/17.
 */
public class DBManager {

    private static final String userTable   = "User";
    private static final String keyUserName = "UserName";
    //private static final String keyUserID = "UserID";
    private static final String keyHighScore   = "HighScore";
    private static final String keyZombiesKilled   = "ZombieKills";



    public DBManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection db;
            db = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void getUsers(){
        String query = "SELECT * " +
                "       FROM " + userTable +
                        " ORDER BY " + keyHighScore +
                        " DESC LIMIT 10;";
        try {
            Connection db = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement;
            preparedStatement = db.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //int userID = rs.getInt(keyUserID);
                String userName = rs.getString(keyUserName);
                int highScore = rs.getInt(keyHighScore);
                int zombieKills = rs.getInt(keyZombiesKilled);

                //System.out.println("UserID : " + userID);
                System.out.println("UserName : " + userName);
                System.out.println("HighScore: " + highScore);
                System.out.println("ZombieKills: " + zombieKills);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public boolean checkIfUserExists(String userName){
//        String query = "S"
//    }

    public void addToUsers(String userName, int highScore, int zombieKills){
        try {
            Connection db = DriverManager.getConnection(URL);
            String query = "INSERT INTO " + userTable + "(" + keyUserName +  ", "
                                                            + keyHighScore + ", "
                                                            + keyZombiesKilled +") VALUES( ?, ?, ? )";
            PreparedStatement prepStatement = db.prepareStatement(query);
            prepStatement.setString(1, userName);
            prepStatement.setInt(2, highScore);
            prepStatement.setInt(3, zombieKills);
            prepStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromUsers(String userName){
        try {
            Connection db = DriverManager.getConnection(URL);
            String query = "DELETE FROM " + userTable + " WHERE " + keyUserName + " = ?;";
            PreparedStatement prepStatement = db.prepareStatement(query);
            prepStatement.setString(1, userName);
            prepStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


}
