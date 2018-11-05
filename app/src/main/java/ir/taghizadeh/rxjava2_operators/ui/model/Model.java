package ir.taghizadeh.rxjava2_operators.ui.model;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("enum")
    private String enums;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public String getEnums() {
        return enums;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
