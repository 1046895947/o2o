package club.bagedate.o2o.service;

import club.bagedate.o2o.entity.HeadLine;
import club.bagedate.o2o.entity.HeadLineExample;

import java.util.List;

public interface HeadLineService {
    public List<HeadLine> getHeadLineList(HeadLineExample headLine);
}
