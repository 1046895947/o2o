package club.bagedate.o2o.service;

import club.bagedate.o2o.entity.Place;

import java.util.List;

public interface PlaceService {
    public List<Place> getPlaceList();
    public Place selectById(Integer id);
}
