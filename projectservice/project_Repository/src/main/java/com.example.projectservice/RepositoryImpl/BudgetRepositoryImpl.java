package com.example.projectservice.RepositoryImpl;

import com.example.projectservice.Model.Budget.BaseBudget;
import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Repository.BudgetRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class BudgetRepositoryImpl implements BudgetRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(BaseBudget budget) {
        em.persist(budget);
    }

    @Override
    @Transactional
    public void delete(BaseBudget budget) {
        em.remove(em.contains(budget) ? budget : em.merge(budget));
    }

    @Override
    @Transactional
    public void appendProjectAndBudget(BaseProject baseProject, BaseBudget budget) {
        em.createQuery("UPDATE DetailedProject p SET p.budget = ?1 WHERE p.id = ?2")
                .setParameter(1,budget)
                .setParameter(2,baseProject.getId())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void appendProjectAndBudget(int project_id, int budget_id) {
        em.createNativeQuery("UPDATE project SET budget_id = ?1 WHERE id = ?2")
                .setParameter(1,budget_id)
                .setParameter(2,project_id)
                .executeUpdate();
    }

}
