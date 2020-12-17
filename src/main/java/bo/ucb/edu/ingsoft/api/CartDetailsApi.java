package bo.ucb.edu.ingsoft.api;

import bo.ucb.edu.ingsoft.bl.CartDetailsBl;
import bo.ucb.edu.ingsoft.bl.TransactionBl;
import bo.ucb.edu.ingsoft.dto.CartDetailsDto;
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
@RequestMapping(value = "/v1/cart/details")
public class CartDetailsApi {
    private CartDetailsBl cartDetailsBl;
    private TransactionBl transactionBl;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartDetailsApi.class);

    @Autowired
    public CartDetailsApi(CartDetailsBl cartDetailsBl, TransactionBl transactionBl) {
        this.cartDetailsBl = cartDetailsBl;
        this.transactionBl =  transactionBl;
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CartDetailsDto> selectProducts(@PathVariable Integer cartId, HttpServletRequest request) {
        return cartDetailsBl.selectDetails(cartId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDetailsDto createProduct(@RequestBody CartDetailsDto cartDetailsDto, HttpServletRequest request) {
        // Creamos transaccion para la operación.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        CartDetailsDto detailsResponse = cartDetailsBl.createDetails(cartDetailsDto, transaction);
        return detailsResponse;
    }
}
