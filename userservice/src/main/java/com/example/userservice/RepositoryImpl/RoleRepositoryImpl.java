package com.example.userservice.RepositoryImpl;

import com.example.userservice.Model.Role.BaseRole;
import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.ShortUser;
import com.example.userservice.Repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(BaseRole baseRole) {
        em.persist(baseRole);
    }

    @Override
    @Transactional
    public void delete(BaseRole baseRole) {
        em.remove(em.contains(baseRole) ? baseRole : em.merge(baseRole));
    }

    @Override
    @Transactional
    public ShortRole getShortRoleByName(final String name) {
        final ShortRole shortRole = em.createQuery("SELECT r FROM ShortRole r WHERE r.name = ?1", ShortRole.class)
                .setParameter(1,name)
                .getResultList().stream().findFirst().orElse(null);
        return shortRole;
    }

    @Override
    @Transactional
    public void appendUserAndRole(Integer user_id, Integer role_id) {
        em.createNativeQuery("INSERT INTO user_and_role (user_id,role_id) VALUES(?1,?2)")
                .setParameter(1,user_id)
                .setParameter(2,role_id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void appendUserAndRole(ShortUser shortUser, ShortRole shortRole) {
        em.createNativeQuery("INSERT INTO user_and_role (user_id,role_id) VALUES(?1,?2)")
                .setParameter(1,shortUser.getId())
                .setParameter(2,shortRole.getId())
                .executeUpdate();
    }
}
