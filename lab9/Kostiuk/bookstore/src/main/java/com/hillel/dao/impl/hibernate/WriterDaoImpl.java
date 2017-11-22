package com.hillel.dao.impl.hibernate;

import com.hillel.dao.WriterDao;
import com.hillel.dto.WriterNameDto;
import com.hillel.model.Writer;
import com.hillel.repository.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;


@Repository
public class WriterDaoImpl extends BasicCRUDImpl<Writer> implements WriterDao {

    @Autowired
    @Qualifier("writerNameTransformer")
    private ResultTransformer writerNameTransformer;

    @PostConstruct
    private void initPersistableClass() {
        setPersistable(Writer.class);
    }

    @Override
    public List<WriterNameDto> getAllWriterNames() {
        return HibernateUtils.getSessionFactory().openSession()
                .createCriteria(Writer.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("id"))
                        .add(Projections.property("fullName")))
                .setResultTransformer(writerNameTransformer)
                .list();
    }

    @Override
    public Optional<Writer> getByFullName(String fullName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Optional<Writer> result = Optional.ofNullable((Writer) session
                .createCriteria(Writer.class)
                .add(Restrictions.eq("fullName", fullName))
                .uniqueResult());
        session.close();
        return result;
    }
}
