/*
 * Copyright 2016-2025 Blue Lotus Software, LLC.
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

package com.bluelotussoftware.jsf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0.1
 */
@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {

    private static final long serialVersionUID = -3466934608216622907L;

    private String message;
    private boolean dirty;
    private Map<String, Object> valueMap;

    public IndexBean() {
        valueMap = new HashMap<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * <p>
     * This method checks to see if the new value is different from the original.
     * </p>
     * <p>
     * This method relies on a setting in the <code>web.xml</code> to treat empty strings as null.
     * </p>
     * <pre> 
     *  &lt;context-param&gt;
     *   &lt;param-name&gt;javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL&lt;/param-name&gt;
     *   &lt;param-value>true&lt;/param-value&gt;
     *  &lt;/context-param&gt;
     * </pre>
     * 
     * @param event An event to check for changes in value.
     */
    public void messageChangeListener(ValueChangeEvent event) {
        System.out.println("Old Value: " + event.getOldValue());
        System.out.println("New Value: " + event.getNewValue());

        if (!valueMap.keySet().contains("message")) {
            valueMap.put("message", event.getOldValue());
        }
        Object value = valueMap.get("message");
        System.out.println("Value: " + value);

        if ((value == null && event.getNewValue() == null) || (value != null && value.equals(event.getNewValue()))) {
            setDirty(false);
        } else {
            setDirty(true);
        }
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

}
