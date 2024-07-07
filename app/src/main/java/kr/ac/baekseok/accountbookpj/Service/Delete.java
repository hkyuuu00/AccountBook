package kr.ac.baekseok.accountbookpj.Service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.ac.baekseok.accountbookpj.Adapter.DeleteAdapter;
import kr.ac.baekseok.accountbookpj.Database.DBHelper;
import kr.ac.baekseok.accountbookpj.Model.DeleteItem;
import kr.ac.baekseok.accountbookpj.R;

public class Delete extends MainActivity {

    ListView deleteListView;
    DBHelper dbHelper;
    List<DeleteItem> dataList;
    DeleteAdapter deleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        deleteListView = findViewById(R.id.deleteListView);
        dbHelper = new DBHelper(this);

        loadData();

        deleteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
            }
        });
    }

    private void loadData() {
        Cursor deleteCursor = dbHelper.getAllData();
        dataList = new ArrayList<>();

        if (deleteCursor != null) {
            int deleteTotalAmount = 0;
            List<DeleteItem> tempList = new ArrayList<>();

            if (deleteCursor.moveToFirst()) {
                do {
                    String date = deleteCursor.getString(deleteCursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATE));
                    String category = deleteCursor.getString(deleteCursor.getColumnIndexOrThrow(DBHelper.COLUMN_CATEGORY));
                    String detail = deleteCursor.getString(deleteCursor.getColumnIndexOrThrow(DBHelper.COLUMN_HISTORY));
                    int amount = deleteCursor.getInt(deleteCursor.getColumnIndexOrThrow(DBHelper.COLUMN_AMOUNT));
                    deleteTotalAmount += amount;

                    tempList.add(new DeleteItem(date, category, detail, String.valueOf(amount))); // 가격 정보 추가
                } while (deleteCursor.moveToNext());
            }
            deleteCursor.close();

            // Calculate percent and add to dataList
            for (DeleteItem item : tempList) {
                float percent = (Float.parseFloat(item.getDeletePrice()) / deleteTotalAmount) * 100;
                item = new DeleteItem(item.getDeleteDate(), item.getDeleteCategory(), item.getDeleteDetail(), item.getDeletePrice()); // 기존 가격 정보를 유지
                dataList.add(item);
            }
        }

        deleteAdapter = new DeleteAdapter(this, dataList);
        deleteListView.setAdapter(deleteAdapter);
    }

    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("삭제 확인")
                .setMessage("이 항목을 삭제하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(position);
                    }
                })
                .setNegativeButton("아니오", null)
                .show();
    }

    private void deleteItem(int position) {
        DeleteItem selectedItem = dataList.get(position);
        String category = selectedItem.getDeleteCategory();

        dbHelper.deleteData(category);
        dataList.remove(position);
        deleteAdapter.notifyDataSetChanged();

        Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
