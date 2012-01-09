/*
 * JBoss, a division of Red Hat
 * Copyright 2012, Red Hat Middleware, LLC, and individual
 * contributors as indicated by the @authors tag. See the
 * copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.gatein.sso.agent;

import org.apache.log4j.Logger;
import org.gatein.wci.security.Credentials;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public abstract class GenericAgent
{
   private static Logger log = Logger.getLogger(GenericAgent.class);
   
   protected void saveSSOCredentials(String username, HttpServletRequest httpRequest)
   {
      //Use empty password....it shouldn't be needed...this is a SSO login. The password has
      //already been presented with the SSO server. It should not be passed around for
      //better security
      Credentials credentials = new Credentials(username, "");

      httpRequest.getSession().setAttribute(Credentials.CREDENTIALS, credentials);
      httpRequest.getSession().setAttribute("username", username);

      // This is needed for using default login module stack instead of SSOLoginModule. In this case, GateIn authentication is done thanks to PortalLoginModule.
      httpRequest.getSession().setAttribute("authenticatedCredentials", credentials);

      log.debug("Credentials of user " + username + " saved into HTTP session.");
   }
   
}
