package com.example.paimonsofferings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThankYouFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThankYouFragment extends Fragment {


    Button OrderAgain;
    Button Exit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_thank_you, container, false);

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Design_Light_NoActionBar);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        OrderAgain = (Button) view.findViewById(R.id.button_OrderAgain);
        Exit = (Button) view.findViewById(R.id.button_Exit);

//        TODO: IMPLEMENT 2 BUTTONS
//        1ST BUTTON: EXIT APP
//        2ND BUTTON: ORDER AGAIN
        OrderAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuFragment menuFragment = new MenuFragment();

                getFragmentManager().beginTransaction().add(R.id.fragmentBox, menuFragment).commit();
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

}