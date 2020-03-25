package models;

public class Card {
    String cardNumber;
    String month;
    String year;
    String name;

    public Card(String cardNumber, String month, String year, String name) {
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
