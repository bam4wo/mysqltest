package com.bam.mysqltest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bam.mysqltest.API.APIRequestData;
import com.bam.mysqltest.API.RetroServer;
import com.bam.mysqltest.Model.ResponseModel;
import com.bam.mysqltest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Create extends AppCompatActivity {
    private EditText etname,etalamat,ettele;
    private Button button;
    private String name,ala,tele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etname = findViewById(R.id.et_name);
        etalamat = findViewById(R.id.et_alamat);
        ettele = findViewById(R.id.et_telepon);
        button = findViewById(R.id.btn_simpan);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etname.getText().toString();
                ala = etalamat.getText().toString();
                tele = ettele.getText().toString();

                if(name.trim().equals("")){
                    etname.setText("aaa");
                }else if(ala.trim().equals("")){
                    etalamat.setText("vvv");
                }else if(tele.trim().equals("")){
                    ettele.setText("ccc");
                }else {
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simData = ardData.ardCreate(name,ala,tele);

        simData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(Create.this,kode+pesan,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(Create.this,"Serve"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}