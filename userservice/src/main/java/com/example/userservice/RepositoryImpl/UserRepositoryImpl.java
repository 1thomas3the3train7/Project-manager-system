package com.example.userservice.RepositoryImpl;

import com.example.userservice.Model.User.BaseUser;
import com.example.userservice.Model.User.ShortUser;
import com.example.userservice.Repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveUser(BaseUser user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public ShortUser getShortUserAndRoleByEmail(final String email) {
        final ShortUser shortUser = em.createQuery("SELECT u FROM ShortUser u " +
                "LEFT JOIN FETCH u.roles WHERE u.email = ?1", ShortUser.class)
                .setParameter(1,email.toLowerCase())
                .getResultList().stream().findFirst().orElse(null);
        return shortUser;
    }

    @Override
    @Transactional
    public ShortUser getShortUserByEmail(String email) {
        final ShortUser shortUser = em.createQuery("SELECT u FROM ShortUser u WHERE u.email = ?1", ShortUser.class)
                .setParameter(1,email)
                .getResultList().stream().findFirst().orElse(null);
        return shortUser;
    }
}
