package ir.taghizadeh.rxjava2_operators.ui.listUtils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.model.Model;

class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_item_title)
    TextView text_item_title;
    @BindView(R.id.text_item_description)
    TextView text_item_description;
    @BindView(R.id.button_item_run)
    Button button_item_run;
    @BindView(R.id.card_item)
    CardView card_item;

    private CustomItemClickListener listener;
    private String source;
    private Context context;

    ViewHolder(View itemView, CustomItemClickListener listener, String source) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.listener = listener;
        this.source = source;
        context = itemView.getContext();
    }

    void configureWith(Model model, int position) {
        text_item_title.setText(model.getName());
        text_item_description.setText(model.getDescription());
        if (source.equals(context.getString(R.string.operators))) {
            button_item_run.setOnClickListener(view -> listener.onItemClick(view, position, model));
        } else {
            button_item_run.setVisibility(View.GONE);
            card_item.setOnClickListener(view -> listener.onItemClick(view, position, model));
        }
    }
}
