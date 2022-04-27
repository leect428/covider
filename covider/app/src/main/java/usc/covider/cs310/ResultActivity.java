package usc.covider.cs310;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cs310.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "");
        CollectionReference surveys = FirebaseFirestore.getInstance().collection("surveys");
        CollectionReference users = FirebaseFirestore.getInstance().collection("users");
        RecyclerView recycler = findViewById(R.id.recycler);
        String[] buildings = {"fertitta", "vkc", "price", "leavey", "tutor", "sal", "phed", "sgm"};
        LocalDate date = LocalDate.now();
        long epochDate = date.toEpochDay();
        users.document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    User u = document.toObject(User.class);
                    surveys.whereGreaterThan("date", epochDate - 3).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Survey> surveys = new ArrayList<Survey>();
                            for(DocumentSnapshot document: queryDocumentSnapshots.getDocuments()){
                                Survey s = document.toObject(Survey.class);
                                surveys.add(s);
                            }
                            List<BuildingResult> results = new ArrayList<>();
                            for(String building: buildings){
                                BuildingResult result = new BuildingResult();
                                result.hasBuilding = u.buildings.contains(building);
                                result.building = building;
                                for(Survey s: surveys){
                                    if(s.buildings!= null && s.buildings.contains(building)){
                                        if(s.hasCovid) result.hasCovidCount++;
                                        if(s.hasSymptom) result.hasSymptomCount++;
                                        if(s.hasContact) result.hasContactCount++;
                                    }
                                }
                                results.add(result);
                            }
                            BuildingResultRecyclerViewAdapter adapter = new BuildingResultRecyclerViewAdapter(ResultActivity.this, results);
                            recycler.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
                            recycler.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }
}
