package shoppingcart.sk.fmph.uniba.repository;

        import shoppingcart.sk.fmph.uniba.entities.Order;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
        Order findByOrderNumber(String orderNumber);
}
