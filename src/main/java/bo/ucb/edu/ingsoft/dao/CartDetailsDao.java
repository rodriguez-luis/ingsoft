package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.model.CartDetails;

import java.util.List;

public interface CartDetailsDao {
    public CartDetails create(CartDetails cartDetails);
    public List<CartDetails> getDetails(Integer cartId);
}
