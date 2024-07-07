package kr.ac.baekseok.accountbookpj.Service;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;


import com.github.mikephil.charting.charts.PieChart;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Calendar;

import kr.ac.baekseok.accountbookpj.Adapter.CategoryAdapter;
import kr.ac.baekseok.accountbookpj.Database.DBHelper;
import kr.ac.baekseok.accountbookpj.R;
import kr.ac.baekseok.accountbookpj.Model.CategoryData;

public class SpendingCategory extends MainActivity {

    PieChart pieChart;
    NumberPicker scYear, scMonth;
    ListView categoryListView;
    DBHelper dbHelper;
    ArrayList<Float> categoryAmounts;
    ArrayList<CategoryData> categoryDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spending_category);

        pieChart = findViewById(R.id.pieChart);
        scMonth = findViewById(R.id.SCNpMonth);
        scYear = findViewById(R.id.SCNpYear);
        categoryListView = findViewById(R.id.categoryListView);
        dbHelper = new DBHelper(this);
        categoryAmounts = new ArrayList<>();
        categoryDataList = new ArrayList<>();

        // 현재 년도와 월을 가져와 설정
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH) + 1;

        //NumberPicker 설정
        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(this, R.array.month_array, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //레이아웃은 스피너 드롭다운으로 설정
        scMonth.setMinValue(1); //월은 1-12월로 설정
        scMonth.setMaxValue(12);
        scMonth.setDisplayedValues(getResources().getStringArray(R.array.month_array));
        scMonth.setValue(currentMonth); //현재 값을 변수로 설정
        scMonth.setWrapSelectorWheel(true); //끝에서 다시 처음으로 돌아오도록 설정

        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this, R.array.year_array, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scYear.setMinValue(1970); //년도는 1970-2100년으로 설정
        scYear.setMaxValue(2100);
        scYear.setValue(currentYear);
        scYear.setWrapSelectorWheel(true);

        //NumberPicker가 변경될 때 마다 loadData() 메소드 호출
        scYear.setOnValueChangedListener((picker, oldVal, newVal) -> loadData());
        scMonth.setOnValueChangedListener((picker, oldVal, newVal) -> loadData());

        // 초기 데이터 로드
        loadData();
    }

    //loadData 메소드는 선택한 날짜에 맞는 DB를 가져와 카테고리별로 그래프와 리스트 보여줌
    private void loadData() {
        String selectedMonth = String.valueOf(scMonth.getValue());
        String selectedYear = String.valueOf(scYear.getValue());

        String datePattern = selectedYear + "-" + selectedMonth + "%";
        Cursor SCcursor = dbHelper.getCategoryTotalAmount(datePattern);

        if (SCcursor != null) {
            ArrayList<PieEntry> entries = new ArrayList<>();
            int totalAmount = 0; // 전체 총 지출액을 저장할 변수 초기화
            categoryDataList.clear();

            // 모든 카테고리 총 지출액을 계산
            if (SCcursor.moveToFirst()) {
                do {
                    int categoryTotalAmount = SCcursor.getInt(SCcursor.getColumnIndexOrThrow(DBHelper.COLUMN_TOTAL_AMOUNT));
                    totalAmount += categoryTotalAmount;
                } while (SCcursor.moveToNext());
            }

            SCcursor.moveToFirst(); // 커서를 다시 처음으로 이동

            // 각 카테고리의 지출액을 비율로 계산
            if (SCcursor.moveToFirst()) {
                do {
                    // 결과에서 카테고리와 해당 카테고리의 총 지출액을 가져옴
                    String category = SCcursor.getString(SCcursor.getColumnIndexOrThrow(DBHelper.COLUMN_CATEGORY));
                    int categoryTotalAmount = SCcursor.getInt(SCcursor.getColumnIndexOrThrow(DBHelper.COLUMN_TOTAL_AMOUNT));

                    // PieEntry 객체를 생성하여 리스트에 추가
                    entries.add(new PieEntry(categoryTotalAmount, category)); // 카테고리와 총 지출액을 라벨로 함께 표시

                    // 카테고리 데이터 리스트에 추가
                    float percent = (categoryTotalAmount / (float) totalAmount) * 100;
                    categoryDataList.add(new CategoryData(category, percent, categoryTotalAmount));
                } while (SCcursor.moveToNext());
            }

            SCcursor.close();

            // PieChart에 데이터 설정
            setPieChartData(entries, totalAmount);

            // ListView에 데이터 설정
            CategoryAdapter adapter = new CategoryAdapter(this, categoryDataList);
            categoryListView.setAdapter(adapter);
        }
    }

    private void setPieChartData(ArrayList<PieEntry> entries, int totalAmount) {
        // PieChart 데이터셋 생성
        PieDataSet dataSet = new PieDataSet(entries, "Categories");
        int[] colors = new int[]{
                Color.rgb(255, 102, 0), // Orange
                Color.rgb(51, 153, 255), // Blue
                Color.rgb(255, 51, 204), // Pink
                Color.rgb(0, 204, 102), // Green
                Color.rgb(204, 0, 102), // Red
                Color.rgb(51, 153, 102), // Teal
                Color.rgb(102, 0, 204), // Purple
                Color.rgb(153, 51, 0) // Brown
        };

        // 색상 배열을 PieDataSet에 설정
        dataSet.setColors(colors);

        // PieData 객체 생성
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart)); // 비율 형식 지정

        // PieChart에 데이터 설정
        pieChart.setData(data);
        pieChart.setUsePercentValues(true); // 데이터 값을 백분율로 표시
        pieChart.setCenterText("Total: ₩" + totalAmount); // 중앙에 전체 총 지출액 표시
        pieChart.setCenterTextSize(14f);
        pieChart.setEntryLabelTextSize(11f);
        pieChart.invalidate(); // 갱신
    }
}

