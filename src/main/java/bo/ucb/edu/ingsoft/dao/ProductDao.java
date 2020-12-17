package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    public Product findByProductId(Integer productId);
    public void create(Product product);
    public List<Product> getProducts();
}
