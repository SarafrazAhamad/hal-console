/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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
package org.jboss.hal.ballroom.form;

import com.google.common.base.Strings;
import elemental.client.Browser;
import elemental.dom.Element;
import elemental.html.OptionElement;
import elemental.html.SelectElement;

import java.util.List;

import static com.google.common.base.Strings.emptyToNull;
import static org.jboss.hal.resources.Names.UNDEFINED;

/**
 * @author Harald Pehl
 */
abstract class SelectBoxElement<T> extends InputElement<T> {

    final boolean allowEmpty;
    final boolean multi;
    final SelectElement element;

    SelectBoxElement(final boolean allowEmpty, final boolean multi) {
        this.allowEmpty = allowEmpty;
        this.multi = multi;

        element = Browser.getDocument().createSelectElement();
        element.setMultiple(multi);
        element.setSize(1);
    }

    void setOptions(List<String> options) {
        for (String option : options) {
            OptionElement optionElement = Browser.getDocument().createOptionElement();
            optionElement.setText(option);
            if (emptyToNull(option) == null) {
                optionElement.setTitle(UNDEFINED);
            }
            element.appendChild(optionElement);
        }
    }

    @Override
    public int getTabIndex() {
        return element.getTabIndex();
    }

    @Override
    public void setAccessKey(final char c) {
        element.setAccessKey(String.valueOf(c));
    }

    @Override
    public void setFocus(final boolean b) {
        if (b) {
            element.focus();
        } else {
            element.blur();
        }
    }

    @Override
    public void setTabIndex(final int i) {
        element.setTabIndex(i);
    }

    @Override
    public boolean isEnabled() {
        return !element.isDisabled();
    }

    @Override
    public void setEnabled(final boolean b) {
        element.setDisabled(!b);
    }

    @Override
    public void setName(final String s) {
        element.setName(s);
    }

    @Override
    public String getName() {
        return element.getName();
    }

    @Override
    public Element asElement() {
        return element;
    }

    @Override
    public void setPlaceholder(final String placeHolder) {
        element.setTitle(Strings.isNullOrEmpty(placeHolder) ? UNDEFINED : placeHolder);
    }
}
