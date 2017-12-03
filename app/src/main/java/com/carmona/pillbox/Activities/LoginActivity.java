package com.carmona.pillbox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carmona.pillbox.API.PillboxAPI;
import com.carmona.pillbox.Adapters.LoginRVAdapter;
import com.carmona.pillbox.Models.Login;
import com.carmona.pillbox.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.carmona.pillbox.Config.Preferences.BASE_URL;

public class LoginActivity extends AppCompatActivity implements Callback<List<Login>> {

    private String user;
    private String pass;
    private String iduser;
    private LoginRVAdapter mLoginRVAdapter;
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
                Toast.makeText(this,"User"+user+" Pass"+pass,Toast.LENGTH_SHORT).show();

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

            Toast.makeText(this,"Id "+LoginList.get(0).getIdusuario(),Toast.LENGTH_LONG).show();
            //mLogin.clear();
            /*for (Login login : LoginList) {
                mLogin.add(login);
            }*/
            //mLoginRVAdapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }

    }

    @Override
    public void onFailure(Call<List<Login>> call, Throwable t) {
        t.printStackTrace();
        Toast.makeText(this,"Error de conexión",Toast.LENGTH_LONG).show();
    }
}
