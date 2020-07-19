/*
  IMPORTANT: Make sure you are using the correct package name.
  This example uses the package name:
  package com.example.android.justjava
  If you get an error when copying this code into Android studio, update it to match teh package name found
  in the project's AndroidManifest.xml file.
 */
package com.example.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;

//import android.support.v7.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //Global Variable creation
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));
    }

    /**
     * This method is called when the order button is clicked.
     * @return outputs the order summary for ordering coffee
     */
    private String createOrderSummary(int price) {
        String priceText = displayPriceCurrency(price);
        String orderSummary = "Name: Austin";
        orderSummary += "\nQuantity: " + quantity + "\nTotal: " + priceText + "\nThank You!";
        return orderSummary;
    }

    /**
    * PsuedoCode - Code in Human Language (essentially what the code is doing).
    * Should read current quantity value
    * increase current quantity value by 1
    */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        if(quantity > 0){
            quantity = quantity - 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param number - represents a quantity to display
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        String textToPrint = "" + number;
        quantityTextView.setText(textToPrint);
    }

    /**
     * This method displays the given price on the screen.
     */
    private String displayPriceCurrency(int number) {
        return NumberFormat.getCurrencyInstance().format(number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method calculates the price of an item
     *
     * @return total price
     */
    private int calculatePrice(){
        return quantity*5;
    }


}