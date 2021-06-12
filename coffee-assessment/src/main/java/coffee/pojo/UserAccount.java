package coffee.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAccount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String user;
	private List<Order> orders = new ArrayList<Order>();
	private List<Payment> payments = new ArrayList<Payment>();
	private Double credit = 0d;
	private Double debt = 0d;
	
	public UserAccount(String user) {
		super();
		this.user = user;
	}
	
	public UserAccount addPayment(Payment payment) {
		if(payment!=null) {
			payments.add(payment);
			credit+=payment.getAmount();
		}
		return this;
	}
	
	public UserAccount addOrder(Order order) {
		if(order!=null) {
			orders.add(order);
			debt+=order.getAmount();
		}
		return this;
	}
	
	public String getUser() {
		return user;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public Double getCredit() {
		return credit;
	}
	public Double getDebt() {
		return debt;
	}
	

}
