package ir.taghizadeh.rxjava2_operators.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_Data {

    @SerializedName("operators")
    private List<Model> modelList;

    public List<Model> getModelList() {
        return modelList;
    }
}
