package com.desafiolatam.chucknorrisrules.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.desafiolatam.chucknorrisrules.networks.FactAsyncTask;
import com.desafiolatam.chucknorrisrules.R;
import com.desafiolatam.chucknorrisrules.models.Fact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundFacts().execute("icon_url", "id", "url", "value");
                TextView textView= (TextView) findViewById(R.id.factTv);
            }
        });
    }

    public class BackgroundFacts extends FactAsyncTask{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Fact fact) {
            if(fact != null){
                TextView quoteTextView = (TextView) findViewById(R.id.factTv);
                quoteTextView.setText(fact.getValue());
            }
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

}
