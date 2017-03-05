package by.bsu.modulestatic.service;

import by.bsu.modulestatic.dao.UserDao;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.User;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

    @Repository
    public class UserService {
        @Autowired
        private UserDao userDao;
        private static final String SAVE_USER_ERROR = "Can't save user.";
        private static final String DELETE_USER_ERROR = "Can't delete user.";


        @Transactional
        public boolean ifLoginExists(String userLogin) throws ServiceException {
            try {
                Long userId = userDao.getUserIdByLogin(userLogin);
                if(userId == null){
                    return false;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            return true;
        }

        @Transactional
        public boolean login(User userInfo) throws ServiceException {
            try {
                String password = userInfo.getPassword();
                String dbPassword = userDao.getPasswordByLogin(userInfo.getLogin());
                if(dbPassword != null){
                    if(dbPassword.equals(password)){
                        return true;
                    }
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            return false;
        }

        @Transactional
        public void deleteUser(Long userId) throws ServiceException {
            try {
                userDao.deleteById(userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }


