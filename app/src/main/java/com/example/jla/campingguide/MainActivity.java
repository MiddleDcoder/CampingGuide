package com.example.jla.campingguide;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button LogInButton, RegisterButton;
    EditText Email, Password;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteOpenHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND";
    public static final  String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = (Button) findViewById(R.id.buttonLogin);

        RegisterButton = (Button) findViewById(R.id.buttonRegister);

        Email = (EditText) findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);

        sqLiteHelper = new SQLiteHelper(this);

        //Adding click listener to login button.
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling EdiText is empty or no method.
                CheckEditTextStatus();
                //Calling Login method.
                LoginFunction();

            }
        });

        //Adding click Listener to register Button.
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    // login function start from here.
    public void LoginFunction(){

        if(EditTextEmptyHolder){
            //Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            //Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()){

                if(cursor.isFirst()){

                    cursor.moveToFirst();

                    //Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));

                    //closing cursor.
                    cursor.close();

                }



            }

            //Calling method to check final result . .
            CheckFinalResult();


        }
        else{

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(MainActivity.this, "Please Enter UserName or Password", Toast.LENGTH_LONG).show();

        }

    }

    //Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        //Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        //Checking EditText is empty or no using TextUtils.
        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false;

        }
        else{
            EditTextEmptyHolder = true;
        }

    }

    // Checking entered password from SQLite database email associated password.
    public  void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder)){

            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, MainActivityDashboard.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);
            EmptyEditTextAfterDataInsert();
            startActivity(intent);


        }
        else{
            Toast.makeText(MainActivity.this, "UserName or Password is Wrong, Please Try Again.", Toast.LENGTH_LONG).show();

        }

        TempPassword = "NOT_FOUND" ;
    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){

        Email.getText().clear();

        Password.getText().clear();


    }

}
