package club.bagedate.o2o.service.Impl;

import club.bagedate.o2o.entity.HeadLine;
import club.bagedate.o2o.entity.HeadLineExample;
import club.bagedate.o2o.mapper.HeadLineMapper;
import club.bagedate.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineMapper headLineMapper;
    @Override
    public List<HeadLine> getHeadLineList(HeadLineExample headLine) {
        return headLineMapper.selectByExample(headLine);
    }
}
