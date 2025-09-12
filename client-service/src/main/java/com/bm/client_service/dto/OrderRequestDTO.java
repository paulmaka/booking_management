package com.bm.client_service.dto;

/**
 * Класс DTO для запроса создания заказа на блюдо пользователя.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class OrderRequestDTO {

    private String dishName;
    private String bookId;

    public OrderRequestDTO() {
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
