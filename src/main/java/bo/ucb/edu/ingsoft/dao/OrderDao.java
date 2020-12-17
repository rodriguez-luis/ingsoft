package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    public void createOrder(Order order);
    public List<Order> getOrders();
    public void updateOrder(Order order);
}
