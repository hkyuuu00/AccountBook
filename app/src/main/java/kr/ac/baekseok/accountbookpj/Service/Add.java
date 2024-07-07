package kr.ac.baekseok.accountbookpj.Service;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import kr.ac.baekseok.accountbookpj.Database.DBHelper;
import kr.ac.baekseok.accountbookpj.R;

public class Add extends MainActivity{
    DatePicker dp;
    EditText amount, history;
    Button addBtn;
    Spinner spinner;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);


        dp = (DatePicker) findViewById(R.id.datePicker1);
        amount = (EditText) findViewById(R.id.UseAmount);
        history = (EditText) findViewById(R.id.UseHistory);
        spinner = (Spinner) findViewById(R.id.spinner1);
        addBtn = (Button) findViewById(R.id.addBtn);
        dbHelper = new DBHelper(this);

        String[] categories = {"식비", "교통", "생활", "의류", "문화/여가", "의료/건강", "교육", "기타"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // DatePicker 현재 날짜로 초기값 설정
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDate = cal.get(Calendar.DATE);

        dp.init(cYear, cMonth, cDate, null);
        
        // 버튼을 누르면 db로 데이터 전송 후 종료
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = dp.getYear();
                int month = dp.getMonth() + 1;
                int day = dp.getDayOfMonth();
                String date = year + "-" + month + "-" + day;
                String category = spinner.getSelectedItem().toString();
                String amountText = amount.getText().toString();
                String historyText = history.getText().toString();

                dbHelper.insertRecord(date, amountText, historyText, category);
                finish();
            }
        });
    }
}
