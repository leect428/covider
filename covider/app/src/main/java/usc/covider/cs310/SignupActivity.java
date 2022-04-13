package usc.covider.cs310;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import usc.covider.cs310.util.AccountUtil;
import usc.covider.cs310.util.EmailUtil;

public class SignupActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        CollectionReference users = FirebaseFirestore.getInstance().collection("users");
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmpass);
        TextView error = findViewById(R.id.error_signup);

        Button createAccount = findViewById(R.id.signup);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                if(e.length()!=6 && !EmailUtil.isValid(e)){
                    error.setText("Invalid email");
                    error.setVisibility(View.VISIBLE);
                    return;
                }
                String p = password.getText().toString();
                String confirm = confirmPassword.getText().toString();
                if(!confirm.equals(p)){
                    error.setText(AccountUtil.checkPassword(p, confirm));
                    error.setVisibility(View.VISIBLE);
                    return;
                }
                users.whereEqualTo("email", e).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && task.getResult().getDocuments().size() > 0){
                            error.setText(AccountUtil.EMAIL_TAKEN);
                            error.setVisibility(View.VISIBLE);
                            return;
                        }
                        User u = new User(e, p);
                        users.add(u).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String userID = documentReference.getId();
                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("userID", userID);
                                Intent i = new Intent(SignupActivity.this, SurveyActivity.class);
                                SignupActivity.this.startActivity(i);
                            }
                        });
                    }
                });
            }
        });
    }
}
