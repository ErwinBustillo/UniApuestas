package com.edu.uninorte.uniapuestas;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by erwin on 3/11/2018.
 */


public class LoginActivity extends AppCompatActivity {

    EditText textoEmail;
    EditText textoPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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

        // hacer la validacion de los datos del servidor

        Intent i = new Intent(view.getContext(),PrincipalActivity.class);
        startActivity(i);

    }
}
