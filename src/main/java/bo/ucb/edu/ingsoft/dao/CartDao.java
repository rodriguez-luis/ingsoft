package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.model.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDao {
    public Cart create(Cart cart);
    public Cart getByUser(String user);
    public void update(Integer id);
}
