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

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.listUtils.Adapter;
import ir.taghizadeh.rxjava2_operators.ui.model.Model;
import ir.taghizadeh.rxjava2_operators.utils.EnumOperators;
import ir.taghizadeh.rxjava2_operators.utils.JsonHelper;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

public class OperatorsFragment extends Fragment {

    Gson gson;
    JsonHelper jsonHelper;
    String source;
    @BindView(R.id.rv_operators)
    RecyclerView rv_operators;
    @BindView(R.id.text_title_operators)
    TextView text_title_operators;
    private Unbinder unbinder;
    private CompositeDisposable compositeDisposable;

    public OperatorsFragment newInstance(String source) {
        Bundle bundle = new Bundle();
        bundle.putString("source", source);
        OperatorsFragment fragment = new OperatorsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operators, container, false);
        unbinder = ButterKnife.bind(this, view);
        readBundle(getArguments());
        setupUI();
        return view;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            source = bundle.getString(getString(R.string.source));
        }
    }

    private void setupUI() {
        rv_operators.setLayoutManager(new LinearLayoutManager(getActivity()));
        gson = new GsonBuilder().create();
        jsonHelper = new JsonHelper();
        Model[] modelList = null;
        switch (source) {
            case "Creating":
                modelList = gson.fromJson(jsonHelper.LoadJSONFromAsset("CreatingObservable.json", getActivity()), Model[].class);
                text_title_operators.setText(getString(R.string.observable_creating));
                break;
            case "Transforming":
                modelList = gson.fromJson(jsonHelper.LoadJSONFromAsset("TransformingObservable.json", getActivity()), Model[].class);
                text_title_operators.setText(getString(R.string.observable_transforming));
                break;
            case "Filtering":
                modelList = gson.fromJson(jsonHelper.LoadJSONFromAsset("FilteringObservable.json", getActivity()), Model[].class);
                text_title_operators.setText(getString(R.string.observable_filtering));
                break;
            case "Combining":
                modelList = gson.fromJson(jsonHelper.LoadJSONFromAsset("CombiningObservable.json", getActivity()), Model[].class);
                text_title_operators.setText(getString(R.string.observable_combining));
                break;
            case "Conditional":
                modelList = gson.fromJson(jsonHelper.LoadJSONFromAsset("ConditionalObservable.json", getActivity()), Model[].class);
                text_title_operators.setText(getString(R.string.observable_conditional));
                break;
        }
        assert modelList != null;
        List<Model> model = Arrays.asList(modelList);
        Adapter adapter = new Adapter(model, getString(R.string.operators), (v, position, model1) -> rowTapped(v, model1));
        rv_operators.setAdapter(adapter);
    }

    private void rowTapped(View v, Model model) {
        Operators operators = EnumOperators.valueOf(model.getEnums()).createOperator();
        compositeDisposable = operators.runOperator();
        new AlertDialog.Builder(v.getContext())
                .setTitle(model.getName())
                .setMessage(v.getContext().getString(R.string.dialog_message, model.getName()))
                .setPositiveButton(v.getContext().getString(R.string.stop), (dialog, which) -> dispose())
                .setCancelable(false)
                .show();
    }

    private void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        dispose();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}