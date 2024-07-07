package kr.ac.baekseok.accountbookpj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import kr.ac.baekseok.accountbookpj.R;
import kr.ac.baekseok.accountbookpj.Model.Record;

public class RecordAdapter extends ArrayAdapter<Record> {
    public RecordAdapter(Context context, List<Record> records) {
        super(context, 0, records);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Record record = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.record_item, parent, false);
        }

        TextView txDate = convertView.findViewById(R.id.MeTxDate);
        TextView txCategory = convertView.findViewById(R.id.MeTxCategory);
        TextView txDetail = convertView.findViewById(R.id.MeTxDetail);
        TextView txPrice = convertView.findViewById(R.id.MeTxPrice);

        if (record != null) {
            txDate.setText(record.getDate());
            txCategory.setText(record.getCategory());
            txDetail.setText(record.getHistory());
            txPrice.setText("₩"+record.getAmount());
        }

        return convertView;
    }
}