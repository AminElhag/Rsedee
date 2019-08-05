package com.kafwapteam.rsedee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Crate the val
    private EditText editTextToSelcetThePrice;
    private Button buttonToSendToSudani, buttonToSendToMTN, buttonToSendToZain;

    //TODO : select number
    private final static String BasicSudaniNumber = "xxxxxxxxx";
    private final static String BasicMTNNumber = "xxxxxxxxx";
    private final static String BasicZainNumber = "xxxxxxxxxx";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link item.
        editTextToSelcetThePrice = findViewById(R.id.editTexttoselcettheprice);

        buttonToSendToSudani = findViewById(R.id.buttontosendtosudani);
        buttonToSendToMTN = findViewById(R.id.buttontosendtomtn);
        buttonToSendToZain = findViewById(R.id.buttontosendtozain);

        //
        final int price = Integer.valueOf(editTextToSelcetThePrice.getText().toString());

        //
        buttonToSendToSudani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funToSendToSudani(price);
            }
        });

        //
        buttonToSendToSudani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funToSendToMTN(price);
            }
        });

        //
        buttonToSendToSudani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funToSendToZain(price);
            }
        });
    }


    //Sudani USSD
    private void funToSendToSudani(int price) {

        //The USSD to send alrsed;
        String USSD = "*333*" + price + BasicSudaniNumber + "*0000#";
        startUSSD(USSD);

    }


    //MTN USSD
    private void funToSendToMTN(int price) {

        //The USSD to send alrsed;
        String USSD = "*121*" + BasicMTNNumber + price + "00" + "*00000#";
        startUSSD(USSD);
    }

    //MTN USSD
    private void funToSendToZain(int price) {

        //ToDO : Solve the problem of requesting the last four digits in the slide
    }

    //
    private void startUSSD(String USSD) {
        //
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(ussdToCallableUri(USSD));

        //
        try {
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    //Format the USSD.
    private Uri ussdToCallableUri(String ussd) {

        String uriString = "";

        if (!ussd.startsWith("tel:")) {
            uriString += "tel:";
        }

        for (char c : ussd.toCharArray()) {

            if (c == '#') {
                uriString += Uri.encode("#");
            } else {
                uriString += c;
            }
        }

        return Uri.parse(uriString);
    }

}
