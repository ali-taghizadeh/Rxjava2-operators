package ir.taghizadeh.rxjava2_operators.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.listUtils.Adapter;
import ir.taghizadeh.rxjava2_operators.ui.model.Model;
import ir.taghizadeh.rxjava2_operators.utils.EnumActivities;
import ir.taghizadeh.rxjava2_operators.utils.JsonHelper;

public class SamplesActivity extends AppCompatActivity {

    Unbinder unbinder;
    @BindView(R.id.rv_samples)
    RecyclerView rv_samples;
    @BindView(R.id.button_samples)
    Button button_samples;

    Gson gson;
    JsonHelper jsonHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);
        unbinder = ButterKnife.bind(this);
        setupUI();
    }

    private void setupUI() {
        button_samples.setVisibility(View.GONE);
        rv_samples.setLayoutManager(new LinearLayoutManager(this));
        gson = new GsonBuilder().create();
        jsonHelper = new JsonHelper();
        Model[] modelArray = gson.fromJson(jsonHelper.LoadJSONFromAsset("Samples.json", this), Model[].class);
        List<Model> model = Arrays.asList(modelArray);
        Adapter adapter = new Adapter(model, getString(R.string.samples), (v, position, model1) -> rowTapped(v, model1));
        rv_samples.setAdapter(adapter);
    }

    private void rowTapped(View v, Model model1) {
        Activity activity = EnumActivities.valueOf(model1.getEnums()).getActivity();
        Intent intent = new Intent(v.getContext(), activity.getClass());
        v.getContext().startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
