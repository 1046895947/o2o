package club.bagedate.o2o.service.Impl;

import club.bagedate.o2o.entity.Place;
import club.bagedate.o2o.mapper.PlaceMapper;
import club.bagedate.o2o.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;
    @Override
    public List<Place> getPlaceList() {
        return placeMapper.queryPlace();
    }

    @Override
    public Place selectById(Integer id) {
        return placeMapper.selectByPrimaryKey(id);
    }
}
