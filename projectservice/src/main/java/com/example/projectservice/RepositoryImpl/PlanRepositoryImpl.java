package com.example.projectservice.RepositoryImpl;

import com.example.projectservice.Model.Plan.BasePlan;
import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Repository.PlanRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class PlanRepositoryImpl implements PlanRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(BasePlan basePlan) {
        em.persist(basePlan);
    }

    @Override
    @Transactional
    public void delete(BasePlan basePlan) {
        em.remove(em.contains(basePlan) ? basePlan : em.merge(basePlan));
    }

    @Override
    @Transactional
    public void appendProjectAndPlan(BaseProject baseProject, BasePlan basePlan) {
        em.createQuery("UPDATE DetailedProject p SET p.plan = ?1 WHERE p.id = ?2")
                .setParameter(1,basePlan)
                .setParameter(2,baseProject.getId())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void appendProjectAndPlan(int project_id, int plan_id) {
        em.createNativeQuery("UPDATE project SET plan_id = ?1 WHERE id = ?2")
                .setParameter(1,plan_id)
                .setParameter(2,project_id)
                .executeUpdate();
    }
}
