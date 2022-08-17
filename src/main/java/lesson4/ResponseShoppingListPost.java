package lesson4;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "measures",
        "usages",
        "usageRecipeIds",
        "pantryItem",
        "aisle",
        "cost",
        "ingredientId"
})
@Generated("jsonschema2pojo")
public class ResponseShoppingListPost {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("measures")
    private Measures measures;
    @JsonProperty("usages")
    private List<Object> usages = new ArrayList<Object>();
    @JsonProperty("usageRecipeIds")
    private List<Object> usageRecipeIds = new ArrayList<Object>();
    @JsonProperty("pantryItem")
    private Boolean pantryItem;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("ingredientId")
    private Integer ingredientId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public ResponseShoppingListPost withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public ResponseShoppingListPost withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("measures")
    public Measures getMeasures() {
        return measures;
    }

    @JsonProperty("measures")
    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public ResponseShoppingListPost withMeasures(Measures measures) {
        this.measures = measures;
        return this;
    }

    @JsonProperty("usages")
    public List<Object> getUsages() {
        return usages;
    }

    @JsonProperty("usages")
    public void setUsages(List<Object> usages) {
        this.usages = usages;
    }

    public ResponseShoppingListPost withUsages(List<Object> usages) {
        this.usages = usages;
        return this;
    }

    @JsonProperty("usageRecipeIds")
    public List<Object> getUsageRecipeIds() {
        return usageRecipeIds;
    }

    @JsonProperty("usageRecipeIds")
    public void setUsageRecipeIds(List<Object> usageRecipeIds) {
        this.usageRecipeIds = usageRecipeIds;
    }

    public ResponseShoppingListPost withUsageRecipeIds(List<Object> usageRecipeIds) {
        this.usageRecipeIds = usageRecipeIds;
        return this;
    }

    @JsonProperty("pantryItem")
    public Boolean getPantryItem() {
        return pantryItem;
    }

    @JsonProperty("pantryItem")
    public void setPantryItem(Boolean pantryItem) {
        this.pantryItem = pantryItem;
    }

    public ResponseShoppingListPost withPantryItem(Boolean pantryItem) {
        this.pantryItem = pantryItem;
        return this;
    }

    @JsonProperty("aisle")
    public String getAisle() {
        return aisle;
    }

    @JsonProperty("aisle")
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public ResponseShoppingListPost withAisle(String aisle) {
        this.aisle = aisle;
        return this;
    }

    @JsonProperty("cost")
    public Double getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ResponseShoppingListPost withCost(Double cost) {
        this.cost = cost;
        return this;
    }

    @JsonProperty("ingredientId")
    public Integer getIngredientId() {
        return ingredientId;
    }

    @JsonProperty("ingredientId")
    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public ResponseShoppingListPost withIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ResponseShoppingListPost withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
