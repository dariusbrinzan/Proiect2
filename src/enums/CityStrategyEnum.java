package enums;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public enum CityStrategyEnum {
    @SerializedName("niceScoreCity")
    @JsonProperty("niceScoreCity")
    NICE_SCORE_CITY("niceScoreCity"),

    @SerializedName("id")
    @JsonProperty("id")
    ID("id"),

    @SerializedName("niceScore")
    @JsonProperty("niceScore")
    NICE_SCORE("niceScore");

    private String value;

    CityStrategyEnum(final String value) {
        this.value = value;
    }
}
