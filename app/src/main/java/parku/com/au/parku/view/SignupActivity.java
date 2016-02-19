package parku.com.au.parku.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import parku.com.au.parku.R;
import parku.com.au.parku.network.RetroClient;
import parku.com.au.parku.network.model.User;

import parku.com.au.parku.util.EncryptPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private RetroClient retroClient;
    private EncryptPassword encryptPassword;

    public EditText etFname, etLname, etEmail, etContact, etUsername, etPassword, etCpassword;
    private Button btnSubmit;

    public String strFname, strLname, strEmail, strContact, strUsername, strPassword, strCpassword,  encryptedPassword;
    String SHAHash;

    public final Pattern PASSWORD_PATTERN = Pattern.compile(
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})"
    );

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        etFname = (EditText) findViewById(R.id.firstName_editText);
        etLname = (EditText) findViewById(R.id.lastName_editText);
        etEmail = (EditText) findViewById(R.id.email_editText);
        etContact = (EditText) findViewById(R.id.contact_editText);
        etUsername = (EditText) findViewById(R.id.signupUsername_editText);
        etPassword = (EditText) findViewById(R.id.signupPassword_editText);
        etCpassword = (EditText) findViewById(R.id.confirmPassword_editText);

        btnSubmit = (Button) findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(this);

        retroClient = new RetroClient();
        encryptPassword = new EncryptPassword();
    }

    public void onClick(View v) {

        // On click submit button
        checkSignupInput();
    }

    private void submitUserData() {

        //Encrypt input password
        encryptedPassword = encryptPassword.computeSHAHash(strPassword);

        //Pass input data to Database
        User user = new User();

        user.setFullname(strFname + "" + strLname);
        user.setEmail(strEmail);
        user.setContactNumber(strContact);
        user.setUsername(strUsername);
        user.setPassword(encryptedPassword);

        // Check connection to the server
        retroClient.getApiService().registerUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Toast.makeText(SignupActivity.this, "Username = " + user.getUsername() + " Password = " + user.getPassword(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "ERROR!!! " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkSignupInput() {

        // Pass input data to a variable
        strFname = etFname.getText().toString();
        strLname = etLname.getText().toString();
        strEmail = etEmail.getText().toString();
        strContact = etContact.getText().toString();
        strUsername = etUsername.getText().toString();
        strPassword = etPassword.getText().toString();
        strCpassword = etCpassword.getText().toString();

        // Check input data
        if (strEmail.isEmpty()) {
            etEmail.setError("Required Field");

            if (strUsername.isEmpty()) {
                etUsername.setError("Required Field");

                if (strPassword.isEmpty() && strCpassword.isEmpty()) {
                    etPassword.setError("Required Field");
                    etCpassword.setError("Required Field");

                } else {
                    etCpassword.setError("Password don't match");
                }

            } else if (strPassword.isEmpty()) {
                etPassword.setError("Required Field");

                if (strCpassword.isEmpty()) {
                    etCpassword.setError("Required Field");
                } else {
                    etCpassword.setError("Password don't match");
                }
            }


        } else if (strUsername.isEmpty()) {
            etUsername.setError("Required Field");

            if (strPassword.isEmpty() && strCpassword.isEmpty()) {
                etPassword.setError("Required Field");
                etCpassword.setError("Required Field");

            } else {
                etCpassword.setError("Password don't match");
            }


        } else if (strPassword.isEmpty() && strCpassword.isEmpty()) {
            etPassword.setError("Required Field");
            etCpassword.setError("Required Field");

        } else if (strCpassword.isEmpty()) {
            etCpassword.setError("Password don't match");

        } else if (!isValidPassword(strPassword)) {
            etPassword.setError("Contains upper and lower case and combination of letters and numbers (6 - 16 characters)");

        } else if (!strPassword.equals(strCpassword)) {
            etCpassword.setError("Password don't match");

        } else if (!isValidEmail(strEmail)) {
            etEmail.setError("Invalid Email");

        } else {

            submitUserData();

            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    // check password pattern
    public boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    // check email pattern
    public boolean isValidEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

}
