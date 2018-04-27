package shoppingcart.sk.fmph.uniba.service;

import shoppingcart.sk.fmph.uniba.entities.Order;
import shoppingcart.sk.fmph.uniba.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService
{
    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(Order order)
    {
        order.setOrderNumber(String.valueOf(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    public Order getOrder(String orderNumber)
    {
        return orderRepository.findByOrderNumber(orderNumber);
    }

}
