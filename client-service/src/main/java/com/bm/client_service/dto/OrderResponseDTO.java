package com.bm.client_service.dto;

/**
 * Класс DTO для ответа на запрос создания заказа блюда пользователя.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class OrderResponseDTO {

    private String cost;
    private String category;
    private String dishName;
    private String description;

    public OrderResponseDTO() {
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
