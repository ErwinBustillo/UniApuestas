package com.edu.uninorte.uniapuestas;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by erwin on 3/11/2018.
 */

public class ProfileFragment extends Fragment {
    EditText editTextNombre;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile_fragment,container,false);
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextNombre.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
        return view;
    }
}
