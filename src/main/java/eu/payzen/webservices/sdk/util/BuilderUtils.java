/*
 * Copyright 2015-2018 Javier Garcia Alonso.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.payzen.webservices.sdk.util;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils used by request object builders
 * 
 * @author Javier Garcia Alonso
 */
public class BuilderUtils {
    private static final Logger logger = LoggerFactory.getLogger(BuilderUtils.class);

    /**
     * Converts an object du type java.util.Date into XMLGregorianCalendar
     * 
     * @param date date in format java.util.Date
     * @return object du type XMLGregorianCalendar
     */
    public static XMLGregorianCalendar date2XMLGregorianCalendar(Date date) {
        XMLGregorianCalendar returnedCalendar = null;

        if (date != null) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            DatatypeFactory datatypeFactory = null;
            try {
                datatypeFactory = DatatypeFactory.newInstance();
            } catch (DatatypeConfigurationException e) {
                logger.error("Error generating datatype to generate XMLGregorianCalendar. "
                        + "This could cause potentially a NPE", e);
            }
            if (datatypeFactory != null) {
                returnedCalendar = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
            }
        }

        return returnedCalendar;
    }
}
