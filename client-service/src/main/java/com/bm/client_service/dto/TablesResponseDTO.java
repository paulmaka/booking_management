package com.bm.client_service.dto;

/**
 * Класс DTO ответа для отображения доступных столиков в заданное в запросе время.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class TablesResponseDTO {

    private String id;

    public TablesResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
