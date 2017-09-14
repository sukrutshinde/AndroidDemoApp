package com.android.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by Sukrut on 9/14/2017.
 */

public class GoogleSignin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton signInButton;
    LinearLayout linearLayout;
    Button signout;
    TextView name,email;
    ImageView imageView;
    GoogleApiClient googleApiClient;
    private  static final int REQ_CODE=100;
    ActionBar ab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlesignin);
        ab=getSupportActionBar();
        ab.setTitle("Google SignIn");
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab=getSupportActionBar();
        signInButton= (SignInButton) findViewById(R.id.signinbutton);
        linearLayout= (LinearLayout) findViewById(R.id.layout1);
        signout= (Button) findViewById(R.id.signout);
        name= (TextView) findViewById(R.id.personname);
        email= (TextView) findViewById(R.id.personemail);
        imageView= (ImageView) findViewById(R.id.profile_pic);
        linearLayout.setVisibility(View.GONE);
        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient= new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   signIn();
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   signOut();
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn()
    {
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }
    private void signOut()
    {

     Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
         @Override
         public void onResult(@NonNull Status status) {
             updateUI(false);
         }
     });
    }

    private void updateUI(boolean isLogin)
    {
        if(isLogin)
        {
            linearLayout.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
        }
        else
        {
            linearLayout.setVisibility(View.GONE);
            signInButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private  void handleResult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            GoogleSignInAccount googleSignInAccount=result.getSignInAccount();
            String nameofperson= googleSignInAccount.getDisplayName();
            String emailofperson=googleSignInAccount.getEmail();
            String img_url= googleSignInAccount.getPhotoUrl().toString();
            name.setText("Name : "+nameofperson);
            email.setText("Email Id: "+emailofperson);
            Glide.with(this).load(img_url).into(imageView);
            updateUI(true);


        }
        else
        {
            updateUI(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE)
        {
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
