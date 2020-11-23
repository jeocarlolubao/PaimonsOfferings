package com.example.paimonsofferings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class OrderSummaryFragment extends Fragment {


    TextView Customer_TOTAL;
    TextView UserPayment;
    TextView change;

    TextView siomaiCount;
    TextView isawCount;
    TextView eggCount;
    TextView sabawCount;
    TextView chickenCount;

    TextView siomaiTotal;
    TextView isawTotal;
    TextView eggTotal;
    TextView sabawTotal;
    TextView chickenTotal;

    EditText Payment_VALUE;

    TextView textView_siomai;
    TextView changeVALUE;


    Button SubmitOrder;
    Button ChangeOrder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_summary, container, false);

//        SiomaiText = (TextView) view.findViewById(R.id.textView_siomai);

        Payment_VALUE = (EditText) view.findViewById(R.id.editText_Number_PAYMENT_VALUE);

        textView_siomai = (TextView) view.findViewById(R.id.textView_siomai);

//        CustomerOrders = (TextView) view.findViewById(R.id.textView_CustomerOrders);
        Customer_TOTAL = (TextView) view.findViewById(R.id.textView_TOTAL_VALUE);
        UserPayment = (TextView) view.findViewById(R.id.textView_UserPayment);
        change = (TextView) view.findViewById(R.id.textView_change);
        changeVALUE = (TextView) view.findViewById(R.id.textView_changeVALUE);


        siomaiCount = (TextView) view.findViewById(R.id.textView_siomaiCount);
        isawCount = (TextView) view.findViewById(R.id.textView_isawCount);
        eggCount = (TextView) view.findViewById(R.id.textView_eggCount);
        sabawCount = (TextView) view.findViewById(R.id.textView_sabawCount);
        chickenCount = (TextView) view.findViewById(R.id.textView_chickenCount);

        siomaiTotal = (TextView) view.findViewById(R.id.textView_siomaiTotal);
        isawTotal = (TextView) view.findViewById(R.id.textView_isawTotal);
        eggTotal = (TextView) view.findViewById(R.id.textView_eggTotal);
        sabawTotal = (TextView) view.findViewById(R.id.textView_sabawTotal);
        chickenTotal = (TextView) view.findViewById(R.id.textView_chickenTotal);

        SubmitOrder = (Button) view.findViewById(R.id.button_SubmitOrder);
        ChangeOrder = (Button) view.findViewById(R.id.button_ChangeOrder);


        String siomaiCount, isawCount, eggCount, sabawCount, chickenCount;
        String OVERALL_TOTAL, SIOMAITOTAL, ISAWTOTAL, EGGTOTAL, SABAWTOTAL, CHICKENTOTAL;

        String siomaistate;

