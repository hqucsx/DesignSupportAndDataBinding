package com.hqucsx.databindinganddesignsupport;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by csx on 15/6/7.
 */
public class WidgetTestActivity extends AppCompatActivity {

    @InjectView(R.id.btn_snackbar_test)
    Button btnSnackbarTest;
    @InjectView(R.id.til_test)
    TextInputLayout tilTest;
    @InjectView(R.id.fb_test)
    FloatingActionButton fbTest;

    EditText et_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets);
        ButterKnife.inject(this);


        et_test = tilTest.getEditText();
        tilTest.setHint("用户名");


        et_test.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() < 3) {
                    tilTest.setError("长度错误");
                    tilTest.setErrorEnabled(true);
                } else
                    tilTest.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.btn_snackbar_test, R.id.fb_test})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_snackbar_test:
                Snackbar.make(btnSnackbarTest, "Snack Test", Snackbar.LENGTH_SHORT)
                        .setAction("SnackBar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(WidgetTestActivity.this, "哈哈是", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                break;
            case R.id.fb_test:
                Snackbar.make(fbTest, "SnackBar Test", Snackbar.LENGTH_SHORT)
                        .setAction("FloatingActionButton", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(WidgetTestActivity.this, "哈哈是", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                break;
        }
    }
}
