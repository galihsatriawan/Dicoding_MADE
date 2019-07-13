package id.shobrun.myviewmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import id.shobrun.myviewmodel.custom.MyButton;
import id.shobrun.myviewmodel.custom.MyEditText;

public class MainActivity extends AppCompatActivity {
    MyButton myButton;
    MyEditText myEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.my_button);
        myEditText = findViewById(R.id.my_edit_text);
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setMyButtonEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,myEditText.getText(),Toast.LENGTH_SHORT);
            }
        });
    }

    private void setMyButtonEnable(){
        Editable result = myEditText.getText();
        if(result != null && !result.toString().isEmpty()){
            myButton.setEnabled(true);
        }else{
            myButton.setEnabled(false);
        }
    }
}
