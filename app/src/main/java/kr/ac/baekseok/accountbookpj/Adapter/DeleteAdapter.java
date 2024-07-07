package kr.ac.baekseok.accountbookpj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kr.ac.baekseok.accountbookpj.Model.DeleteItem;
import kr.ac.baekseok.accountbookpj.R;

public class DeleteAdapter extends BaseAdapter {

    private Context context;
    private List<DeleteItem> items;

    public DeleteAdapter(Context context, List<DeleteItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_delete, parent, false);
        }

        TextView deleteDate = convertView.findViewById(R.id.deleteDate);
        TextView deleteCategory = convertView.findViewById(R.id.deleteCategory);
        TextView deleteDetail = convertView.findViewById(R.id.deleteDetail);
        TextView deletePrice = convertView.findViewById(R.id.deletePrice); // 가격을 표시할 TextView

        DeleteItem item = items.get(position);

        deleteDate.setText(item.getDeleteDate());
        deleteCategory.setText(item.getDeleteCategory());
        deleteDetail.setText(item.getDeleteDetail());
        deletePrice.setText(item.getDeletePrice()+"원"); // 가격을 표시

        return convertView;
    }


    private static class ViewHolder {
        TextView deleteDate;
        TextView deleteCategory;
        TextView deleteDetail;
        TextView deletePrice;
    }
}