//        args.putString("siomaiOrderQty", String.valueOf(siomaiOrderQty));
//        args.putString("isawOrderQty", String.valueOf(isawOrderQty));
//        args.putString("eggOrderQty", String.valueOf(eggOrderQty));
//        args.putString("sabawOrderQty", String.valueOf(sabawOrderQty));
//        args.putString("chickenOrderQty", String.valueOf(chickenOrderQty));
//
//        args.putString("OVERALL_TOTAL", String.valueOf(OVERALL_TOTAL));
//
//        args.putString("SIOMAITOTAL", String.valueOf(SIOMAITOTAL));
//        args.putString("ISAWTOTAL", String.valueOf(ISAWTOTAL));
//        args.putString("EGGTOTAL", String.valueOf(EGGTOTAL));
//        args.putString("SABAWTOTAL", String.valueOf(SABAWTOTAL));
//        args.putString("CHICKENTOTAL", String.valueOf(CHICKENTOTAL));

        siomaiCount = getArguments().getString("siomaiOrderQty");
        isawCount = getArguments().getString("isawOrderQty");
        eggCount = getArguments().getString("eggOrderQty");
        sabawCount = getArguments().getString("sabawOrderQty");
        chickenCount = getArguments().getString("chickenOrderQty");

        OVERALL_TOTAL = getArguments().getString("OVERALL_TOTAL");

        SIOMAITOTAL = getArguments().getString("SIOMAITOTAL");
        ISAWTOTAL = getArguments().getString("ISAWTOTAL");
        EGGTOTAL = getArguments().getString("EGGTOTAL");
        SABAWTOTAL = getArguments().getString("SABAWTOTAL");
        CHICKENTOTAL = getArguments().getString("CHICKENTOTAL");

        siomaistate = getArguments().getString("SiomaiState");


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                double customerpayment = 0;
                double change = 0;

                if (!Payment_VALUE.getText().toString().equalsIgnoreCase("")) {
                    customerpayment = Double.parseDouble(Payment_VALUE.getText().toString().trim());
                    change = customerpayment - Double.parseDouble(OVERALL_TOTAL);
                    changeVALUE.setText(FormatNumber(Double.valueOf(String.valueOf(change))));
                }

                if (Payment_VALUE.getText().toString().isEmpty()) {
                    changeVALUE.setText(FormatNumber(Double.valueOf(String.valueOf(change))));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        Payment_VALUE.addTextChangedListener(textWatcher);


        SubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Payment_VALUE.getText().toString().trim().isEmpty()) {
                    Toast toast = Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (Double.parseDouble(OVERALL_TOTAL) > Double.parseDouble(Payment_VALUE.getText().toString())) {

                        Toast toast = Toast.makeText(getActivity(), "Insufficient Funds", Toast.LENGTH_SHORT);
                        toast.show();

                    } else if (Payment_VALUE.getText().toString().trim().isEmpty()) {
                        Toast toast = Toast.makeText(getActivity(), "Please enter your payment", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        ThankYouFragment thankYouFragment = new ThankYouFragment();
                        getFragmentManager().beginTransaction().add(R.id.fragmentBox, thankYouFragment).commit();
                    }
                }


//
//                if (Double.parseDouble(OVERALL_TOTAL) > Double.parseDouble(Payment_VALUE.getText().toString())) {
//
//                    Toast toast = Toast.makeText(getActivity(), "Insufficient Funds", Toast.LENGTH_SHORT);
//                    toast.show();
//
//                } else if (Payment_VALUE.getText().toString().trim().isEmpty()) {
//                    Toast toast = Toast.makeText(getActivity(), "Please enter your payment", Toast.LENGTH_SHORT);
//                    toast.show();
//                } else {
//                    ThankYouFragment thankYouFragment = new ThankYouFragment();
//
//                    getFragmentManager().beginTransaction().add(R.id.fragmentBox, thankYouFragment).commit();
//                }


            }
        });
        ChangeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChangeOrderDialog();
            }
        });


        textView_siomai.setText(String.format("Jade Siomai\n(%s)", siomaistate));

        this.siomaiCount.setText(String.format("x%s", siomaiCount));
        this.isawCount.setText(String.format("x%s", isawCount));
        this.eggCount.setText(String.format("x%s", eggCount));
        this.sabawCount.setText(String.format("x%s", sabawCount));
        this.chickenCount.setText(String.format("x%s", chickenCount));

        this.siomaiTotal.setText(SIOMAITOTAL);
        this.isawTotal.setText(ISAWTOTAL);
        this.eggTotal.setText(EGGTOTAL);
        this.sabawTotal.setText(SABAWTOTAL);
        this.chickenTotal.setText(CHICKENTOTAL);
        Customer_TOTAL.setText(FormatNumber(Double.valueOf(OVERALL_TOTAL)));

//
//        double total, customerpayment, change;
//        total = Double.parseDouble(OVERALL_TOTAL);
//        customerpayment = Double.parseDouble(Customer_TOTAL.getText().toString());
////                    change = total - customerpayment;
//        textView_changeVALUE.setText(String.valueOf(total - customerpayment));


//        Payment_VALUE.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                double total = Double.parseDouble(OVERALL_TOTAL);
//                double customerpayment = Double.parseDouble(Customer_TOTAL.getText().toString());
//                if (!Payment_VALUE.getText().toString().equals("")){
//                    textView_changeVALUE.setText(String.valueOf(total - customerpayment));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//
//            }
//        });


        return view;

    }

    private String FormatNumber(Double number) {
        String formattedNumber;
        formattedNumber = String.format(Locale.ENGLISH, "%,.2f", number);
        return formattedNumber;
    }

    private void openChangeOrderDialog() {
        ChangeOrderDialog changeOrderDialog = new ChangeOrderDialog();
        changeOrderDialog.show(getFragmentManager(), "change order dialog");
    }

}

