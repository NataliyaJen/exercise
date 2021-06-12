package coffee.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import coffee.pojo.Order;
import coffee.pojo.Payment;
import coffee.pojo.Product;
import coffee.pojo.UserAccount;

import org.slf4j.Logger;

@Service
public class CoffeeData {
	
	private boolean loaded = false;
	private List<Product> products = new ArrayList<>();
	private Map<String, UserAccount> accounts =  new ConcurrentHashMap<>();

	Logger logger = LoggerFactory.getLogger(CoffeeData.class);
	
	@PostConstruct
    public void init() {
		

		
		logger.info("-- We are begining --");
		
		try {

		    Gson gson = new Gson();
		    
		    Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("payments.json").toURI()));
		    ((List<Payment>)gson.fromJson(reader, new TypeToken<List<Payment>>(){}.getType()))
		    .forEach(p -> {
		    	if(accounts.containsKey(p.getUser())) 
		    		accounts.get(p.getUser()).addPayment(p);
		    	else 
		    		accounts.putIfAbsent(p.getUser(),new UserAccount(p.getUser()).addPayment(p));		    	
		    	}
		    );
		    
		    reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("products.json").toURI()));
		    products = gson.fromJson(reader, new TypeToken<List<Product>>(){}.getType());
		    final Map<String, Product> mapedProducts = 
		    		products
		    		.stream()
		    		.collect(Collectors.toMap(Product::getDrink_name, Function.identity()));


		    
		    reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("orders.json").toURI()));
		    List<Order> orders = gson.fromJson(reader, new TypeToken<List<Order>>(){}.getType());
		    
		    orders.forEach(new Consumer<Order>() {
				@Override
				public void accept(Order order) {
					Product product = mapedProducts.get(order.getDrink());
					if(product!=null) {
						if(product.getPrices().containsKey(order.getSize()))
							order.setAmount(product.getPrices().get(order.getSize()));
					}
					if(accounts.containsKey(order.getUser()))
						accounts.get(order.getUser()).addOrder(order);
					else
						accounts.putIfAbsent(order.getUser(),new UserAccount(order.getUser()).addOrder(order));
				}
			});
		    
		    loaded = true;
		    
		}catch (Exception e) {
			logger.error("Reading json", e);
		}		
		
    }

	public Map<String, UserAccount> getAccounts() {
		return accounts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public boolean isLoaded() {
		return loaded;
	}




}
