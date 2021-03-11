/*
 * Copyright (c) 2007, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * $Id$
 */

package com.sun.ts.tests.jaxws.wsi.j2w.rpc.literal.R2101;

import java.util.Properties;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.javatest.Status;
import com.sun.ts.tests.jaxws.sharedclients.ClientFactory;
import com.sun.ts.tests.jaxws.sharedclients.SOAPClient;
import com.sun.ts.tests.jaxws.sharedclients.rpclitclient.*;
import com.sun.ts.tests.jaxws.wsi.constants.DescriptionConstants;
import com.sun.ts.tests.jaxws.wsi.utils.DescriptionUtils;

import com.sun.ts.lib.harness.*;

public class Client extends ServiceEETest implements DescriptionConstants {
  /**
   * The client.
   */
  private SOAPClient client;

  static J2WRLShared service = null;

  /**
   * Test entry point.
   *
   * @param args
   *          the command-line arguments.
   */
  public static void main(String[] args) {
    Client test = new Client();
    Status status = test.run(args, System.out, System.err);
    status.exit();
  }

  /**
   * @class.testArgs: -ap jaxws-url-props.dat
   * @class.setup_props: webServerHost; webServerPort; platform.mode;
   *
   * @param args
   * @param properties
   *
   * @throws Fault
   */
  public void setup(String[] args, Properties properties) throws Fault {
    client = ClientFactory.getClient(J2WRLSharedClient.class, properties, this,
        service);
    logMsg("setup ok");
  }

  public void cleanup() {
    logMsg("cleanup");
  }

  /**
   * @testName: testQNameReferences
   *
   * @assertion_ids: WSI:SPEC:R2101
   *
   * @test_Strategy: Retrieve the WSDL, generated by the Java-to-WSDL tool, and
   *                 examine all element's attributes, and verify the namespace
   *                 declaration for each QName.
   *
   * @throws Fault
   */
  public void testQNameReferences() throws Fault {
    Document document = client.getDocument();
    verifyElementAttributes(document.getDocumentElement());
  }

  protected void verifyElementAttributes(Element element) throws Fault {
    Attr[] attributes = DescriptionUtils.getElementAttributes(element);
    for (int i = 0; i < attributes.length; i++) {
      String name = attributes[i].getName();
      String value = attributes[i].getValue();
      int index = value.indexOf(':');
      if (index > 0) {
        if (value.indexOf('/') == -1) {
          String prefix = value.substring(0, index);
          if (!DescriptionUtils.isNamespacePrefixDeclared(element, prefix)) {
            throw new Fault("The prefix '" + prefix + "' in QName attribute '"
                + name + "' is not declared (BP-R2101)");
          }
        }
      }
    }
    verifyElementChildren(element);
  }

  protected void verifyElementChildren(Element element) throws Fault {
    Element[] children = DescriptionUtils.getChildElements(element);
    for (int i = 0; i < children.length; i++) {
      verifyElementAttributes(children[i]);
    }
  }
}
