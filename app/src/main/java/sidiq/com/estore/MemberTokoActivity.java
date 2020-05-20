package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MemberTokoActivity extends AppCompatActivity {

    CardView cardReqMember, cardDataMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_toko);
        setTitle("Membership");

        cardReqMember = findViewById(R.id.card_request_member);
        cardDataMember = findViewById(R.id.card_data_member);

        cardReqMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberTokoActivity.this, RequestMemberActivity.class));
            }
        });

        cardDataMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberTokoActivity.this, DataMemberActivity.class));
            }
        });
    }
}
