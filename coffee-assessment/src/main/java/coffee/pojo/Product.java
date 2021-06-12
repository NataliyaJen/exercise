package coffee.pojo;

import java.io.Serializable;

import java.util.HashMap;

import java.util.Map;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	private String drink_name;
	private Map<String, Double> prices = new HashMap<>();
	
	public String getDrink_name() {
		return drink_name;
	}
	public void setDrink_name(String drink_name) {
		this.drink_name = drink_name;
	}
//	public List<PricesSize> getPrices() {
//		return prices;
//	}
//	public void setPrices(List<PricesSize> prices) {
//		this.prices = prices;
//	}
	public Map<String, Double> getPrices() {
		return prices;
	}
	public void setPrices(Map<String, Double> prices) {
		this.prices = prices;
	}
	
	
	
}
