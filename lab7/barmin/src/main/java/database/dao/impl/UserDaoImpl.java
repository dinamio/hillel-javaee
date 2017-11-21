package database.dao.impl;

import database.config.HibernateUtil;
import database.dao.UserDao;
import database.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDaoImpl implements UserDao{
    @Override
    public User getUserByID(String id) {
        User user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    private static String cryptWithMD5(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (byte aDigested : digested) {
                sb.append(Integer.toHexString(0xff & aDigested));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @return -2 - no such user, 0 - user was logged in, -1 - wrong password
     */
    @Override
    public int loginUser(String username, String password) {
        User user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, username);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(user == null){
            return -2;
        }
        if(user.getPassHex().equals(cryptWithMD5(password))){
            return 0;
        }else {
            return -1;
        }
    }

    @Override
    public boolean registerUser(String username, String password) {
        User user;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, username);
            if(user == null){
                user = new User();
                user.setId(username);
                user.setPassHex(cryptWithMD5(password));
                session.save(user);
                transaction.commit();
                return true;
            }else {
                transaction.commit();
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
