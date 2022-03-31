package usc.covider.cs310;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cs310.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "");
        CollectionReference surveys = FirebaseFirestore.getInstance().collection("surveys");

        TextView covidcount = findViewById(R.id.numcovid);
        TextView symptomcount= findViewById(R.id.numsymptoms);
        TextView contactcount = findViewById(R.id.numcontact);

        surveys.whereEqualTo("hasCovid", true).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int count = queryDocumentSnapshots.getDocuments().size();
                covidcount.setText(count + " people have COVID");
            }
        });
        surveys.whereEqualTo("hasSymptom", true).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int count = queryDocumentSnapshots.getDocuments().size();
                symptomcount.setText(count + " people have symptoms");
            }
        });
        surveys.whereEqualTo("hasContact", true).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int count = queryDocumentSnapshots.getDocuments().size();
                contactcount.setText(count + " people have contact");
            }
        });
    }
}
