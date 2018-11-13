package ir.taghizadeh.rxjava2_operators.ui.listUtils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.model.Model;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private CustomItemClickListener listener;
    private List<Model> modelList;
    private String source;

    public Adapter(List<Model> models, String source, CustomItemClickListener listener) {
        this.modelList = models;
        this.source = source;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view, listener, source);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Model model= modelList.get(position);
        viewHolder.configureWith(model, position);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
