package ares.kiefer.parare.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ares.kiefer.parare.R;

public class EditProfessor extends AppCompatActivity{

    EditText editText;
    Button btnDone;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.string_input_screen);

        btnDone = (Button) findViewById(R.id.string_button_done);
        editText = (EditText) findViewById(R.id.string_edit_text);

        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent data = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("prof_name", editText.getText().toString());
            //    data.putExtra("prof_name", editText.getText().toString());
                data.putExtras(bundle);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
