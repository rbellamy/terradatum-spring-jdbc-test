package com.terradatum;

import com.terradatum.business.MlsAreasBean;
import com.terradatum.db.dao.JdbcMlsAreasDAO;
import com.terradatum.db.dao.MlsAreasDAO;
import com.terradatum.db.objects.MlsAreasObject;
import com.terradatum.util.JULProducer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringConfiguration;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by rbellamy on 12/28/15.
 */
@RunWith(Arquillian.class)
@SpringConfiguration({"applicationContext.xml"})
public class TestMlsAreasBean {

    @EJB()
    private MlsAreasBean mlsAreas;

    @Inject
    private Logger log;

    @Deployment
    public static Archive<?> createDeployment() {
        //ArquillianTestHelper.processCliFile(new File("src/test/wildfly/wildfly-add-oracle-module.cli"));
        //ArquillianTestHelper.processCliFile(new File("src/test/wildfly/wildfly-add-hibernate-module.cli"));
        //ArquillianTestHelper.processCliFile(new File("src/test/wildfly/wildfly-add-oracle-driver-and-datasource.cli"));

        // Import Maven runtime dependencies
        PomEquippedResolveStage pomFromFile = Maven.resolver().loadPomFromFile("pom.xml");
        File[] files = pomFromFile
                .importRuntimeDependencies()
                .resolve()
                .withoutTransitivity()
                .asFile();

        WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MlsAreasBean.class,
                        MlsAreasObject.class,
                        MlsAreasDAO.class,
                        JdbcMlsAreasDAO.class,
                        JULProducer.class)
                .addAsManifestResource("MANIFEST.MF")
                .addAsWebInfResource("applicationContext.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(files);
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void getMlsAreas() {
        log.info("Starting the test 'getMlsAreas()'");
        Assert.assertNotNull("Injected object should not be null.", mlsAreas);
        List agentContactInfoObjectList =  mlsAreas.getMlsAreas(1);
        Assert.assertEquals(1, agentContactInfoObjectList.size());
    }
}
