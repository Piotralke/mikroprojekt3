package com.example.demo1;

import java.sql.*;

public class DatabaseConnection {

    static Pracownik login(String login, String password, Connection connection)  {
        try (
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM pracownicy");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                if(login.equals(rs.getString("login")) && password.equals(rs.getString("haslo")))
                {
                    return new Pracownik(rs.getString("imie"),rs.getString("nazwisko"),rs.getString("login"),rs.getString("haslo"),rs.getBoolean("isAdmin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
