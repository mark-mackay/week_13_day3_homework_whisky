package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Whisky> findDistilleriesThatHaveWhiskiesAged(Integer age){
        List<Whisky> result = null;
        Session session = entityManager.unwrap(Session.class);
        Session session2 = entityManager.unwrap(Session.class);
        // SELECT      *     FROM distilleries <restrictions>
        Criteria cr = session.createCriteria(Distillery.class);

        cr.createAlias("distilleries", "distilleryAlias");
        cr.createAlias("distilleryAlias.whiskies", "whiskyAlias");
        cr.add(Restrictions.eq("whiskyAlias.age", age));


        result = cr.list();

//        public List<Whisky> findThatHavePiratesNamed(String firstName){
//            List<Ship> result = null;
//            Session session = entityManager.unwrap(Session.class);
//            // SELECT      *     FROM ships <restrictions>
//            Criteria cr = session.createCriteria(Ship.class);
//
//            cr.createAlias("pirates", "pirateAlias");
//
//
//            // SELECT * FROM ships WHERE "firstName"=firstName
//            cr.add(Restrictions.eq("pirateAlias.firstName", firstName));
//
//            result = cr.list();





        return result;
    }
}
