package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.model.CartDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CartDetailsDao {
    public void create(CartDetails cartDetails);
    public List<CartDetails> getDetails(Integer cartId);
}
