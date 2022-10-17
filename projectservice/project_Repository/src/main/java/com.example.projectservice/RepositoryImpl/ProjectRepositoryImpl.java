package com.example.projectservice.RepositoryImpl;

import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Model.Project.DetailedProject;
import com.example.projectservice.Repository.ProjectRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(BaseProject baseProject) {
        em.persist(baseProject);
    }

    @Override
    public void saveWT(BaseProject baseProject) {
        em.persist(baseProject);
    }

    @Override
    @Transactional
    public void delete(BaseProject baseProject) {
        em.remove(em.contains(baseProject) ? baseProject : em.merge(baseProject));
    }

    @Override
    @Transactional
    public DetailedProject getDetailedProjectByName(final String name) {
        final DetailedProject detailedProject = em.createQuery("SELECT p FROM DetailedProject p LEFT JOIN FETCH p.budget " +
                "LEFT JOIN FETCH p.passport " +
                "LEFT JOIN FETCH p.plan " +
                "LEFT JOIN FETCH p.users " +
                "WHERE p.name = ?1", DetailedProject.class)
                .setParameter(1,name)
                .getResultList().stream().findFirst().orElse(null);
        return detailedProject;
    }
}
