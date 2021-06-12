package coffee.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	private String user;
	private Double amount;
	private List<Order> orders = new ArrayList<>();
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
