package com.edu.uninorte.uniapuestas;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadJSONFragment extends Fragment {

    EditText textoUrl;
    Button btnLoadJSON;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.load_json_fragment,container,false);
        textoUrl = view.findViewById(R.id.editTextJsonURL);
        textoUrl.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorUnderline), PorterDuff.Mode.SRC_ATOP);
        btnLoadJSON = view.findViewById(R.id.btnLoadJSON);

        btnLoadJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"ME ACCIONE EXITOSAMENTE",Toast.LENGTH_SHORT).show();
                getActivity().getFragmentManager().popBackStack(); // esto es experimental
            }
        });
        return view;
    }

}
