package com.revature.ECommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "sales", schema = _SchemaName.schemaName)
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private Integer saleId;

    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "dop")
    private Timestamp dateOfPurchase;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /*@JsonBackReference
    @JoinColumn(name ="order_id", referencedColumnName = "o")
    private Order order;*/

    public Sale(){

    }

    public Sale(Integer quantity, Timestamp dateOfPurchase, Product product){
        this.quantity=quantity;
        this.product=product;
        this.cost= product.getProductPrice()*quantity;
        this.dateOfPurchase=dateOfPurchase;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Timestamp getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Timestamp dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
/*
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }*/
}
