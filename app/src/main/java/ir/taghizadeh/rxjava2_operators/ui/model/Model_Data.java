package ir.taghizadeh.rxjava2_operators.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_Data {

    @SerializedName("operators")
    private List<Model> operators_modelList;

    @SerializedName("samples")
    private List<Model> samples_modelList;

    public List<Model> getOperators_modelList() {
        return operators_modelList;
    }

    public List<Model> getSamples_modelList() {
        return samples_modelList;
    }
}
