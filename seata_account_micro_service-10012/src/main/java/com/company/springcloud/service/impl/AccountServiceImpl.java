package com.company.springcloud.service.impl;

import com.company.springcloud.dao.AccountDao;
import com.company.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Resource
    AccountDao accountDao;

    @Override
    public void reduce(Long userId, Integer money) {
        LOGGER.info("========seata_account_micro_service-10012 扣减账户余额 start========");
        accountDao.reduce(userId, money);
        LOGGER.info("========seata_account_micro_service-10012 扣减账户余额 end========");
    }
}
