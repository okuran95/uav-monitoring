package dao;

import core.helper.Generator;
import entity.Uav;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockUavDao implements UavDaoInterface {

    private static final ArrayList<Uav> uavs = new ArrayList<>();
   
    @Override
    public void save(Uav uav) {
        int id = Generator.generateUniqueId(uavs);
        uav.setId(id);
        uavs.add(uav);
    }

    @Override
    public ArrayList<Uav> getAll() {
        return uavs;
    }

    @Override
    public Uav getById(int id) {
        for (Uav uav : uavs) {
            if (uav.getId() == id) {
                return uav;
            }
        }
        return null;
    }

    @Override
    public void update(Uav uav) throws SQLException {        
    }

    @Override
    public void delete(int id) {
        Uav uav = getById(id);
        uavs.remove(uav);
    }

    @Override
    public void updateAllBatteries(List<Uav> uavs) {
    }

    @Override
    public void finishFlight(int id, LocalDateTime datetime) {
        Uav uav = getById(id);
        uav.setFlightFinishedTime(datetime);
    }

    @Override
    public List<Uav> getAllByNotEmptyBattariesAndFligth() {
        ArrayList<Uav> list = new ArrayList<>();
        for(Uav uav :uavs){
            if (uav.getBatteryPercentage()>0 && uav.getFlightFinishedTime() == null){
                list.add(uav);
            }
        }
        return list;
    }

}
