package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.CartDao;
import bo.ucb.edu.ingsoft.dao.ProductDao;
import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dto.CartDto;
import bo.ucb.edu.ingsoft.dto.ProductDto;
import bo.ucb.edu.ingsoft.model.Cart;
import bo.ucb.edu.ingsoft.model.Product;
import bo.ucb.edu.ingsoft.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartBl {
    private TransactionDao transactionDao;
    private CartDao cartDao;
    @Autowired
    public CartBl(TransactionDao transactionDao, CartDao cartDao) {
        this.transactionDao = transactionDao;
        this.cartDao = cartDao;
    }
    public CartDto create(CartDto cartDto, Transaction transaction) {
        updateCart(cartDto.getUsername(),transaction);
        Cart cart = new Cart();
        cart.setUsername(cartDto.getUsername());
        cart.setCartStatus(1);
        cart.setStatus(1);
        cart.setTxId(transaction.getTxId());
        cart.setTxHost(transaction.getTxHost());
        cart.setTxUserId(transaction.getTxUserId());
        cart.setTxDate(transaction.getTxDate());
        cartDao.create(cart);
        Integer getLastId = transactionDao.getLastInsertId();
        cartDto.setCartId(getLastId);
        return cartDto;
    }
    public CartDto getCart(String username){
        Cart cart = cartDao.findByUser(username);
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setUsername(cart.getUsername());
        cartDto.setCartStatus(cart.getCartStatus());
        return cartDto;
    }
    public void updateCart(String user, Transaction transaction){
        Cart cart = new Cart();
        cart.setUsername(user);
        cart.setCartStatus(2);
        cart.setTxId(transaction.getTxId());
        cart.setTxHost(transaction.getTxHost());
        cart.setTxUserId(transaction.getTxUserId());
        cart.setTxDate(transaction.getTxDate());
        cartDao.update(cart);
    }
}
