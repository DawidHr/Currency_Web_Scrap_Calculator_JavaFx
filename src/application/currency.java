package application;

public class currency {
private String nameCurrency;
private float number;
public currency(String nameCurrency, float number) {
	super();
	this.nameCurrency = nameCurrency;
	this.number = number;
}
public String getNameCurrency() {
	return nameCurrency;
}
public float getNumber() {
	return number;
}
public void setNameCurrency(String nameCurrency) {
	this.nameCurrency = nameCurrency;
}
public void setNumber(float number) {
	this.number = number;
}

}
