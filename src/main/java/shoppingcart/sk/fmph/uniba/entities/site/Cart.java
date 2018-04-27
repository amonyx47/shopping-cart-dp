package shoppingcart.sk.fmph.uniba.entities.site;

import shoppingcart.sk.fmph.uniba.entities.Customer;
import shoppingcart.sk.fmph.uniba.entities.Payment;
import shoppingcart.sk.fmph.uniba.entities.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<LineItem> items;
    private Customer customer;
    private Payment payment;

    public Cart()
    {
        items = new ArrayList<LineItem>();
        customer = new Customer();
        payment = new Payment();
    }

    public void addItem(Product product)
    {
        for (LineItem lineItem : items)
        {
            if(lineItem.getProduct().getCod().equals(product.getCod())){
                lineItem.setQuantity(lineItem.getQuantity()+1);
            }
        }
        LineItem item = new LineItem(product, 1);
        this.items.add(item);
    }

    public void updateItemQuantity(Product product, int quantity)
    {
        for (LineItem lineItem : items)
        {
            if(lineItem.getProduct().getCod().equals(product.getCod()))
            {
                lineItem.setQuantity(quantity);
            }
        }
    }

    public void removeItem(String cod)
    {
        LineItem  item = null;
        for (LineItem lineItem : items)
        {
            if(lineItem.getProduct().getCod().equals(cod))
            {
                item = lineItem;
                break;
            }
        }
        if(item != null){
            items.remove(item);
        }
    }

    public void clearItems()
    {
        items = new ArrayList<>();
    }

    public int getItemCount()
    {
        int count = 0;
        for (LineItem lineItem : items) {
            count +=  lineItem.getQuantity();
        }
        return count;
    }

    public List<LineItem> getItems()
    {
        return items;
    }

    public void setItems(List<LineItem> items)
    {
        this.items = items;
    }

    public BigDecimal getTotalAmount()
    {
        BigDecimal amount = new BigDecimal("0.0");
        for (LineItem lineItem : items)
        {
            amount = amount.add(lineItem.getSubTotal());
        }
        return amount;
    }

    public Customer getCustomer()
    {
        return customer;
    }
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Payment getPayment()
    {
        return payment;
    }
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

}
