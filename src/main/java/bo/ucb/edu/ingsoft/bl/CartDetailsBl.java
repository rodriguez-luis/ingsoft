package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.CartDetailsDao;
import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dto.CartDetailsDto;
import bo.ucb.edu.ingsoft.dto.ProductDto;
import bo.ucb.edu.ingsoft.model.CartDetails;
import bo.ucb.edu.ingsoft.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartDetailsBl {
    private TransactionDao transactionDao;
    private CartDetailsDao cartDetailsDao;

    @Autowired
    public CartDetailsBl(TransactionDao transactionDao, CartDetailsDao cartDetailsDao) {

        this.transactionDao = transactionDao;
        this.cartDetailsDao = cartDetailsDao;
    }
    public List<CartDetailsDto> selectDetails(Integer cartId){
        List<CartDetails> details= cartDetailsDao.getDetails(cartId);
        List<CartDetailsDto> cartDetailsDtos = new ArrayList<CartDetailsDto>();
        for (int i=0; i< details.size(); i++){
            CartDetails cartDetails= details.get(i);
            CartDetailsDto cartDetailsDto = new CartDetailsDto();
            cartDetailsDto.setCartId(cartDetails.getCartId());
            cartDetailsDto.setDetailsId(cartDetails.getDetailsId());
            cartDetailsDto.setProductId(cartDetails.getProductId());
            cartDetailsDto.setQuantity(cartDetails.getQuantity());
            cartDetailsDto.setPrice(cartDetails.getPrice());
            cartDetailsDtos.add(i,cartDetailsDto);
        }
        return cartDetailsDtos;
    }
    public CartDetailsDto createDetails(CartDetailsDto cartDetailsDto, Transaction transaction) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setDetailsId(cartDetailsDto.getDetailsId());
        cartDetails.setProductId(cartDetailsDto.getProductId());
        cartDetails.setCartId(cartDetailsDto.getCartId());
        cartDetails.setQuantity(cartDetailsDto.getQuantity());
        cartDetails.setPrice(cartDetailsDto.getPrice());
        cartDetails.setTxId(transaction.getTxId());
        cartDetails.setTxUserId(transaction.getTxUserId());
        cartDetails.setTxHost(transaction.getTxHost());
        cartDetails.setTxDate(transaction.getTxDate());
        cartDetails.setStatus(1);
        cartDetailsDao.create(cartDetails);
        Integer getLastId = transactionDao.getLastInsertId();
        cartDetailsDto.setDetailsId(getLastId);
        return cartDetailsDto;
    }
}
