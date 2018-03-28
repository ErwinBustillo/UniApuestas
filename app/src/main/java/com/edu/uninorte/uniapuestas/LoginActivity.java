package com.edu.uninorte.uniapuestas;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.uninorte.uniapuestas.users.UserEntity;
import com.edu.uninorte.uniapuestas.users.UserViewModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by erwin on 3/11/2018.
 */


public class LoginActivity extends AppCompatActivity {

    EditText textoEmail;
    EditText textoPassword;
    private UserViewModel model;
    List<UserEntity> usuarios;
    UserEntity u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // adds Admin
        model = ViewModelProviders.of(this).get(UserViewModel.class);
        model.addUser(new UserEntity(1, "Admin", "admin@admin.com", "1234", true, "0"));
        //

        model.getAllUsers().observe(this, users -> {
            usuarios = users;
            Log.d("TAGASO", usuarios.get(0).toString());
        });

        textoEmail = findViewById(R.id.textoEmail);
        textoPassword = findViewById(R.id.textoPassword);
        textoEmail.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
        textoPassword.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
    }

    public void onClickLabelRegister(View view) {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public void onClickButtonLogin(View view) {
        if(textoEmail.getText().toString().equals("")){
            Toast.makeText(this,"DIGITE SU CORREO",Toast.LENGTH_SHORT).show();
            return;
        }
        if (textoPassword.getText().toString().equals("")){
            Toast.makeText(view.getContext(),"DIGITE SU PASSWORD",Toast.LENGTH_SHORT).show();
            return;
        }



        model.getUser(textoEmail.getText().toString(), textoPassword.getText().toString()).observe(this, user -> {
            u = user;

            if(u != null) {
                Log.d("TAGASO", u.toString());
                // hacer la validacion de los datos del servidor
                Intent i = new Intent(view.getContext(),PrincipalActivity.class);
                i.putExtra("user", (Serializable) u);
                startActivity(i);
            } else {
                Toast.makeText(this,"CREDENCIALES INVALIDAS",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
