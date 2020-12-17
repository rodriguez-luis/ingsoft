package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.model.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDao {
    public void create(Cart cart);
    public Cart findByUser(String user);
    public void update(Cart cart);
}
