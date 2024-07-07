package kr.ac.baekseok.accountbookpj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.baekseok.accountbookpj.R;
import kr.ac.baekseok.accountbookpj.Model.CategoryData;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CategoryData> data;
    private LayoutInflater inflater;

    public CategoryAdapter(Context context, ArrayList<CategoryData> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_category, parent, false);
        }

        TextView categoryName = convertView.findViewById(R.id.categoryName);
        TextView categoryPercent = convertView.findViewById(R.id.categoryPercent);
        TextView categoryAmount = convertView.findViewById(R.id.categoryAmount);

        CategoryData categoryData = data.get(position);

        categoryName.setText(categoryData.getCategory());
        categoryPercent.setText(String.format("%.1f%%", categoryData.getPercent()));
        categoryAmount.setText("₩" + categoryData.getAmount());

        return convertView;
    }
}
