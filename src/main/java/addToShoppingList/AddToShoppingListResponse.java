package addToShoppingList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "measures",
        "aisle",
        "cost",
        "ingredientId"
})
@Generated("jsonschema2pojo")

public class AddToShoppingListResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pantryItem")
    private Boolean pantryItem;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("ingredientId")
    private Integer ingredientId;
}
