package com.example.projectservice.RepositoryImpl;

import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Model.User.ShortUser;
import com.example.projectservice.Model.User.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserRepository implements com.example.projectservice.Repository.UserRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    @Transactional
    public void appendProjectAndUser(final BaseProject baseProject,final User user) {
        em.createQuery("UPDATE DetailedProject p SET p.users = ?1 WHERE p.id = ?2")
                .setParameter(1, user)
                .setParameter(2,baseProject.getId())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void appendProjectAndUser(final int project_id,final int user_id) {
        em.createNativeQuery("UPDATE project SET user_id = ?1 WHERE id = ?2")
                .setParameter(1,user_id)
                .setParameter(2,project_id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public ShortUser getShortUserByEmail(final String email) {
        final ShortUser shortUser = em.createQuery("SELECT u FROM ShortUser u WHERE u.email = ?1", ShortUser.class)
                .setParameter(1,email)
                .getResultList().stream().findFirst().orElse(null);
        return shortUser;
    }
}
