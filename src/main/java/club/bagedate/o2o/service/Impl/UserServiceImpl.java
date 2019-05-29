package club.bagedate.o2o.service.Impl;

import club.bagedate.o2o.entity.User;
import club.bagedate.o2o.mapper.UserMapper;
import club.bagedate.o2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
