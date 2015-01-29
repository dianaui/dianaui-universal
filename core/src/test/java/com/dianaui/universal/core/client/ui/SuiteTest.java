/*
 * #%L
 * Diana UI Core
 * %%
 * Copyright (C) 2014 Diana UI
 * %%
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
 * #L%
 */
package com.dianaui.universal.core.client.ui;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class SuiteTest extends TestCase {

    public static Test suite() {
        GWTTestSuite suite = new GWTTestSuite("All Gwt Tests go in here");
        suite.addTestSuite(AlertGwtTest.class);
        suite.addTestSuite(AnchorButtonGwtTest.class);
        suite.addTestSuite(AnchorListItemGwtTest.class);
        //suite.addTestSuite(ButtonGroupGwtTest.class);
        //suite.addTestSuite(CheckBoxButtonGwtTest.class);
        //suite.addTestSuite(DropDownButtonGwtTest.class);
        //suite.addTestSuite(RadioButtonGwtTest.class);
        return suite;
    }

}
