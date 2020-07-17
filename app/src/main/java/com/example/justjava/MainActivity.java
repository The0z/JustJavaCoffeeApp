/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

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
        //int coffeeCost = 3; //Old Code used without string
        //displayPrice(quantity*coffeeCost); //Old code used without string
        String CupPrice = NumberFormat.getCurrencyInstance().format(quantity*5);
        String priceMessage = "Your total comes to " + CupPrice + " for " + quantity + " cups of coffee";
        displayMessage(priceMessage);
    }

    /*
    PsuedoCode - Code in Human Language (essentially what the code is doing).
    Should read current quantity value
    increase current quantity value by 1
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
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
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
     */
    private int calculatePrice(int numOfItem, int pricePerItem){
        int price = numOfItem * pricePerItem;
        return price;
    }


}