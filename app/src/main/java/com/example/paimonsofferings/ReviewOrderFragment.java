package com.example.paimonsofferings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ReviewOrderFragment extends Fragment {


    Button ConfirmOrder;
    Button ChangeOrder;

    TextView SiomaiState;
    TextView siomaiQty;
    TextView isawQty;
    TextView eggQty;
    TextView sabawQty;
    TextView chickenQty;


    CheckBox siomai;
    CheckBox isaw;
    CheckBox egg;
    CheckBox sabaw;
    CheckBox chicken;

    Spinner BundleSpinner;

    String siomaiOrderQty;
    String isawOrderQty;
    String eggOrderQty;
    String sabawOrderQty;
    String chickenOrderQty;

    String BundleChoice2;
    double OVERALL_TOTAL, SIOMAITOTAL, ISAWTOTAL, EGGTOTAL, SABAWTOTAL, CHICKENTOTAL;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_order, container, false);

        ConfirmOrder = (Button) view.findViewById(R.id.button_ConfirmOrder);
        ChangeOrder = (Button) view.findViewById(R.id.button_ChangeOrder3);

        siomaiQty = (TextView) view.findViewById(R.id.textView_siomaiQty);
        isawQty = (TextView) view.findViewById(R.id.textView_isawQty);
        eggQty = (TextView) view.findViewById(R.id.textView_eggQty);
        sabawQty = (TextView) view.findViewById(R.id.textView_sabawQty);
        chickenQty = (TextView) view.findViewById(R.id.textView_chickenQty);

        SiomaiState = (TextView) view.findViewById(R.id.textView_siomai_state);

//        Siomai_VALUE = (EditText) view.findViewById(R.id.editText_Siomai_VALUE);
//        Isaw_VALUE = (EditText) view.findViewById(R.id.editText_Isaw_VALUE);
//        Egg_VALUE = (EditText) view.findViewById(R.id.editText_Egg_VALUE);
//        Sabaw_VALUE = (EditText) view.findViewById(R.id.editText_Sabaw_VALUE);
//        Chicken_VALUE = (EditText) view.findViewById(R.id.editText_Chicken_VALUE);

        siomai = (CheckBox) view.findViewById(R.id.checkBox_siomai);
        isaw = (CheckBox) view.findViewById(R.id.checkBox_isaw);
        egg = (CheckBox) view.findViewById(R.id.checkBox_egg);
        sabaw = (CheckBox) view.findViewById(R.id.checkBox_sabaw);
        chicken = (CheckBox) view.findViewById(R.id.checkBox_chicken);



        SIOMAITOTAL = Double.parseDouble(getArguments().getString("SiomaiOrder"));
        ISAWTOTAL = Double.parseDouble(getArguments().getString("IsawOrder"));
        EGGTOTAL = Double.parseDouble(getArguments().getString("EggOrder"));
        SABAWTOTAL = Double.parseDouble(getArguments().getString("SabawOrder"));
        CHICKENTOTAL = Double.parseDouble(getArguments().getString("ChickenOrder"));

        OVERALL_TOTAL = SIOMAITOTAL + ISAWTOTAL + EGGTOTAL + SABAWTOTAL + CHICKENTOTAL;



        siomaiOrderQty = getArguments().getString("SiomaiCount");
        isawOrderQty = getArguments().getString("IsawCount");
        eggOrderQty = getArguments().getString("EggCount");
        sabawOrderQty = getArguments().getString("SabawCount");
        chickenOrderQty = getArguments().getString("ChickenCount");

        if (siomaiOrderQty.isEmpty()){
            siomaiQty.setText(" ");
        }
        if (isawOrderQty.isEmpty()){
            isawQty.setText(" ");
        }
        if (eggOrderQty.isEmpty()){
            eggQty.setText(" ");
        }
        if (sabawOrderQty.isEmpty()){
            sabawQty.setText(" ");
        }
        if (chickenOrderQty.isEmpty()){
            chickenQty.setText(" ");
        }



        BundleChoice2 = getArguments().getString("BundleChoice");

        SiomaiState.setText(String.format("Jade Siomai (%s)", BundleChoice2));

        siomaiQty.setText(String.format("x %s", siomaiOrderQty));
        isawQty.setText(String.format("x %s", isawOrderQty));
        eggQty.setText(String.format("x %s", eggOrderQty));
        sabawQty.setText(String.format("x %s", sabawOrderQty));
        chickenQty.setText(String.format("x %s", chickenOrderQty));


        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendOrderstoOrderSummary();
            }
        });

        ChangeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChangeOrderDialog();
            }
        });


        return view;
    }

    private void SendOrderstoOrderSummary() {




        OrderSummaryFragment fragment2 = new OrderSummaryFragment();

        Bundle args = new Bundle();
        args.putString("SiomaiState", BundleChoice2);
        args.putString("siomaiOrderQty", String.valueOf(siomaiOrderQty));
        args.putString("isawOrderQty", String.valueOf(isawOrderQty));
        args.putString("eggOrderQty", String.valueOf(eggOrderQty));
        args.putString("sabawOrderQty", String.valueOf(sabawOrderQty));
        args.putString("chickenOrderQty", String.valueOf(chickenOrderQty));

        args.putString("OVERALL_TOTAL", String.valueOf(OVERALL_TOTAL));

        args.putString("SIOMAITOTAL", String.valueOf(SIOMAITOTAL));
        args.putString("ISAWTOTAL", String.valueOf(ISAWTOTAL));
        args.putString("EGGTOTAL", String.valueOf(EGGTOTAL));
        args.putString("SABAWTOTAL", String.valueOf(SABAWTOTAL));
        args.putString("CHICKENTOTAL", String.valueOf(CHICKENTOTAL));

        fragment2.setArguments(args);

        getFragmentManager().beginTransaction().add(R.id.fragmentBox, fragment2).commit();
    }

    private void openChangeOrderDialog() {
        ChangeOrderDialog changeOrderDialog = new ChangeOrderDialog();
        changeOrderDialog.show(getFragmentManager(), "change order dialog");
    }
}

