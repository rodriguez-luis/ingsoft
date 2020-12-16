package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dao.UserDao;
import bo.ucb.edu.ingsoft.dto.UserDto;
import bo.ucb.edu.ingsoft.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBl {
    private TransactionDao transactionDao;
    private UserDao userDao;

    @Autowired
    public UserBl (TransactionDao transactionDao, UserDao userDao){
        this.transactionDao = transactionDao;
        this.userDao = userDao;
    }
    public UserDto getById(Integer userId){
        return new UserDto();
    }
    public UserDto create(UserDto userDto, Transaction transaction){
        return new UserDto();
    }
}
