package kr.ac.baekseok.accountbookpj.Service;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.ac.baekseok.accountbookpj.Adapter.RecordAdapter;
import kr.ac.baekseok.accountbookpj.Database.DBHelper;
import kr.ac.baekseok.accountbookpj.Model.Record;
import kr.ac.baekseok.accountbookpj.R;

public class MonthlyExpenses extends MainActivity {
    NumberPicker month, year;
    ListView listView;
    TextView monthPrice;
    RecordAdapter recordAdapter;
    DBHelper dbHelper;
    List<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_expenses);

        month = findViewById(R.id.MeNpMonth);
        year = findViewById(R.id.MeNpYear);
        listView = findViewById(R.id.listView);
        monthPrice = findViewById(R.id.MonthPrice);

        dbHelper = new DBHelper(this);
        records = new ArrayList<>();
        recordAdapter = new RecordAdapter(this, records);
        listView.setAdapter(recordAdapter);

        // 현재 년도와 월을 가져와 설정
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작하므로 +1 해줍니다.

        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(this, R.array.month_array, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setMinValue(1); // Set minimum value for month picker
        month.setMaxValue(12); // Set maximum value for month picker
        month.setDisplayedValues(getResources().getStringArray(R.array.month_array)); // Set month names
        month.setValue(currentMonth); // Set default value for month picker
        month.setWrapSelectorWheel(true); // Enable wrapping for month picker

        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this, R.array.year_array, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setMinValue(1970); // Set minimum value for year picker
        year.setMaxValue(2100); // Set maximum value for year picker
        year.setValue(currentYear); // Set default value for year picker
        year.setWrapSelectorWheel(true); // Enable wrapping for year picker
        year.setOnValueChangedListener((picker, oldVal, newVal) -> loadData()); // Load data when year value changes

        month.setOnValueChangedListener((picker, oldVal, newVal) -> loadData()); // Load data when month value changes

        // 초기 데이터 로드
        loadData();
    }

    private void loadData() {
        String selectedMonth = String.valueOf(month.getValue());
        String selectedYear = String.valueOf(year.getValue());

        String datePattern = selectedYear + "-" + selectedMonth + "%";
        Cursor MonthCursor = dbHelper.getDataByDatePattern(datePattern);

        int totalMonthAmount = (int) dbHelper.getTotalAmountByMonth(datePattern);
        monthPrice.setText("월 사용 금액: " + totalMonthAmount + "원");

        if (MonthCursor != null) {
            if (MonthCursor.moveToFirst()) {
                records.clear();
                do {
                    String date = MonthCursor.getString(MonthCursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATE));
                    String category = MonthCursor.getString(MonthCursor.getColumnIndexOrThrow(DBHelper.COLUMN_CATEGORY));
                    String history = MonthCursor.getString(MonthCursor.getColumnIndexOrThrow(DBHelper.COLUMN_HISTORY));
                    String amount = MonthCursor.getString(MonthCursor.getColumnIndexOrThrow(DBHelper.COLUMN_AMOUNT));
                    records.add(new Record(date, category, history, amount));
                } while (MonthCursor.moveToNext());
            } else {
                // 해당 월에 결과가 없는 경우, 리스트를 클리어합니다.
                records.clear();
            }

            recordAdapter.notifyDataSetChanged();
            MonthCursor.close();
        }
    }
}