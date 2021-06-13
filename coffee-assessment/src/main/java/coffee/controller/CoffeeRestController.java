package coffee.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(CoffeeRestController.class);

	@Autowired
	protected CoffeeData coffeeData;
	
	

	@GetMapping("/coffee/accounts/{query}")	
	public ResponseEntity<List<UserAccount>> getAccounts(@PathVariable(name="query", required=false) String query) {

		try {
			if(query==null || query.trim().length()==0 || query.equals("*"))
				return new ResponseEntity<List<UserAccount>>(
						(List<UserAccount>) 
						coffeeData.getAccounts().values().stream()
						.collect(Collectors.toList())
						, HttpStatus.OK);
			else
				return new ResponseEntity<List<UserAccount>>(
						(List<UserAccount>) 
						coffeeData.getAccounts().values().stream()
						.filter(obj -> obj.getUser().toLowerCase().contains(query.toLowerCase()))	
						.collect(Collectors.toList())
						, HttpStatus.OK);
		}catch(Exception e) { 
			logger.error(" GET accounts with query ["+query+"]",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	



	
}
