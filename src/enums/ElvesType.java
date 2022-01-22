package enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public enum ElvesType {
    @SerializedName("yellow")
    @JsonProperty("yellow")
    YELLOW("yellow"),

    @SerializedName("black")
    @JsonProperty("black")
    BLACK("black"),

    @SerializedName("pink")
    @JsonProperty("pink")
    PINK("pink"),

    @SerializedName("white")
    @JsonProperty("white")
    WHITE("white");

    private String value;

    ElvesType(final String value) {
        this.value = value;
    }
}
