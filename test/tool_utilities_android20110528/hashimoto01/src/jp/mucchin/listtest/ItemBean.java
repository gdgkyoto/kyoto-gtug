package jp.mucchin.listtest;

public class ItemBean {

	private String name = "";
	private String url = "";
	private String price = "";
	private String ItemCount ="";
	private String priceSum = "";
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice() {
		return price;
	}
		
	public void setItemCount(String ItemCount) {
		this.ItemCount = ItemCount;
	}
	public String getItemCount() {
		return ItemCount;
	}	

	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}
	public String getPriceSum() {
		return priceSum;
	}		
	
	
}
