package bo.ucb.edu.ingsoft.api;

import bo.ucb.edu.ingsoft.bl.CartBl;
import bo.ucb.edu.ingsoft.bl.TransactionBl;
import bo.ucb.edu.ingsoft.dto.CartDto;
import bo.ucb.edu.ingsoft.model.Transaction;
import bo.ucb.edu.ingsoft.util.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public class CartApi {
    private CartBl cartBl;
    private TransactionBl transactionBl;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartApi.class);

    @Autowired
    public CartApi(CartBl cartBl, TransactionBl transactionBl){
        this.cartBl = cartBl;
        this.transactionBl = transactionBl;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartDto getCart(@PathVariable("username") String username, HttpServletRequest request){
        return this.cartBl.getCart(username);
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartDto postCart (@RequestBody CartDto cartDto, HttpServletRequest request){
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        CartDto cartResponse = cartBl.create(cartDto, transaction);
        return cartResponse;
    }
    @RequestMapping(value = "/{username}" , method = RequestMethod.PATCH)
    public void updatePrivilege(@PathVariable Integer id, HttpServletRequest request){
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction((transaction));
        cartBl.updateCart(id, transaction);
    }
}
