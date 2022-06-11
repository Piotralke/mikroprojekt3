package com.example.demo1;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    static Pracownik login(String login, String password, Connection connection)  {
        try (
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM pracownicy");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                if(login.equals(rs.getString("login")) && password.equals(rs.getString("haslo")))
                {
                    return new Pracownik(rs.getInt("idPracownika"),rs.getString("imie"),rs.getString("nazwisko"),rs.getString("login"),rs.getString("haslo"),rs.getBoolean("isAdmin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static List<Pracownik> getWorkers(Connection connection){
        List<Pracownik> workers = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM pracownicy");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                    workers.add(new Pracownik(rs.getInt("idPracownika"),rs.getString("imie"),rs.getString("nazwisko"),rs.getString("login"),rs.getString("haslo"),rs.getBoolean("isAdmin")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workers;
    }

    static List<Zlecenie> getOrders(Connection connection, int id){
        List<Zlecenie> orders = new ArrayList<>();
        List<String> opisy= new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM opis");
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                opisy.add(rs.getString("opis"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM zlecenia"))
                 {
                     ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getInt("idPracownika")==id)
                    orders.add(new Zlecenie(rs.getInt("idZlecenia"),rs.getInt("idPracownika"), opisy.get(rs.getInt("idOpisu")-1),rs.getString("tytul"),rs.getString("tresc"),rs.getDate("data")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    static boolean addWorker(Connection connection,  String login, String password, String name, String surname){
        List<Pracownik> workers=getWorkers(connection);
        for(Pracownik worker : workers){
            if(login.equals(worker.getLogin())){
                return false;
            }
        }
        String query = " insert into pracownicy (imie, nazwisko, login, haslo, isAdmin)"
                + " values (?, ?, ?, ?, ?)";
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString (1, name);
            preparedStatement.setString (2, surname);
            preparedStatement.setString   (3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setInt    (5, 0);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    static boolean addOrder(Connection connection,  String description, String title, int idWorker){

        String query = " insert into zlecenia (idPracownika, idOpisu, tytul, tresc, data)"
                + " values (?, ?, ?, ?,?)";
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt (1, idWorker);
            preparedStatement.setInt (2, 1);
            preparedStatement.setString   (3, title);
            preparedStatement.setString(4, description);
            preparedStatement.setDate(5,java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static void updateOrder(Connection connection, int idZlecenia, int idOpisu){
        String query = " update zlecenia set idOpisu=?"
                + " where idZlecenia =?";
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt (1, idOpisu);
            preparedStatement.setInt (2, idZlecenia);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static boolean updateUser(Connection connection,int idPracownika, String password, String name, String surname){
        String query = " update pracownicy set haslo=?, imie=?, nazwisko=? "
                + " where idPracownika =?";
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString (1, password);
            preparedStatement.setString (2, name);
            preparedStatement.setString (3, surname);
            preparedStatement.setInt (4, idPracownika);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    static boolean deleteUser(Connection connection, int idPracownika){
        String query = " delete from pracownicy "
                + " where idPracownika =?";
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt (1, idPracownika);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
