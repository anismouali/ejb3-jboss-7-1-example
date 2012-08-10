/*
 * Created on 8/9/12
 * 
 * @author <a href="mailto:holesinsky@dap-services.cz">Daniel Holesinsky</a>
 * 
 * @author www.dap-services.cz
 */
package cz.daps.examples.ejb3.jboss7.deploy;

import javax.ejb.EJB;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author <a href="mailto:holesinsky@dap-services.cz">Daniel Holesinsky</a> */
@RunWith(Arquillian.class)
public class RandomGeneratorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomGeneratorTest.class);

    @EJB(mappedName = "java:global/test/RandomGenerator!cz.daps.examples.ejb3.jboss7.deploy.IRandomGeneratorRemote")
    private IRandomGeneratorRemote randomGeneratorRemote;

    @EJB(mappedName = "java:global/test/RandomGenerator!cz.daps.examples.ejb3.jboss7.deploy.IRandomGenerator")
    private IRandomGenerator randomGeneratorLocal;

    @Deployment
    public static JavaArchive createTestEjbArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test-ejb.jar").addClass(RandomGenerator.class)
            .addClass(IRandomGenerator.class).addClass(IRandomGeneratorRemote.class);
    }

    public static EnterpriseArchive createTestEar() {
        final JavaArchive ejb = createTestEjbArchive();
        return ShrinkWrap.create(EnterpriseArchive.class, "test-ear.ear").addAsModule(ejb);
    }

    @Test
    public void testGenerateLocal() throws Exception {
        Long actual = randomGeneratorLocal.generate();
        MatcherAssert.assertThat(actual, Matchers.notNullValue());
        MatcherAssert.assertThat(actual, Matchers.isA(Long.class));
    }

    @Test
    public void testGenerateRemote() throws Exception {
        Long actual = randomGeneratorRemote.generate();
        LOGGER.info("testGenerateRemote value: " + actual);
        MatcherAssert.assertThat(actual, Matchers.notNullValue());
        MatcherAssert.assertThat(actual, Matchers.isA(Long.class));
    }
}
