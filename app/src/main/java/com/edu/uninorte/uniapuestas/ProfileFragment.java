package com.edu.uninorte.uniapuestas;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.uninorte.uniapuestas.users.UserViewModel;

/**
 * Created by erwin on 3/11/2018.
 */

public class ProfileFragment extends Fragment {
    EditText editTextNombre;
    Button btnGuardar;
    UserViewModel userModel;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile_fragment,container,false);
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextNombre.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        userModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(UserViewModel.class);

        Log.d("USERNEW",DataSingleton.currentUser.toString());
        DataSingleton.currentUser.setName(editTextNombre.getText().toString() + ""); // se actualiza el nombre del usuario

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextNombre.getText().toString().equals("")) return;
                userModel.addUser(DataSingleton.currentUser); // aqui se sobreescribe el usuario actual con el nuevo nombre //revisar nombre queda empty en tabla
                Log.d("USERNEW",DataSingleton.currentUser.toString());
                Toast.makeText(view.getContext(),"ACCIONADO",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
