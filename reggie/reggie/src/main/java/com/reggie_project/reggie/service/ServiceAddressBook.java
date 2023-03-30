package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.AddressBookMapper;
import com.reggie_project.reggie.Pojo.AddressBook;
import com.reggie_project.reggie.service.Impl.AddressBookServicieImpi;
import org.springframework.stereotype.Service;

@Service
public class ServiceAddressBook extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookServicieImpi {
}
