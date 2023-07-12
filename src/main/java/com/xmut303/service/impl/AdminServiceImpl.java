package com.xmut303.service.impl;

import com.xmut303.dao.AdminMapper;
import com.xmut303.pojo.Admin;
import com.xmut303.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/28 14:20
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdmin(Admin admin) {
        return adminMapper.findAdmin(admin);
    }
}
