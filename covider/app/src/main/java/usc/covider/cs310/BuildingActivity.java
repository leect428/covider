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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BuildingActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildings);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        CollectionReference users = FirebaseFirestore.getInstance().collection("users");

        CheckBox fertitta = findViewById(R.id.fertitta_hall);
        CheckBox leavey = findViewById(R.id.leavey_library);
        CheckBox vkc = findViewById(R.id.vkc);
        CheckBox price = findViewById(R.id.price);
        CheckBox tutor = findViewById(R.id.tutor_hall);
        CheckBox sal = findViewById(R.id.sal);
        CheckBox phed = findViewById(R.id.phed);
        CheckBox sgm = findViewById(R.id.sgm);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "");
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            List<String> buildings = new ArrayList<>();
                            if(fertitta.isChecked()) buildings.add("fertitta");
                            if(leavey.isChecked()) buildings.add("leavey");
                            if(vkc.isChecked()) buildings.add("vkc");
                            if(price.isChecked()) buildings.add("price");
                            if(tutor.isChecked()) buildings.add("tutor");
                            if(sal.isChecked()) buildings.add("sal");
                            if(phed.isChecked()) buildings.add("phed");
                            if(sgm.isChecked()) buildings.add("sgm");
                            DocumentSnapshot document = task.getResult();
                            User u = document.toObject(User.class);
                            u.setBuildings(buildings);
                            users.document(userID).set(u).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent i = new Intent(BuildingActivity.this, SurveyActivity.class);
                                    BuildingActivity.this.startActivity(i);
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
