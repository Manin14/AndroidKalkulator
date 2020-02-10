package com.manin.kalkulatorsederhana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.sql.RowId;

public class MainActivity extends AppCompatActivity {

    private  int [] operator= {R.id.id_kurang, R.id.id_tambah, R.id.id_samadengan, R.id.id_kali};
    private  int [] angka={R.id.id_satu, R.id.id_dua};

    AppCompatTextView tv=null;

    private boolean jikaError;
    private boolean jikaAngkatidakAdaTambahKurangTidakBisaDiKlik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.id_tv_tampil);

        setAngka();
        setOperator();
    }

    private void setOperator() {
      View.OnClickListener listener=new View.OnClickListener() {
          @Override
          public void onClick(View v) {

             if ( jikaAngkatidakAdaTambahKurangTidakBisaDiKlik && !jikaError)
                 {  AppCompatButton button=(AppCompatButton) v;
                     tv.append(button.getText());
                     jikaAngkatidakAdaTambahKurangTidakBisaDiKlik=false; }


          }
      };
      for (int id: operator){findViewById(id).setOnClickListener(listener);}


      //sama dengan
     findViewById(R.id.id_samadengan).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         samaDengan();
         }
     });


      //bersih
      findViewById(R.id.id_bersih).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
           tv.setText("");
           jikaAngkatidakAdaTambahKurangTidakBisaDiKlik=false;
          }
      });

    }

    private void setAngka() {
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatButton button=(AppCompatButton) v;
                if (jikaError)
                     { tv.setText(button.getText());
                       jikaError=false;
                     }
                else
                    { tv.append(button.getText());}

                jikaAngkatidakAdaTambahKurangTidakBisaDiKlik=true;

            }
        };
        for (int id:angka) { findViewById(id).setOnClickListener(listener);}
    }



    private void samaDengan(){
              if (jikaAngkatidakAdaTambahKurangTidakBisaDiKlik && !jikaError)
                { String tulisan=tv.getText().toString();

                 Expression b=new ExpressionBuilder(tulisan).build();

                  try {
                      double hasil=b.evaluate();
                      tv.setText(Double.toString(hasil));

                  }
                  catch (ArithmeticException f)
                  { tv.setText("Error");
                      jikaError = true;
                      jikaAngkatidakAdaTambahKurangTidakBisaDiKlik = false;}

                }

    }
}
