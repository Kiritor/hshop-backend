package com.kiritor.hshop.serviceuser;

import com.kiritor.hshop.serviceuser.config.ServerConfig;
import com.kiritor.hshop.serviceuser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author: 子梁
 * @Description
 * @Date
 */

@SpringBootApplication(scanBasePackages = {"com.kiritor.hshop.serviceuser"})
@RestController
@EnableEurekaClient
public class ServiceUserServerApplication {

	@Autowired
	private ServerConfig serverConfig;
	private User user;

	public static void main(String[] args) {
		SpringApplication.run(ServiceUserServerApplication.class, args);
	}

	/**
	 * 假如这个客户端要提供一个getUser的方法
	 * @return
	 */
	@GetMapping(value = "/getUser")
	@ResponseBody
	public Map<String,Object> getUser(@RequestParam Integer id){
		Map<String,Object> data = new HashMap<>();
		data.put("id",id);
		data.put("userName","admin");
		data.put("from","service-user-server["+serverConfig.getUrl()+"]");
		return data;
	}
}
