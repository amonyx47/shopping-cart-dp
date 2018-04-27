package shoppingcart.sk.fmph.uniba;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingcart.sk.fmph.uniba.entities.Product;
import shoppingcart.sk.fmph.uniba.entities.site.Cart;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartTesting {

    @Test
    public void addItem(){
        Cart cart = mock(Cart.class);
        cart.addItem(new Product());
        when(cart.getItemCount()).thenReturn(1);
        assertEquals(1, cart.getItemCount());
    }

    @Test
    public void clearItems(){
        Cart cart = mock(Cart.class);
        cart.addItem(new Product());
        cart.clearItems();
        when(cart.getItemCount()).thenReturn(0);
        assertEquals(0, cart.getItemCount());
    }

    @Test
    public void removeOneItem(){
        Cart cart = mock(Cart.class);
        int count = 100;
        for(int i = 0; i < count; i++){
            cart.addItem(new Product());
        }
        cart.removeItem("");
        when(cart.getItemCount()).thenReturn(99);
        assertEquals(99, cart.getItemCount());
    }

    @Test
    public void removeMultipleItems(){
        Cart cart = mock(Cart.class);
        int count = 100;
        int toRemove = new Random().nextInt(count);
        for(int i = 0; i < count; i++){
            cart.addItem(new Product());
        }
        for (int i = 0; i < toRemove; i++){
            cart.removeItem("");
        }
        when(cart.getItemCount()).thenReturn(count - toRemove);
        assertEquals(count - toRemove, cart.getItemCount());
    }

    @Test
    public void addMultipleItems(){
        Cart cart = mock(Cart.class);
        int threshold = 1000;
        int toAdd = new Random().nextInt(threshold);
        for(int i = 0; i < toAdd; i++){
            cart.addItem(new Product());
        }
        when(cart.getItemCount()).thenReturn(toAdd);
        assertEquals(toAdd, cart.getItemCount());
    }
}
