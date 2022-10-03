package com.company.springcloud.controller;

import com.company.springcloud.entity.Member;
import com.company.springcloud.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class MemberConsumerController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;
    //后面这里地方会修改成提供服务模块的注册别名
    private static final String MEMBER_SERVICE_PROVIDER_URL = "http://MEMBER-SERVICE-PROVIDER";

    @GetMapping("/consumer/member/query/{id}")
    public Result<Member> query(@PathVariable("id") Long id) {
        //getForObject：返回对象为响应体中数据转化成的对象，基本可以理解为json
        return restTemplate.getForObject(MEMBER_SERVICE_PROVIDER_URL + "/member/query/" + id, Result.class);
    }

    @GetMapping("/consumer/member/save")
    public Result<Member> save(Member member) {
        //postForObject：返回对象为响应体中数据转化成的对象，基本可以理解为json
        return restTemplate.postForObject(MEMBER_SERVICE_PROVIDER_URL + "/member/save", member, Result.class);
    }

    @GetMapping("/consumer/member/getForEntity/{id}")
    public Result<Member> query2(@PathVariable("id") Long id) {
        //getForEntity：返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
        ResponseEntity<Result> entity = restTemplate.getForEntity(MEMBER_SERVICE_PROVIDER_URL + "/member/query/" + id, Result.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info("--响应信息：" + entity.getStatusCode() + "\t" + entity.getStatusCodeValue() + "\t" + entity.getHeaders());
            return entity.getBody();
        } else {
            return Result.error("402", "ID=" + id + "不存在");
        }
    }

    @GetMapping("/consumer/member/discovery")
    public Object discovery() {
        //获取所有的服务名称
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("--服务名称：" + service);
        }
        //根据服务名称获取所有的微服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("MEMBER-SERVICE-PROVIDER");
        for (ServiceInstance instance : instances) {
            log.info("--服务信息：" + instance.getServiceId() + "\t" + instance.getHost()
                    + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
}