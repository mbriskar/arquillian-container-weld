/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.container.weld.se.embedded_1.shrinkwrap;

import java.util.Collection;

import junit.framework.Assert;

import org.jboss.arquillian.container.weld.se.embedded_1.beans.MyBean;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;


/**
 * ShrinkwrapBeanDeploymentArchiveTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ShrinkwrapBeanDeploymentArchiveTestCase
{

   @Test
   public void shouldBeAbleToFindAllClasses() throws Exception 
   {
      JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                              .addPackage(MyBean.class.getPackage())
                              .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

      
      ShrinkwrapBeanDeploymentArchive beanDeployment = archive.as(ShrinkwrapBeanDeploymentArchive.class);
      try
      {
         Collection<Class<?>> classes = beanDeployment.getBeanClasses();
         Assert.assertEquals(1, classes.size());
      }
      finally 
      {
         beanDeployment.getClassLoader().close();
      }
                              
   }

   @Test
   public void shouldBeAbleToFindNoClasses() throws Exception
   {
      JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
               .addPackage(MyBean.class.getPackage());


      ShrinkwrapBeanDeploymentArchive beanDeployment = archive.as(ShrinkwrapBeanDeploymentArchive.class);
      try
      {
         Collection<Class<?>> classes = beanDeployment.getBeanClasses();
         Assert.assertTrue(classes.isEmpty());
      }
      finally
      {
         beanDeployment.getClassLoader().close();
      }

   }
}
