package business;

import core.helper.Generator;
import core.ValidationException;
import core.helper.ValidationHelper;
import dao.MockUavDao;
import dao.UavDaoInterface;
import entity.Position;
import entity.Uav;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UavService {

    private final UavDaoInterface uavDao;
    private final String format = "#.#####";

    public UavService() {
        this.uavDao = new MockUavDao();
    }

    public List<Uav> getAll() {
        return this.uavDao.getAll();
    }

    public Uav getById(int id) {
        return this.uavDao.getById(id);
    }

    public Boolean save(String code, String latitude, String longitude, String altitude, String speed, String battery) throws ValidationException, SQLException {

        saveValidation(code, latitude, longitude, altitude, speed, battery);

        double validatedSpeed = Double.parseDouble(speed);
        double validatedBattery = Double.parseDouble(battery);
        double validatedLatitude = Double.parseDouble(latitude);
        double validatedLongitude = Double.parseDouble(longitude);
        double validatedAltitude = Double.parseDouble(altitude);

        if (!ValidationHelper.isValidLatitude(validatedAltitude)
                || !ValidationHelper.isValidLongitude(validatedLongitude)
                || !ValidationHelper.isValidLongitude(validatedAltitude)) {
            throw new ValidationException("Coğrafi Konum uygun formatta değil");
        }

        Position position = new Position();

        position.create(
                validatedLatitude,
                validatedLongitude,
                validatedAltitude
        );

        Uav uav = new Uav();
        uav.create(code, validatedBattery, position, validatedSpeed, LocalDateTime.now());

        this.uavDao.save(uav);
        return true;

    }

    public Boolean update(int id, String code, String latitude, String longitude, String altitude, String speed, String battery) throws ValidationException, SQLException {

        Objects.requireNonNull(id);

        saveValidation(code, latitude, longitude, altitude, speed, battery);

        double validatedSpeed = Double.parseDouble(speed);
        double validatedBattery = Double.parseDouble(battery);
        double validatedLatitude = Double.parseDouble(latitude);
        double validatedLongitude = Double.parseDouble(longitude);
        double validatedAltitude = Double.parseDouble(altitude);

        if (!ValidationHelper.isValidLatitude(validatedAltitude)
                || !ValidationHelper.isValidLongitude(validatedLongitude)
                || !ValidationHelper.isValidLongitude(validatedAltitude)) {
            throw new ValidationException("Coğrafi Konum uygun formatta değil");
        }
        //todo:transactional!
        Uav uav = this.uavDao.getById(id);
        uav.getGeoPosition().update(validatedLatitude, validatedLongitude, validatedAltitude);

        uav.update(code, validatedBattery, validatedSpeed);

        this.uavDao.update(uav);
        return true;

    }

    public void delete(int id) {
        this.uavDao.delete(id);
    }

    public void reduceBatteryPercantage() {
        System.out.print("çalıştı");
        List<Uav> uavs = this.uavDao.getAllByNotEmptyBattariesAndFligth();
        LocalDateTime now = LocalDateTime.now();
        for (Uav uav : uavs) {
            Double batteryPercentage = uav.getBatteryPercentage();
            if (uav.getDuration().getHour() >= 5) {
                uav.setFlightFinishedTime(now);
            }
            if (uav.getFlightFinishedTime() == null) {
                batteryPercentage = Math.max(0, batteryPercentage - 0.2);
                if (batteryPercentage == 0) {
                    uav.setFlightFinishedTime(now);
                }
                uav.setBatteryPercentage(batteryPercentage);
            }

        }
        this.uavDao.updateAllBatteries(uavs);
    }

    public void stop(int id) {
        LocalDateTime now = LocalDateTime.now();
        this.uavDao.finishFlight(id, now);
    }

    public void createMockData() {
        for (int i = 0; i < 25; i++) {
            Uav uav = new Uav(
                    1,
                    Generator.stringGenerate(),
                    Generator.doubleGenerate(0.0, 100.0, 1),
                    Generator.coordinateGenerate(),
                    Generator.doubleGenerate(0, 5.0, 1),
                    LocalDateTime.now(),
                    null
            );
            this.uavDao.save(uav);
        }

    }

    private void saveValidation(String code, String latitude, String longitude, String altitude, String speed, String battery)
            throws ValidationException {
        if (ValidationHelper.isBlank(code)) {
            throw new ValidationException("Kod boş olamaz!");
        }

        if (ValidationHelper.isBlank(latitude) || ValidationHelper.isBlank(longitude) || ValidationHelper.isBlank(altitude)) {
            throw new ValidationException("Coğrafi Konum boş olamaz");
        }

        if (ValidationHelper.isBlank(speed)) {
            throw new ValidationException("Hız boş olamaz");
        }

        if (ValidationHelper.isBlank(battery)) {
            throw new ValidationException("Batarya boş olamaz");
        }
    }
}
