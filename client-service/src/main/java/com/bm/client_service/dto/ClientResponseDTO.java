package com.bm.client_service.dto;


public class ClientResponseDTO {
    private String id;
    private String name;
    private String email;
    private String tableNumber;
    private String bookDate;

    public ClientResponseDTO() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
}
