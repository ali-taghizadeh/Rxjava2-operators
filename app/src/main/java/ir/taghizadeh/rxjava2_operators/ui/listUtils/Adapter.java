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

    public Adapter(Model_Data models) {
        modelList = models;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int position) {
        Model model = modelList.getModelList().get(position);
        TextView text_title = viewHolder.text_item_title;
        TextView text_description = viewHolder.text_item_description;
        Button button_item_run = viewHolder.button_item_run;
        text_title.setText(model.getName());
        text_description.setText(model.getDescription());
        button_item_run.setOnClickListener(view -> {
            Operators operators = EnumOperators.valueOf(model.getEnums()).createOperator();
            compositeDisposable = operators.runOperator();
            new AlertDialog.Builder(context)
                    .setTitle(model.getName())
                    .setMessage(context.getString(R.string.dialog_message, model.getName()))
                    .setPositiveButton("STOP", (dialog, which) -> dispose())
                    .setCancelable(false)
                    .show();
        });
    }

    private void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public int getItemCount() {
        return modelList.getModelList().size();
    }
}
