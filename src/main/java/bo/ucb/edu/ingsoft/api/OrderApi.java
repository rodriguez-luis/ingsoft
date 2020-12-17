package bo.ucb.edu.ingsoft.api;

import bo.ucb.edu.ingsoft.bl.OrderBl;
import bo.ucb.edu.ingsoft.bl.TransactionBl;
import bo.ucb.edu.ingsoft.dto.OrderDto;
import bo.ucb.edu.ingsoft.model.Order;
import bo.ucb.edu.ingsoft.model.Transaction;
import bo.ucb.edu.ingsoft.util.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/order")
public class OrderApi {
    private OrderBl orderBl;
    private TransactionBl transactionBl;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApi.class);

    @Autowired
    public OrderApi(OrderBl orderBl, TransactionBl transactionBl) {
        this.orderBl = orderBl;
        this.transactionBl =  transactionBl;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> selectOrders(HttpServletRequest request) {
        return orderBl.selectOrders();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        // Creamos transaccion para la operación.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        OrderDto orderResponse = orderBl.createOrder(orderDto, transaction);
        return orderResponse;
    }
    @RequestMapping(method = RequestMethod.PATCH , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDetails(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        // Creamos transaccion para la operación.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        orderBl.updateOrder(orderDto, transaction);
    }
}
