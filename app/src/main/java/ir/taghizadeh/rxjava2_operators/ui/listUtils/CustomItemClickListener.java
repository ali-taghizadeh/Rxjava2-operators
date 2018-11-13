package ir.taghizadeh.rxjava2_operators.ui.listUtils;

import android.view.View;

import ir.taghizadeh.rxjava2_operators.ui.model.Model;

public interface CustomItemClickListener {
    void onItemClick(View v, int position, Model model);
}
