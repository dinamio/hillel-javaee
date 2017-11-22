package edu.karazin.shop.model;

public class Product {

	private long id;
	private String title;
	private String description;
	private String image;
	private long cost;
	private int balance;

	public Product() {
	}

	public Product(long id, String title, String description) {
		this(id, title, description, null, 0L, 0);
	}

	public Product(long id, String title, String description, String image, long cost, int balance) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.cost = cost;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
