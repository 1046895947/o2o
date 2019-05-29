package bagedate.o2o.Dao;

import club.bagedate.o2o.BaseTest;
import club.bagedate.o2o.mapper.PlaceMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceTest extends BaseTest {
    @Autowired
    private PlaceMapper placeMapper;
    @Test
    public void testPlaceMapper(){
        System.out.println(placeMapper.queryPlace());
    }
}
