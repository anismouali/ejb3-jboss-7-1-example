/*
 * Created on 8/9/12
 * 
 * @author <a href="mailto:holesinsky@dap-services.cz">Daniel Holesinsky</a>
 * 
 * @author www.dap-services.cz
 */
package cz.daps.examples.ejb3.jboss7.deploy;

import java.util.Random;

import javax.ejb.Stateless;


/** @author <a href="mailto:holesinsky@dap-services.cz">Daniel Holesinsky</a> */
@Stateless
public class RandomGenerator implements IRandomGenerator, IRandomGeneratorRemote {

    public static final String NAME = "cz.daps.examples.ejb3.jboss7.deploy.RandomGenerator";

    @Override
    public Long generate() {
        return new Random().nextLong();
    }

}
