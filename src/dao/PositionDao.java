/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import core.ConnectDb;
import entity.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author onurkuran
 */
public class PositionDao {

    private final Connection connection;

    public PositionDao() {
        this.connection = ConnectDb.getInstance();
    }

    public void save(Position position) throws SQLException {
        String sql = "INSERT INTO position (latitude, longitude, altitude) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, position.getLatitude());
            stmt.setDouble(2, position.getLongitude());
            stmt.setDouble(3, position.getAltitude());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                position.setId(id);

            }
        }
    }

    public void update(Position position) throws SQLException {
                String sql = "UPDATE position SET "
                + "latitude = ?, longitude = ?, altitude = ? "
                + "WHERE id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, position.getLatitude());
            stmt.setDouble(2, position.getLongitude());
            stmt.setDouble(3, position.getAltitude());
            stmt.setInt(4, position.getId());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                position.setId(id);

            }
        }
    }
}
