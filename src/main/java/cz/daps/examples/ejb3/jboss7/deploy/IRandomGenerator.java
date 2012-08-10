/*
 * Created on 8/9/12
 * 
 * @author <a href="mailto:holesinsky@dap-services.cz">Daniel Holesinsky</a>
 * 
 * @author www.dap-services.cz
 */
package cz.daps.examples.ejb3.jboss7.deploy;

import javax.ejb.Local;

/** @author <a href="mailto:holesinsky@dap-services.cz">Daniel Holesinsky</a> */
@Local
public interface IRandomGenerator {

    Long generate();
}
