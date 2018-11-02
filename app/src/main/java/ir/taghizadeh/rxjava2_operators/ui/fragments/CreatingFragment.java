package ir.taghizadeh.rxjava2_operators.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.listUtils.Adapter;
import ir.taghizadeh.rxjava2_operators.ui.model.Model_Data;
import ir.taghizadeh.rxjava2_operators.utils.JsonHelper;


public class CreatingFragment extends Fragment {

    private Unbinder unbinder;
    Gson gson;
    JsonHelper jsonHelper;

    @BindView(R.id.rv_creating)
    RecyclerView rv_creating;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creating, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        rv_creating.setLayoutManager(new LinearLayoutManager(getActivity()));
        gson = new GsonBuilder().create();
        jsonHelper = new JsonHelper();
        Model_Data model_data = gson.fromJson(jsonHelper.LoadJSONFromAsset("CreatingObservable.json", getActivity()), Model_Data.class);
        Adapter adapter = new Adapter(model_data);
        rv_creating.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}