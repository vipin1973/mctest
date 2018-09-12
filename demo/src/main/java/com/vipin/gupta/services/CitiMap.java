package com.vipin.gupta.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class CitiMap {

	private Map<String, Set<String>> cityMap ;

	 
	
	public Map<String, Set<String>> getCityMap() {
		
		if(null!=cityMap)
			return cityMap;
		else {
			
			cityMap = new HashMap<String, Set<String>>();
			Stream<String> stream =null;
			try 
			{
				stream = Files.lines(Paths.get("C:/Users/c0gupv2/city.txt")) ;
				stream.forEach(line ->{
					String[] cities = line.split(",");
					String originationCity = cities[0].trim();
					String destinationCity = cities[1].trim();
					Set<String> originationCityConnections = getConnectionsMap(cityMap,originationCity);
					Set<String> destinationCityConnections = getConnectionsMap(cityMap,destinationCity);
					originationCityConnections.add(destinationCity);
					destinationCityConnections.add(originationCity); 
				});
				 
              stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if(null!=stream){
					stream.close();
				}
			}
			return cityMap;
		}
	}
	
	private Set<String> getConnectionsMap(Map<String, Set<String>> map, String city) {
		if (!map.containsKey(city)) {
			map.put(city, new HashSet<String>());
		}
		return map.get(city);
	}
}
