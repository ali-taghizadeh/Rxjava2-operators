package ir.taghizadeh.rxjava2_operators.ui.listUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.model.Model;
import ir.taghizadeh.rxjava2_operators.ui.model.Model_Data;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        TextView text_description;

        ViewHolder(View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_item_title);
            text_description = itemView.findViewById(R.id.text_item_description);
        }
    }

    private Model_Data modelList;

    public Adapter(Model_Data models) {
        modelList = models;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int position) {
        Model model = modelList.getModelList().get(position);
        TextView text_title = viewHolder.text_title;
        TextView text_description = viewHolder.text_description;
        text_title.setText(model.getName());
        text_description.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return modelList.getModelList().size();
    }
}
