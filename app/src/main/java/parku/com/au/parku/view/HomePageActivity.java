package parku.com.au.parku.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import parku.com.au.parku.R;
import parku.com.au.parku.network.RetroClient;
import parku.com.au.parku.network.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity {

    User user;

    private RetroClient retroClient;

    private TextView tvFullname, tvEmail, tvContact, tvUsername;

    String strFullname, strEmail, strContact, strUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        user = new User();

        tvFullname = (TextView) findViewById(R.id.fullname_textView);
        tvEmail = (TextView) findViewById(R.id.email_textView);
        tvContact = (TextView) findViewById(R.id.contact_textView);
        tvUsername = (TextView) findViewById(R.id.username_textView);

        strFullname = tvFullname.getText().toString();
        strEmail = tvEmail.getText().toString();
        strContact = tvContact.getText().toString();
        strUsername = tvUsername.getText().toString();

        retroClient = new RetroClient();

        userData();
        user = new User();

    }

    private void userData(){

        retroClient.getApiService().registerUser(user).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User user = response.body();

                tvFullname.setText(strFullname + user.getFullname());
                tvEmail.setText(strEmail + user.getEmail());
                tvContact.setText(strContact + user.getContactNumber());
                tvUsername.setText(strUsername + user.getUsername());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
