/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import core.ConnectDb;
import entity.Position;
import entity.Uav;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onurkuran
 */
public class UavDao implements UavDaoInterface {

    private final Connection connection;
    private final PositionDao positionDao;

    public UavDao() {
        this.connection = ConnectDb.getInstance();
        this.positionDao = new PositionDao();
    }

    @Override
    public List<Uav> getAll() {
        List<Uav> userList = new ArrayList<>();

        String query = "SELECT u.id, u.code,u.speed,u.battery_percantage,u.flight_started_datetime, u.flight_finished_datetime,p.id AS position_id, p.longitude, p.latitude, p.altitude "
                + "FROM uavs u "
                + "JOIN position p ON u.position_id = p.id";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            //stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Uav uav = resultSetToModel(rs);
                    userList.add(uav);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void save(Uav uav) {
        try {
            this.positionDao.save(uav.getGeoPosition());

            String query = "INSERT INTO uavs (code, speed, battery_percantage, position_id, flight_started_datetime) VALUES (?,?,?,?,?)";
            try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
                stmt.setString(1, uav.getCode());
                stmt.setDouble(2, uav.getSpeed());
                stmt.setDouble(3, uav.getBatteryPercentage());
                stmt.setInt(4, uav.getGeoPosition().getId());
                stmt.setObject(5, uav.getFlightStartedTime());
                stmt.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Uav getById(int id
    ) {
        Uav uav = null;

        String sql = "SELECT u.id, u.code,u.speed,u.battery_percantage,u.flight_started_datetime,p.id AS position_id, p.longitude, p.latitude, p.altitude "
                + "FROM uavs u "
                + "JOIN position p ON u.position_id = p.id "
                + "WHERE u.id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    uav = resultSetToModel(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uav;
    }

    @Override
    public void update(Uav uav) throws SQLException {

        this.positionDao.update(uav.getGeoPosition());

        String sql = "UPDATE uavs SET "
                + "code = ?, speed = ?, battery_percantage = ? "
                + "WHERE id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, uav.getCode());
            stmt.setDouble(2, uav.getSpeed());
            stmt.setDouble(3, uav.getBatteryPercentage());
            stmt.setInt(4, uav.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM uavs WHERE id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAllBatteries(List<Uav> uavs) {
        String sql = "UPDATE uavs SET battery_percantage = ?, flight_finished_datetime = ? WHERE id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            for (Uav uav : uavs) {
                stmt.setDouble(1, uav.getBatteryPercentage());
                stmt.setObject(2, uav.getFlightFinishedTime());
                stmt.setInt(3, uav.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finishFlight(int id, LocalDateTime datetime) {
        String sql = "UPDATE uavs SET flight_finished_datetime = ? WHERE id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setObject(1, datetime);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Uav> getAllByNotEmptyBattariesAndFligth() {
        List<Uav> userList = new ArrayList<>();

        String query = "SELECT u.id, u.battery_percantage, u.flight_started_datetime, u.flight_finished_datetime "
                + "FROM uavs u "
                + "WHERE u.battery_percantage > 0 and u.flight_finished_datetime IS NULL ";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            //stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int uavId = rs.getInt("id");
                    Double batteryPercantage = rs.getDouble("battery_percantage");
                    LocalDateTime startDate = rs.getTimestamp("flight_started_datetime").toLocalDateTime();
                    var finishDate = rs.getTimestamp("flight_finished_datetime") != null ? rs.getTimestamp("flight_finished_datetime").toLocalDateTime() : null;

                    Uav uav = new Uav();

                    uav.setId(uavId);
                    uav.setBatteryPercentage(batteryPercantage);
                    uav.setFlightStartedTime(startDate);
                    uav.setFlightFinishedTime(finishDate);
                    userList.add(uav);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private Uav resultSetToModel(ResultSet rs) throws SQLException {//düzenle
        int uavId = rs.getInt("id");
        String code = rs.getString("code");
        int positionId = rs.getInt("position_id");
        Double longitude = rs.getDouble("longitude");
        Double latitude = rs.getDouble("latitude");
        Double altitude = rs.getDouble("altitude");
        Double batteryPercantage = rs.getDouble("battery_percantage");
        Double speed = rs.getDouble("speed");
        LocalDateTime flightStartedDate = rs.getTimestamp("flight_started_datetime").toLocalDateTime();
        LocalDateTime flightFinishedDate = rs.getTimestamp("flight_finished_datetime")!= null ? rs.getTimestamp("flight_finished_datetime").toLocalDateTime() : null;
        
        Position position = new Position(positionId, longitude, latitude, altitude);
        return new Uav(uavId, code, batteryPercantage, position, speed, flightStartedDate,flightFinishedDate);
    }
}
