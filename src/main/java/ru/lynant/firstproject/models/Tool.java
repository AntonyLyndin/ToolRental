package ru.lynant.firstproject.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Tool {

    private int tool_id;
    @Size(min = 1, max = 30, message = "name should be > 1 and < 30 characters")
    @NotEmpty(message = "name should not be empty")
    @Pattern(regexp = "[A-Z][a-z]+", message = "First symbol should be UP register, all other subsequent")
    private String name;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 1, max = 30, message = "name should be > 1 and < 30 characters")
    @Pattern(regexp = "[A-Za-z]+", message = "Should not be number")
    private String brand;
    @Min(value = 1, message = "price should be > 0")
    private int price;

    public Tool(int id, String name, String brand, int price) {
        this.tool_id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public Tool() {}

    public int getTool_id() {
        return tool_id;
    }

    public void setTool_id(int tool_id) {
        this.tool_id = tool_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
