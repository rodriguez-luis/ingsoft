package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dao.UserDao;
import bo.ucb.edu.ingsoft.dto.UserDto;
import bo.ucb.edu.ingsoft.model.Transaction;
import bo.ucb.edu.ingsoft.model.User;
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
    public UserDto getByUsername(String username){
        User user = userDao.findByUsername(username);
        UserDto userResponse = new UserDto();
        userResponse.setUsername(user.getUsername());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPrivilege(user.getPrivilege());
        return userResponse;
    }
    public UserDto create(UserDto userDto, Transaction transaction){
        User user =new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPrivilege(userDto.getPrivilege());
        user.setStatus(1);
        user.setTxId(transaction.getTxId());
        user.setTxHost(transaction.getTxHost());
        user.setTxUserId(transaction.getTxUserId());
        user.setTxDate(transaction.getTxDate());
        userDao.create(user);
        return userDto;
    }
}
