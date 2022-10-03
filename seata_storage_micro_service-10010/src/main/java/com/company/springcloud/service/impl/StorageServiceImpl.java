package com.company.springcloud.service.impl;

import com.company.springcloud.dao.StorageDao;
import com.company.springcloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    @Resource
    private StorageDao storageDao;

    @Override
    public void reduce(Long productId, Integer nums) {
        LOGGER.info("==========seata_storage_micro_service-10010 扣减库存 start==========");
        storageDao.reduce(productId, nums);
        LOGGER.info("==========seata_storage_micro_service-10010 扣减库存 end==========");
    }
}
