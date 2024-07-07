package kr.ac.baekseok.accountbookpj.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import kr.ac.baekseok.accountbookpj.R;

public class CustomAdapter extends CursorAdapter {
    public CustomAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // 새로운 뷰 생성
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // 데이터를 뷰에 바인딩
        TextView amountTextView = view.findViewById(R.id.amountTextView);
        TextView historyTextView = view.findViewById(R.id.historyTextView);
        TextView categoryTextView = view.findViewById(R.id.categoryTextView);

        String amount = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
        String history = cursor.getString(cursor.getColumnIndexOrThrow("history"));
        String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));

        amountTextView.setText(amount + "원");
        historyTextView.setText(history);
        categoryTextView.setText(category);
    }
}
