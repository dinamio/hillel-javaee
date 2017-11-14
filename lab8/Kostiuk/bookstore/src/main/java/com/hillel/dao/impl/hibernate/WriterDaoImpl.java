package com.hillel.dao.impl.hibernate;

import com.hillel.dao.WriterDao;
import com.hillel.dto.WriterNameDto;
import com.hillel.dto.transformers.WriterNameTransformer;
import com.hillel.model.Writer;
import com.hillel.repository.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class WriterDaoImpl extends BasicCRUDImpl<Writer> implements WriterDao {

    static volatile WriterDaoImpl instance;

    private WriterDaoImpl(Class<Writer> persistable) {
        super(persistable);
    }

    public static WriterDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new WriterDaoImpl(Writer.class);
                }
            }
        }
        return instance;
    }


    @Override
    public List<WriterNameDto> getAllWriterNames() {
        return HibernateUtils.getSessionFactory().openSession()
                .createCriteria(Writer.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("id"))
                        .add(Projections.property("fullName")))
                .setResultTransformer(new WriterNameTransformer())
                .list();
    }

    @Override
    public Optional<Writer> getByFullName(String fullName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
//        StatelessSession session = HibernateUtils.getSessionFactory().openStatelessSession();
        Optional<Writer> result = Optional.ofNullable((Writer) session
                .createCriteria(Writer.class)
                .add(Restrictions.eq("fullName", fullName))
                .uniqueResult());
        session.close();
        return result;
    }
}
