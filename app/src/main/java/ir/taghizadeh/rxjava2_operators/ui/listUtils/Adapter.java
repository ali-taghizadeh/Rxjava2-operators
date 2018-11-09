package ir.taghizadeh.rxjava2_operators.ui.listUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.model.Model;
import ir.taghizadeh.rxjava2_operators.ui.model.Model_Data;
import ir.taghizadeh.rxjava2_operators.utils.EnumOperators;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private CompositeDisposable compositeDisposable;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_item_title)
        TextView text_item_title;
        @BindView(R.id.text_item_description)
        TextView text_item_description;
        @BindView(R.id.button_item_run)
        Button button_item_run;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private Model_Data modelList;
    private Context context;
    private String source;

    public Adapter(Model_Data models, String source) {
        modelList = models;
        this.source = source;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int position) {
        Model model;
        TextView text_title = viewHolder.text_item_title;
        TextView text_description = viewHolder.text_item_description;
        Button button_item_run = viewHolder.button_item_run;

        if (source.equals(context.getString(R.string.operators))) {
            model = modelList.getOperators_modelList().get(position);
            Model finalModel = model;
            button_item_run.setOnClickListener(view -> {
                Operators operators = EnumOperators.valueOf(finalModel.getEnums()).createOperator();
                compositeDisposable = operators.runOperator();
                new AlertDialog.Builder(context)
                        .setTitle(finalModel.getName())
                        .setMessage(context.getString(R.string.dialog_message, finalModel.getName()))
                        .setPositiveButton("STOP", (dialog, which) -> dispose())
                        .setCancelable(false)
                        .show();
            });
        }else {
            model = modelList.getSamples_modelList().get(position);
            button_item_run.setVisibility(View.GONE);
        }
        text_title.setText(model.getName());
        text_description.setText(model.getDescription());
    }

    private void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public int getItemCount() {
        int size;
        if (source.equals("operators"))
            size = modelList.getOperators_modelList().size();
        else
            size = modelList.getSamples_modelList().size();
        return size;
    }
}
