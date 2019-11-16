package com.example.chattingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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


public class LoginFragment extends Fragment {


     private  EditText mTextUsername;
    EditText getmTextPassword;
    Button mButtonLogin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_loginf, container, false);

        final DatabaseHelper db = new DatabaseHelper(getActivity());

        final EditText mTextUsername = v.findViewById(R.id.edittext_username);

        final EditText mTextPassword = v.findViewById(R.id.edittext_password);

       final  Button mButtonLogin = v.findViewById(R.id.button_login);

        TextView mTextViewRegister = v.findViewById(R.id.registerr);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new signupp();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= mTextUsername.getText().toString().trim();
                String pwd= mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);

                if ((user.equals(""))&(pwd.equals(""))) { v.findViewById(R.id.button_login).setBackgroundColor(Color.RED);
                    Toast.makeText(getActivity(),"Enter Username or Password",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (res == true) {
                        Toast.makeText(getActivity(), "Successfull Logged In", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                        Toast.makeText(getActivity(), "Welcome To DeepChatting " + user, Toast.LENGTH_SHORT).show();

                        {

                            Intent i = new Intent(getActivity(), MainnActivity.class);
                            startActivity(i);

                        }
                    } else {
                        v.findViewById(R.id.button_login).setBackgroundColor(Color.RED);
                        Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return v;
    }



}
