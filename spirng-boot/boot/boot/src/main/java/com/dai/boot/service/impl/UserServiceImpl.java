package com.dai.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dai.boot.entity.User;
import com.dai.boot.mapper.UserMapper;
import com.dai.boot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author dai.jf
 * @date 2021/11/1-10:15
 * @description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
