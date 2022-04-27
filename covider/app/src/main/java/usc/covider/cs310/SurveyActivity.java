package usc.covider.cs310;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.cs310.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import usc.covider.cs310.util.DateUtil;

public class SurveyActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        CollectionReference surveys = FirebaseFirestore.getInstance().collection("surveys");
        CollectionReference users = FirebaseFirestore.getInstance().collection("users");
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "");
        CheckBox hasCovid = findViewById(R.id.has_covid);
        CheckBox hasSymptoms = findViewById(R.id.has_symptoms);
        CheckBox hasContact = findViewById(R.id.has_contact);
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hCovid = hasCovid.isChecked();
                boolean hSymptom = hasSymptoms.isChecked();
                boolean hContact = hasContact.isChecked();
                long dateString = DateUtil.getTodayEpoch();
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                String userID = sharedPreferences.getString("userID", "");
                    users.document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                User u = document.toObject(User.class);
                                Survey s = new Survey(hCovid, hSymptom, hContact, dateString, userID, u.buildings);
                                surveys.add(s).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Intent i = new Intent(SurveyActivity.this, ResultActivity.class);
                                        SurveyActivity.this.startActivity(i);
                                    }
                                });
                            }
                        }
                    });
            }
        });
    }
}
