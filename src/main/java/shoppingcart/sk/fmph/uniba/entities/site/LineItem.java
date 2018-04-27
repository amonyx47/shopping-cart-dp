package shoppingcart.sk.fmph.uniba.entities.site;

import shoppingcart.sk.fmph.uniba.entities.Product;

import java.math.BigDecimal;

public class LineItem {

    private Product product;
    private int quantity;

    LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct()
    {
        return product;
    }
    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal()
    {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}
