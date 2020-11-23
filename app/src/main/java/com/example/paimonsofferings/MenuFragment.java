package com.example.paimonsofferings;

import android.app.AlertDialog;
import android.bluetooth.le.AdvertiseData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.NumberPicker;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    Button OrderButton;

//    EditText Siomai_VALUE;
//    EditText Isaw_VALUE;
//    EditText Egg_VALUE;
//    EditText Sabaw_VALUE;
//    EditText Chicken_VALUE;

//    EditText Payment_VALUE;

    CheckBox siomai;
    CheckBox isaw;
    CheckBox egg;
    CheckBox sabaw;
    CheckBox chicken;

    com.shawnlin.numberpicker.NumberPicker siomaiCount;
    com.shawnlin.numberpicker.NumberPicker isawCount;
    com.shawnlin.numberpicker.NumberPicker eggCount;
    com.shawnlin.numberpicker.NumberPicker sabawCount;
    com.shawnlin.numberpicker.NumberPicker chickenCount;


    Spinner BundleSpinner;

//    int checkCounter = 0;

    String SiomaiOrder = "", IsawOrder = "", EggOrder = "", SabawOrder = "", ChickenOrder = "", OverallOrder;
    double SiomaiOrder_TOTAL = 0, IsawOrder_TOTAL = 0, EggOrder_TOTAL = 0, SabawOrder_TOTAL = 0, ChickenOrder_TOTAL = 0, OVERALL_TOTAL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_menu, container, false);


        OrderButton = (Button) view.findViewById(R.id.button_OrderNow);

        siomaiCount = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.number_picker_siomaiCount);
        isawCount = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.number_picker_isawCount);
        eggCount = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.number_picker_eggCount);
        sabawCount = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.number_picker_sabawCount);
        chickenCount = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.number_picker_chickenCount);


        siomai = (CheckBox) view.findViewById(R.id.checkBox_siomai);
        isaw = (CheckBox) view.findViewById(R.id.checkBox_isaw);
        egg = (CheckBox) view.findViewById(R.id.checkBox_egg);
        sabaw = (CheckBox) view.findViewById(R.id.checkBox_sabaw);
        chicken = (CheckBox) view.findViewById(R.id.checkBox_chicken);

        BundleSpinner = (Spinner) view.findViewById(R.id.spinner_BundleSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.SiomaiBundles, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        BundleSpinner.setAdapter(adapter);

        siomaiCount.setValue(0);
        isawCount.setValue(0);
        eggCount.setValue(0);
        sabawCount.setValue(0);
        chickenCount.setValue(0);


        OrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyOrders();
                ParseOrders();
                BundleSpinner.setEnabled(false);
                BundleSpinner.setClickable(false);
                BundleSpinner.setAdapter(adapter);
            }

        });

        return view;
    }

    private void ParseOrders() {
        ReviewOrderFragment fragment1 = new ReviewOrderFragment();
//        MenuFragment fragment1 = new MenuFragment();
        Bundle args = new Bundle();

        if (siomai.isChecked()) {
            if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Bundle")) {
                openNoSiomaiBundleDialog();
            } else if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("4pcs for 9.00")) {
                SiomaiOrder_TOTAL = 9.00 * siomaiCount.getValue();
            } else if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("8pcs for 15.00")) {
                SiomaiOrder_TOTAL = 15.00 * siomaiCount.getValue();
            } else if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("12pcs for 25.00")) {
                SiomaiOrder_TOTAL = 25.00 * siomaiCount.getValue();
            }

            if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Bundle") && siomaiCount.getValue() == 0) {
                openNoSiomaiBundleDialog();
                openAlertOrderDialog();
            } else if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("4pcs for 9.00") && siomaiCount.getValue() == 0) {
                openAlertOrderDialog();
            } else if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("8pcs for 15.00") && siomaiCount.getValue() == 0) {
                openAlertOrderDialog();
            } else if (siomai.isChecked() && BundleSpinner.getSelectedItem().toString().equalsIgnoreCase("12pcs for 25.00") && siomaiCount.getValue() == 0) {
                openAlertOrderDialog();
            }

        } else if (!siomai.isChecked() && siomaiCount.getValue() > 0) {
            openAlertOrderDialog();
        } else {
            SiomaiOrder_TOTAL = 0;
        }


        if (isaw.isChecked()) {
            IsawOrder_TOTAL = IsawOrder_TOTAL = 5.00 * isawCount.getValue();
        } else if (!isaw.isChecked() && isawCount.getValue() > 0) {
            openAlertOrderDialog();
        } else {
            IsawOrder_TOTAL = 0;
        }

        if (isaw.isChecked() && isawCount.getValue() <= 0) {
            openAlertOrderDialog();
        }


        if (egg.isChecked()) {
            EggOrder_TOTAL = 15.00 * eggCount.getValue();
        } else if (!egg.isChecked() && eggCount.getValue() > 0) {
            openAlertOrderDialog();
        } else {
            EggOrder_TOTAL = 0;
        }
        if (egg.isChecked() && eggCount.getValue() == 0) {
            openAlertOrderDialog();
        }


        if (sabaw.isChecked()) {
            SabawOrder_TOTAL = 25.00 * sabawCount.getValue();
        } else if (!sabaw.isChecked() && sabawCount.getValue() > 0) {
            openAlertOrderDialog();
        } else {
            SabawOrder_TOTAL = 0;
        }
        if (sabaw.isChecked() && sabawCount.getValue() == 0) {
            openAlertOrderDialog();
        }

        if (chicken.isChecked()) {
            ChickenOrder_TOTAL = 40.00 * chickenCount.getValue();
        } else if (!chicken.isChecked() && chickenCount.getValue() > 0) {
            openAlertOrderDialog();
        } else {
            ChickenOrder_TOTAL = 0;
        }
        if (chicken.isChecked() && chickenCount.getValue() == 0) {
            openAlertOrderDialog();
        }

        OVERALL_TOTAL = SiomaiOrder_TOTAL + IsawOrder_TOTAL + EggOrder_TOTAL + SabawOrder_TOTAL + ChickenOrder_TOTAL;


        args.putString("OVERALL_TOTAL", String.valueOf(FormatNumber(OVERALL_TOTAL)));


