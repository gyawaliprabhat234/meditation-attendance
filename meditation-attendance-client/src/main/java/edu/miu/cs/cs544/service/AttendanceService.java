package edu.miu.cs.cs544.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AttendanceService {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spring-boot-server.name}")
	private String serverName;

	public String scanBarcode(String barCodeId) {
		String url = getBaseServiceUrl() + "/faculty/basic";
		return restTemplate.getForObject(url, String.class);
	}

	private String getBaseServiceUrl() {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
		return serviceInstances.get(0).getUri().toString();
	}
}
