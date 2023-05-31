package ar.com.weather_app.repositories;

import ar.com.weather_app.repositories.dao.ForecastDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IWeatherRepository extends JpaRepository<ForecastDAO, Long> {

    @Query(value = "SELECT f FROM ForecastDAO f WHERE f.locationKey = :location and f.dateTime = :date")
    public List<ForecastDAO> getForecastByLocationKey(@Param("location") Long location, @Param("date") Date date);
}

