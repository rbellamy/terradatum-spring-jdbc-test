package com.terradatum.business;

import com.terradatum.db.dao.JdbcMlsAreasDAO;
import com.terradatum.db.objects.MlsAreasObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by rbellamy on 12/28/15.
 */
@Stateless
@Local
public class MlsAreasBean {

    @Inject
    ClassPathXmlApplicationContext springContext;

    @Inject
    Logger log;

    public List<MlsAreasObject> getMlsAreas(Integer mlsSid) {
        JdbcMlsAreasDAO jbcMlsAreasDAO = springContext.getBean(JdbcMlsAreasDAO.class);
        return jbcMlsAreasDAO.findMlsAreasByMlsSid(mlsSid);
    }
}
