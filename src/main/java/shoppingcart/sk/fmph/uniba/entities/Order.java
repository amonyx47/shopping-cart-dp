package shoppingcart.sk.fmph.uniba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, unique=true)
    private String orderNumber;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private Set<OrderItem> items;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="cust_id")
    private Customer customer;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="delivery_addr_id")
    private Address deliveryAddress;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="billing_addr_id")
    private Address billingAddress;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="payment_id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
        this.items = new HashSet<OrderItem>();
        this.status = OrderStatus.NEW;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0.0");
        for (OrderItem item : items) {
            amount = amount.add(item.getSubTotal());
        }
        return amount;
    }
}
