package com.vipin.gupta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vipin.gupta.services.IConnect;

@RestController
public class ConnectController {

	@Autowired
	IConnect connectService;
	
	@RequestMapping("/connected")
    public String  isConnected(@RequestParam(value="origin") String origin,@RequestParam(value="destination") String destination) {
       
		return connectService.isConnected(origin, destination)+"";
		//return origin+destination;
    }
}
