package com.edu.uninorte.uniapuestas;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText textoNombre;
    EditText textoEmail;
    EditText textoPassword;
    EditText textoConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textoNombre = findViewById(R.id.campoNombre);
        textoEmail = findViewById(R.id.campoEmail);
        textoPassword = findViewById(R.id.campoPassword);
        textoConfirmPassword = findViewById(R.id.campoConfirmPassword);

        textoNombre.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
        textoEmail.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
        textoPassword.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
        textoConfirmPassword.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
    }

    public void onClickLabelGoBackLogin(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickButtonRegister(View view) {
        if(textoNombre.getText().toString().equals("")){
            Toast.makeText(view.getContext(),"DIGITE SU Nombre",Toast.LENGTH_SHORT).show();
            return;
        }
        if (textoEmail.getText().toString().equals("")){
            Toast.makeText(view.getContext(),"DIGITE SU email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(textoPassword.getText().toString().equals("")){
            Toast.makeText(view.getContext(),"DIGITE SU password",Toast.LENGTH_SHORT).show();
            return;
        }
        if (textoConfirmPassword.getText().toString().equals("")){
            Toast.makeText(view.getContext(),"DIGITE el campo",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!(textoPassword.getText().toString().equals(textoConfirmPassword.getText().toString()))){
            Toast.makeText(view.getContext(),"Los passwords no coinciden",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}