//        get 5 orders and their respective values
        args.putString("SiomaiCount", String.valueOf(siomaiCount.getValue()));
        args.putString("IsawCount", String.valueOf(isawCount.getValue()));
        args.putString("EggCount", String.valueOf(eggCount.getValue()));
        args.putString("SabawCount", String.valueOf(sabawCount.getValue()));
        args.putString("ChickenCount", String.valueOf(chickenCount.getValue()));

//        total per order
        args.putString("SiomaiOrder", String.valueOf(SiomaiOrder_TOTAL));
        args.putString("IsawOrder", String.valueOf(IsawOrder_TOTAL));
        args.putString("EggOrder", String.valueOf(EggOrder_TOTAL));
        args.putString("SabawOrder", String.valueOf(SabawOrder_TOTAL));
        args.putString("ChickenOrder", String.valueOf(ChickenOrder_TOTAL));

        args.putString("BundleChoice", BundleSpinner.getSelectedItem().toString());


        fragment1.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.fragmentBox, fragment1).commit();


    }

    private void VerifyOrders() {
        if (!siomai.isChecked() && !isaw.isChecked() && !egg.isChecked() && !sabaw.isChecked() && !chicken.isChecked()) {
            openAlertOrderDialog();
        }
    }


    private String FormatNumber(Double number) {
        String formattedNumber;
        formattedNumber = String.format(Locale.ENGLISH, "%,.2f", number);
        return formattedNumber;
    }

    private void openAlertOrderDialog() {
        AlertOrderDialog alertOrderDialog = new AlertOrderDialog();
        alertOrderDialog.show(getFragmentManager(), "alert order dialog");
    }

    private void openNoSiomaiBundleDialog() {
        NoSiomaiBundleDialog noSiomaiBundleDialog = new NoSiomaiBundleDialog();
        noSiomaiBundleDialog.show(getFragmentManager(), "no siomai bundle dialog");
    }

}