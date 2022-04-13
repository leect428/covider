package usc.covider.cs310;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import usc.covider.cs310.util.DateUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cs310.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CollectionReference users = FirebaseFirestore.getInstance().collection("users");
        TextView createAccount = findViewById(R.id.signup);
        Button login = findViewById(R.id.login);
        TextView error = findViewById(R.id.error_msg);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                users.whereEqualTo("email", e).whereEqualTo("password", p).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.isSuccessful()  || task.getResult().getDocuments().size() == 0){
                            error.setVisibility(View.VISIBLE);
                            return;
                        }
                        String userID = task.getResult().getDocuments().get(0).getId();
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("userID", userID);
                        myEdit.apply();
                        CollectionReference surveys = FirebaseFirestore.getInstance().collection("surveys");
                        String dateString = DateUtil.getToday();
                        surveys.whereEqualTo("userID", userID).whereEqualTo("date", dateString).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful() && !e.equals("test")){
                                    QuerySnapshot querySnapshot = task.getResult();
                                    if(querySnapshot.getDocuments().size() > 0){
                                        Intent i = new Intent(MainActivity.this, ResultActivity.class);
                                        MainActivity.this.startActivity(i);
                                    } else{
                                        Intent i = new Intent(MainActivity.this, SurveyActivity.class);
                                        MainActivity.this.startActivity(i);
                                    }
                                } else {
                                    Intent i = new Intent(MainActivity.this, SurveyActivity.class);
                                    MainActivity.this.startActivity(i);
                                }
                            }
                        });
                    }
                });
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                MainActivity.this.startActivity(i);
            }
        });
    }

}