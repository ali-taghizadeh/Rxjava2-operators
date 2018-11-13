package ir.taghizadeh.rxjava2_operators.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.listUtils.Adapter;
import ir.taghizadeh.rxjava2_operators.ui.model.Model_Data;
import ir.taghizadeh.rxjava2_operators.utils.EnumOperators;
import ir.taghizadeh.rxjava2_operators.utils.JsonHelper;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

public class OperatorsFragment extends Fragment {

    private Unbinder unbinder;

    private CompositeDisposable compositeDisposable;

    Gson gson;
    JsonHelper jsonHelper;
    String source;

    @BindView(R.id.rv_operators)
    RecyclerView rv_operators;
    @BindView(R.id.text_title_operators)
    TextView text_title_operators;

    public OperatorsFragment newInstance(String source) {
        Bundle bundle = new Bundle();
        bundle.putString("source", source);
        OperatorsFragment fragment = new OperatorsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            source = bundle.getString(getString(R.string.source));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operators, container, false);
        unbinder = ButterKnife.bind(this, view);
        readBundle(getArguments());
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        rv_operators.setLayoutManager(new LinearLayoutManager(getActivity()));
        gson = new GsonBuilder().create();
        jsonHelper = new JsonHelper();
        Model_Data model_data = null;
        switch (source) {
            case "Creating":
                model_data = gson.fromJson(jsonHelper.LoadJSONFromAsset("CreatingObservable.json", getActivity()), Model_Data.class);
                text_title_operators.setText(getString(R.string.observable_creating));
                break;
            case "Transforming":
                model_data = gson.fromJson(jsonHelper.LoadJSONFromAsset("TransformingObservable.json", getActivity()), Model_Data.class);
                text_title_operators.setText(getString(R.string.observable_transforming));
                break;
            case "Filtering":
                model_data = gson.fromJson(jsonHelper.LoadJSONFromAsset("FilteringObservable.json", getActivity()), Model_Data.class);
                text_title_operators.setText(getString(R.string.observable_filtering));
                break;
            case "Combining":
                model_data = gson.fromJson(jsonHelper.LoadJSONFromAsset("CombiningObservable.json", getActivity()), Model_Data.class);
                text_title_operators.setText(getString(R.string.observable_combining));
                break;
            case "Conditional":
                model_data = gson.fromJson(jsonHelper.LoadJSONFromAsset("ConditionalObservable.json", getActivity()), Model_Data.class);
                text_title_operators.setText(getString(R.string.observable_conditional));
                break;
        }
        Adapter adapter = new Adapter(model_data, getString(R.string.operators), (v, position, model) -> {
            Operators operators = EnumOperators.valueOf(model.getEnums()).createOperator();
            compositeDisposable = operators.runOperator();
            new AlertDialog.Builder(v.getContext())
                    .setTitle(model.getName())
                    .setMessage(v.getContext().getString(R.string.dialog_message, model.getName()))
                    .setPositiveButton(v.getContext().getString(R.string.stop), (dialog, which) -> dispose())
                    .setCancelable(false)
                    .show();
        });
        rv_operators.setAdapter(adapter);
    }


    private void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}