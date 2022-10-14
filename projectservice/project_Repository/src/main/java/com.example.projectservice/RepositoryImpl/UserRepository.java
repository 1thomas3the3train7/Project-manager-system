package com.example.projectservice.RepositoryImpl;

import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Model.User.Users;
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
    public void save(Users users) {
        em.persist(users);
    }

    @Override
    @Transactional
    public void delete(Users users) {
        em.remove(em.contains(users) ? users : em.merge(users));
    }

    @Override
    @Transactional
    public void appendProjectAndUser(BaseProject baseProject, Users users) {
        em.createQuery("UPDATE DetailedProject p SET p.users = ?1 WHERE p.id = ?2")
                .setParameter(1,users)
                .setParameter(2,baseProject.getId())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void appendProjectAndUser(int project_id, int user_id) {
        em.createNativeQuery("UPDATE project SET user_id = ?1 WHERE id = ?2")
                .setParameter(1,user_id)
                .setParameter(2,project_id)
                .executeUpdate();
    }
}
