package com.carmona.pillbox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.carmona.pillbox.API.PillboxAPI;
import com.carmona.pillbox.Models.Login;
import com.carmona.pillbox.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.carmona.pillbox.Config.Preferences.APP_USER;
import static com.carmona.pillbox.Config.Preferences.BASE_URL;

public class LoginActivity extends AppCompatActivity implements Callback<List<Login>> {

    private String user;
    private String pass;
    private int iduser;
    private ArrayList<Login> mLogin;

    @BindView (R.id.edtPassword)
    TextView edtPassword;
    @BindView(R.id.edtUsuario)
    TextView edtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLogin = new ArrayList<Login>();
    }

    @OnClick(R.id.btnEntrar)
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnEntrar:
                user = edtUsuario.getText().toString();
                pass = edtPassword.getText().toString();
                login();
                break;
        }

    }

    public void login() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PillboxAPI pillboxAPI = retrofit.create(PillboxAPI.class);
        Call<List<Login>> call = pillboxAPI.login(user,pass);
        call.enqueue(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {

        if(response.isSuccessful()) {
            List<Login> LoginList = response.body();
            APP_USER = Integer.parseInt(LoginList.get(0).getIdusuario());
            iduser = APP_USER;

            //Toast.makeText(this,"Id "+iduser,Toast.LENGTH_SHORT).show();
            if(iduser > 0 ){
                Toast.makeText(this,"Bienvenido "+user+" !",Toast.LENGTH_SHORT).show();
                mostrarSistema();
            }else{
                Toast.makeText(this,"Usuario o contraseña incorrectos! :'( ",Toast.LENGTH_SHORT).show();
            }
        } else {
            System.out.println(response.errorBody());
        }

    }

    private void mostrarSistema() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(Call<List<Login>> call, Throwable t) {
        t.printStackTrace();
        Toast.makeText(this,"Error de conexión",Toast.LENGTH_LONG).show();
    }
}
