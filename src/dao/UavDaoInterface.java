/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Uav;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author onurkuran
 */



public interface UavDaoInterface {
    
    List<Uav> getAll();
    
    void save(Uav uav);
    
    Uav getById(int id);
    
    void update(Uav uav) throws SQLException;
    
    void delete(int id);
    
    void updateAllBatteries(List<Uav> uavs);
    
    void finishFlight(int id, LocalDateTime datetime);
    
    List<Uav> getAllByNotEmptyBattariesAndFligth();
    // Diğer gerekli metodlar eklenebilir
}

