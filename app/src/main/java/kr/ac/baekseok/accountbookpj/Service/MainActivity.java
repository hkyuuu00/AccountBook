package kr.ac.baekseok.accountbookpj.Service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import kr.ac.baekseok.accountbookpj.Adapter.CustomAdapter;
import kr.ac.baekseok.accountbookpj.Database.DBHelper;
import kr.ac.baekseok.accountbookpj.R;


public class MainActivity extends AppCompatActivity {

    CalendarView calendar;
    ListView listView;
    Button mainAddBtn;
    DBHelper dbHelper;
    CustomAdapter customAdapter;
    TextView today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendar);
        listView = findViewById(R.id.listView);
        mainAddBtn = findViewById(R.id.mainAddBtn);
        today = findViewById(R.id.today);
        dbHelper = new DBHelper(this);
        customAdapter = new CustomAdapter(this, null); // CustomAdapter 클래스는 아래에서 설명할 예정

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(customAdapter);

        // 캘린더뷰에서 날짜 선택 이벤트 처리
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                displayData(selectedDate);
                int MaintotalAmount = (int) dbHelper.getTotalAmountByDate(selectedDate);
                today.setText("하루 사용 금액: " + MaintotalAmount + "원");
            }
        });

        // 버튼을 누르면 add창으로 넘어가기
        mainAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Add.class));
            }
        });
    }

    // 선택된 날짜에 해당하는 데이터를 표시하는 메서드
    private void displayData(String selectedDate) {
        Cursor mainCursor = dbHelper.getDataByDate(selectedDate);
        customAdapter.swapCursor(mainCursor); // 어댑터에 새로운 커서 설정
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.monthlyExpenses) {
            Intent intent = new Intent(this, MonthlyExpenses.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.spendingCategory) {
            Intent intent = new Intent(this, SpendingCategory.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.delete) {
            Intent intent = new Intent(this, Delete.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }
}



