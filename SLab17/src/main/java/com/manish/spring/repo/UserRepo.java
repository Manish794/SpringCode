package com.manish.spring.repo;

import com.manish.spring.jpa.entity.UserEntity;
import com.manish.spring.model.Login;
import com.manish.spring.model.User;
import com.manish.spring.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Base64;
import java.util.List;

@Repository
public class UserRepo {

    private EntityManager entityManager;

    @Autowired
    public UserRepo(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public String addUser(User user){
        UserEntity userEntity = UserTransformer.getUserEntity(user);
        entityManager.persist(userEntity);
        return userEntity.getUserId();
    }

    @Transactional
    public User updateUser(User newUserData){
        UserEntity userEntity = entityManager.find(UserEntity.class, newUserData.getUserId());
        try{
            if(!ObjectUtils.isEmpty(userEntity)) {
                UserTransformer.updateUserEntity(userEntity, newUserData);
                entityManager.merge(userEntity);
                return UserTransformer.getUser(userEntity);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String userId){
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);
        return UserTransformer.getUser(userEntity);
    }

    public User getUserByPhone(long phone){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        Predicate pr1 = builder.equal(root.get("phone"), phone);
        query.where(pr1);

        Query q = entityManager.createQuery(query);
        List list = q.getResultList();
        if (!CollectionUtils.isEmpty(list)) {
            return UserTransformer.getUser((UserEntity) list.get(0));
        }
        return null;
    }

    public User getUserByEmail(String email){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        Predicate pr1 = builder.equal(root.get("email"), email);
        query.where(pr1);

        Query q = entityManager.createQuery(query);
        List list = q.getResultList();
        if (!CollectionUtils.isEmpty(list)) {
            return UserTransformer.getUser((UserEntity) list.get(0));
        }
        return null;
    }

    public User getUserByLoginId(String loginId){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        Predicate pr1 = builder.equal(root.get("loginId"), loginId);
        query.where(pr1);

        Query q = entityManager.createQuery(query);
        List list = q.getResultList();
        if (!CollectionUtils.isEmpty(list)) {
            return UserTransformer.getUser((UserEntity) list.get(0));
        }
        return null;
    }

    public List<User> getAllUsers(){
        Query query = entityManager.createQuery("SELECT u from UserEntity u", UserEntity.class);
        return UserTransformer.getUsers(query.getResultList());
    }

    @Transactional
    public User deleteUser(String userId){
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);
        try{
            if(!ObjectUtils.isEmpty(userEntity)) {
                entityManager.remove(userEntity);
                return UserTransformer.getUser(userEntity);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User loginUser(Login login){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        Predicate pr1 = builder.equal(root.get("loginId"), login.getLoginId());

        byte[] result = Base64.getEncoder().encode(login.getLoginPassword().getBytes());
        Predicate pr2 = builder.equal(root.get("loginPassword"), new String(result));
        query.where(builder.and(pr1,pr2));

        Query q = entityManager.createQuery(query);
        List list = q.getResultList();
        if (!CollectionUtils.isEmpty(list)) {
            return UserTransformer.getUser((UserEntity) list.get(0));
        }
        return null;
    }

    @Transactional
    public boolean deleteAll(){
        Query query = entityManager.createQuery("SELECT u from UserEntity u", UserEntity.class);
        List<UserEntity> userEntities = query.getResultList();
        if(!CollectionUtils.isEmpty(userEntities)){
            for(UserEntity userEntity : userEntities){
                entityManager.remove(userEntity);
            }
            return true;
        }
        return false;
    }

}
