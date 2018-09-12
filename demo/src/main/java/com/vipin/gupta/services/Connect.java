package com.vipin.gupta.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Connect implements IConnect {

	@Autowired
	CitiMap cityMap;

	@Override
	public boolean isConnected(String origin, String destination) {
		 
		Map<String, Set<String>> map = cityMap.getCityMap();
		
		boolean isConnected  = origin.equals(destination);
		
		if (map.containsKey(origin) && map.containsKey(destination)) {
			// By using a Queue, we are implementing Breadth First Search
			// This will find the shortest path between two cities
			Queue<String> citiesToVisit = new LinkedList<String>();

			// We keep a set of the cities we have already visited. This prevents BFS from looping in
			// cycles and allows the BFS to terminate if no path can be found after exploring all reachable nodes
			Set<String> citiesAlreadyVisited = new HashSet<String>();

			citiesToVisit.add(origin);

			while (!citiesToVisit.isEmpty() && !isConnected) {
				String city = citiesToVisit.poll();
				isConnected = city.equals(destination);

				Set<String> possibleConnections = map.get(city);
				for (String possibleCity : possibleConnections) {
					if (!citiesAlreadyVisited.contains(possibleCity)) {
						citiesToVisit.add(possibleCity);
						citiesAlreadyVisited.add(possibleCity);
					}
				}
			}
		}
		
		return isConnected;
	}
	 
}
