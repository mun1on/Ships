package database;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class Driver
{
    public Driver()
    {

    }

    public boolean checkIfExist(Connection con, String db_db, String name)
    {
        try
        {
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = stmt.executeQuery("SELECT "+db_db+" FROM users");

            while (resultSet.next())
            {
                if(resultSet.getString(1).equals(name))
                {
                    JOptionPane.showMessageDialog(null,"This login exist!","ERROR!",JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
            resultSet.close();
            stmt.close();
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Couldn't find Database 'shipsdb'");
        }
        return false;
    }
    public void addUser(Connection con, String fn, String sn, String un, String up)
    {
        try
        {

            LocalDate ld = LocalDate.now();
            String mySqlResult = "INSERT INTO users (name, surname, login, password, registered) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(mySqlResult);
            ps.setString(1, fn);
            ps.setString(2, sn);
            ps.setString(3, un);
            ps.setString(4, up);
            ps.setString(5, ld.toString());

            ps.execute();
            con.close();

        } catch (SQLException e)
        {
            System.out.println(e);
            System.out.println("[addUser] - couldn't load 'shipsdb''");
        }
    }
}
