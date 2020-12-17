package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.OrderDao;
import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dto.OrderDto;
import bo.ucb.edu.ingsoft.model.Order;
import bo.ucb.edu.ingsoft.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBl {
    private TransactionDao transactionDao;
    private OrderDao orderDao;

    @Autowired
    public OrderBl(TransactionDao transactionDao, OrderDao orderDao) {
        this.transactionDao = transactionDao;
        this.orderDao = orderDao;
    }
    public List<OrderDto> selectOrders(){
        List<Order> orders= orderDao.getOrders();
        List<OrderDto> orderDtos = new ArrayList<OrderDto>();
        for (int i=0; i< orders.size(); i++){
            Order order= orders.get(i);
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getOrderId());
            orderDto.setDate(order.getDate());
            orderDto.setTotal(order.getTotal());
            orderDto.setAddress(order.getAddress());
            orderDto.setCartId(order.getCartId());
            orderDto.setUsername(order.getUsername());
            orderDto.setDeliveryBoy(order.getDeliveryBoy());
            orderDto.setOrderStatus(order.getOrderStatus());
            orderDtos.add(i,orderDto);
        }
        return orderDtos;
    }
    public OrderDto createOrder(OrderDto orderDto, Transaction transaction) {
        Order order = new Order();
        order.setDate(transaction.getTxDate());
        order.setTotal(orderDto.getTotal());
        order.setAddress(orderDto.getAddress());
        order.setCartId(orderDto.getCartId());
        order.setUsername(orderDto.getUsername());
        order.setDeliveryBoy(orderDto.getDeliveryBoy());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setTxId(transaction.getTxId());
        order.setTxUserId(transaction.getTxUserId());
        order.setTxHost(transaction.getTxHost());
        order.setTxDate(transaction.getTxDate());
        order.setStatus(1);
        orderDao.createOrder(order);
        Integer getLastId = transactionDao.getLastInsertId();
        orderDto.setOrderId(getLastId);
        return orderDto;
    }
    public void updateOrder(OrderDto orderDto, Transaction transaction){
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setDeliveryBoy(orderDto.getDeliveryBoy());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setTxId(transaction.getTxId());
        order.setTxUserId(transaction.getTxUserId());
        order.setTxHost(transaction.getTxHost());
        order.setTxDate(transaction.getTxDate());
        orderDao.updateOrder(order);
    }
}
