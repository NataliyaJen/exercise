package coffee.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import coffee.pojo.UserAccount;
import coffee.service.CoffeeData;

@RestController
public class CoffeeRestController {

	@Autowired
	protected CoffeeData coffeeData;
	
	

	@GetMapping("/coffee/accounts/{query}")	
	public ResponseEntity<Map<String,UserAccount>> getAccounts(@PathVariable(name="query", required=false) String query) {

		try {
			if(query==null || query.trim().length()==0 || query.equals("*"))
				return new ResponseEntity<Map<String,UserAccount>>((Map<String,UserAccount>) coffeeData.getAccounts(), HttpStatus.OK);
			else
				return new ResponseEntity<Map<String,UserAccount>>(
						(Map<String,UserAccount>) 
						coffeeData.getAccounts().entrySet().stream()
						.filter(map -> map.getKey().toLowerCase().contains(query.toLowerCase()))	
						.collect(Collectors.toMap(entry -> entry.getKey(),(entry -> entry.getValue())))
						, HttpStatus.OK);
		}catch(Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	



	
}
