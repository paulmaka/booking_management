package com.bm.client_service.dto;

/**
 * Класс DTO для запроса столиков доступных в это время.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class TablesRequestDTO {

    private String bookTime;

    public TablesRequestDTO() {
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }
}
