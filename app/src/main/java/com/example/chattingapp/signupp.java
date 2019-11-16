package com.example.chattingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;


public class signupp extends Fragment {


    private  EditText mTextUsername;
    EditText getmTextPassword;
    Button mButtonLogin;

    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_signupp, container, false);

            final EditText mTextUsername2 = v.findViewById(R.id.edittext_username2);

        final EditText mTextPassword2 = v.findViewById(R.id.edittext_password2);
        final TextView already= v.findViewById(R.id.already);

        final EditText mTextCnfPassword2 = v.findViewById(R.id.edittext_cpassword2);

        final EditText mTextEmail2 = v.findViewById(R.id.edittext_email2);

        final EditText mTextMobile2 = v.findViewById(R.id.edittext_mobile2);

        final Button mButtonRegister2 = v.findViewById(R.id.button_register2);

        final DatabaseHelper db = new DatabaseHelper(getActivity());

        mButtonRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 awesomeValidation.addValidation(getActivity(), R.id.edittext_username2, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
                awesomeValidation.addValidation(getActivity(), R.id.edittext_email2, Patterns.EMAIL_ADDRESS, R.string.emailerror);
                awesomeValidation.addValidation(getActivity(), R.id.edittext_password2, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
                awesomeValidation.addValidation(getActivity(), R.id.edittext_mobile2, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
                String user = mTextUsername2.getText().toString().trim();
                String pwd = mTextPassword2.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword2.getText().toString().trim();

                if (pwd.equals(cnf_pwd)){

                    if (awesomeValidation.validate()) {




                    long val= db.addUser(user,pwd);
                    if (val>0) {    Toast.makeText(getActivity(),"You Have Registered Successfully",Toast.LENGTH_SHORT).show();
                        Fragment fragment = new LoginFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit(); }


                }   else { v.findViewById(R.id.button_register2).setBackgroundColor(Color.RED);
                        Toast.makeText(getActivity(),"Registration Failed Check Your Details",Toast.LENGTH_SHORT).show();
                    }
                }
               else{ Toast.makeText(getActivity(),"Password Did Not Matching",Toast.LENGTH_SHORT).show();}



            }


        });

        already.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment1 = new LoginFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }



}
