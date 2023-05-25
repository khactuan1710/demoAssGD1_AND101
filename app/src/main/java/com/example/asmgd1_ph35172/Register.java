package com.example.asmgd1_ph35172;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    ActivityResultLauncher<Intent> getData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent intent = result.getData();

            int number = intent.getIntExtra("number", 0);

            Toast.makeText(getApplicationContext(), number + "", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edtUsername = findViewById(R.id.edt_username);
        EditText edtPassword = findViewById(R.id.edt_password);
        EditText edtRetypePass = findViewById(R.id.edt_retype_password);

        Button btnRegister = findViewById(R.id.btn_dangky);
        Button btnBack = findViewById(R.id.btn_trove);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                //finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate input data
                String sUserName = edtUsername.getText().toString().trim();
                String sPassword = edtPassword.getText().toString().trim();
                String sRetypePass = edtRetypePass.getText().toString().trim();

                if (sUserName.equals("")) {
                    Toast.makeText(Register.this, "Username khong duoc de trong!", Toast.LENGTH_SHORT).show();
                } else if (sPassword.equals("")) {
                    Toast.makeText(Register.this, "Password khong duoc de trong!", Toast.LENGTH_SHORT).show();
                } else if (sRetypePass.equals("")) {
                    Toast.makeText(Register.this, "Password nhap lai khong duoc de trong!", Toast.LENGTH_SHORT).show();
                } else if (!sRetypePass.equals(sPassword)) {
                    Toast.makeText(Register.this, "Password nhap lai chua dung!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Login.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("username", sUserName);
                    bundle.putString("password", sPassword);

                    intent.putExtras(bundle);

                    getData.launch(intent);

                    //startActivity(intent);
                }
            }
        });

    }
}
