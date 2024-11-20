package io.github.neharoshni.demo.models.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class Order {
    private String instrument;
    private Date timestamp;
    private Integer quantity;
    private String userId;
    private String orderId;
    private OrderType type;

    public Order() {
    }

    @Override
    public String toString() {
        return toJSON();
    }

    public String toJSON() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Order(String instrument, Date timestamp, Integer quantity, String userId, String orderId, OrderType type) {
        this.instrument = instrument;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.userId = userId;
        this.orderId = orderId;
        this.type = type;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public static Order fromJSON(String jsonString) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, Order.class);
    }
}
