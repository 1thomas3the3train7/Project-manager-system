package com.example.projectservice.RepositoryImpl;

import com.example.projectservice.Model.Passport.BasePassport;
import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Repository.PassportRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class PassportRepositoryImpl implements PassportRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(BasePassport basePassport) {
        em.persist(basePassport);
    }
    @Override
    @Transactional
    public void delete(BasePassport basePassport) {
        em.remove(em.contains(basePassport) ? basePassport : em.merge(basePassport));
    }

    @Override
    @Transactional
    public void appendProjectAndPassport(BaseProject baseProject, BasePassport basePassport) {
        em.createQuery("UPDATE DetailedProject p SET p.passport = ?1 WHERE p.id = ?2")
                .setParameter(1,basePassport)
                .setParameter(2,baseProject.getId())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void appendProjectAndPassport(int project_id, int passport_id) {
        em.createNativeQuery("UPDATE project SET passport_id = ?1 WHERE id = ?2")
                .setParameter(1,passport_id)
                .setParameter(2,project_id)
                .executeUpdate();
    }
}
