/*
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.hal.client.deployment;

import javax.inject.Inject;

import org.jboss.hal.core.mvp.HalViewImpl;
import org.jboss.hal.dmr.ModelNode;
import org.jboss.hal.dmr.dispatch.Dispatcher;
import org.jboss.hal.resources.Resources;

/**
 * @author Harald Pehl
 */
public class BrowseContentView extends HalViewImpl implements BrowseContentPresenter.MyView {

    private final BrowseContentElement browseContent;
    private BrowseContentPresenter presenter;

    @Inject
    public BrowseContentView(final Dispatcher dispatcher, final Resources resources) {
        this.browseContent = new BrowseContentElement(dispatcher, resources, () -> presenter.loadContent());
        initElement(browseContent);
    }

    @Override
    public void attach() {
        super.attach();
        browseContent.attach();
    }

    @Override
    public void setPresenter(final BrowseContentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setContent(final String content, final ModelNode browseContentResult) {
        browseContent.setContent(content, browseContentResult);
    }
}