/*
  IMPORTANT: Make sure you are using the correct package name.
  This example uses the package name:
  package com.example.android.justjava
  If you get an error when copying this code into Android studio, update it to match teh package name found
  in the project's AndroidManifest.xml file.
 */
package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

//import android.support.v7.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //Global Variable creation
    int quantity = 1;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = getString(R.string.default_name);
    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCreamChecked = findViewById(R.id.whipped_cream_check);
        CheckBox chocolateChecked = findViewById(R.id.chocolate_check);
        boolean hasWhippedCream = whippedCreamChecked.isChecked();
        boolean hasChocolate = chocolateChecked.isChecked();

        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String body = createOrderSummary(price,hasWhippedCream,hasChocolate);
        String[] arr = new String[]{getString(R.string.hotmail_address)};
        String subject = getString(R.string.order_summary_email_subject, name);
        composeEmail(arr,subject, body);
    }

    /**
     * This method is called when the order button is clicked.
     * @return outputs the order summary for ordering coffee
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceText = displayPriceCurrency(price);
        customerName();
        String orderSummary = getString(R.string.order_summary_name, name);
        orderSummary += getString(R.string.add_whipped_cream) + yesOrNoToppings(hasWhippedCream);
        orderSummary += getString(R.string.add_chocolate) + yesOrNoToppings(hasChocolate);
        orderSummary += getString(R.string.quantity) + quantity + getString(R.string.total) + priceText + getString(R.string.thank_you_message);
        return orderSummary;
    }

    /**
     * This method is called when the EditText box is entered
     * updates the global name variable
     */
    private void customerName() {
        EditText userTextField = findViewById(R.id.name);
        name = userTextField.getText().toString();
        if(name.equals("")) {name = getString(R.string.default_name);}
    }



    /**
     * This method is called when the order button is clicked.
     * @return outputs yes or no depending on the state of the checkbox.
     */
    private String yesOrNoToppings(boolean hasTopping) {
        if(hasTopping){ return getString(R.string.yes_message); }
        else{ return getString(R.string.no_message); }
    }


    /**
    * PsuedoCode - Code in Human Language (essentially what the code is doing).
    * Should read current quantity value
     * outputs a toast message if user tries to order more than 100 cups of coffee
    * increase current quantity value by 1
    */
    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(this, R.string.max_order_coffee_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is used to decrease global variable quantity by one.
     *     * outputs a toast message if user tries to order less than one cup of coffee
     * @return void
     */
    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this, R.string.min_order_coffee_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
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
     * This method calculates the price of an item
     *
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){

        int price = 5;
        if(hasWhippedCream) price += 1;
        if(hasChocolate) price += 2;

        return quantity*price;
    }

    /**
     * This method is so email intent can be called
     */
    public void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}