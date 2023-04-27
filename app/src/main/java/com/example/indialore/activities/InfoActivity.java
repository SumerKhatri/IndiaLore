package com.example.indialore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.indialore.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView tv=findViewById(R.id.textView8);
        String s="The team of IndiaLore believes that it is very important to service all the needs of its customers to provide them what they really want with a hassle-free and a great user experience, also keeping in mind that our seller's family is equally benefited and achieve higher profits when they are enrolled with IndiaLore.\nIndiaLore's priority is the security and privacy of their users including both our valueable customer's as well as our seller family. Thus, to ensure that we provide smart contract-based architecture for buying and selling of the products and, also assuring the authenticity of the traditional product being sold.\n";
        tv.setText(s);
    }
}