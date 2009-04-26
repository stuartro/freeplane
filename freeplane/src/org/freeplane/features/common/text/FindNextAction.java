/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev
 *
 *  This file is modified by Dimitry Polivaev in 2008.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.features.common.text;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.regex.Matcher;

import org.freeplane.core.modecontroller.ModeController;
import org.freeplane.core.resources.ResourceBundles;
import org.freeplane.core.ui.AFreeplaneAction;
import org.freeplane.core.ui.components.UITools;
import org.freeplane.core.util.HtmlTools;

class FindNextAction extends AFreeplaneAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private FindAction find;

	public FindNextAction(final ModeController controller, final FindAction find) {
		super("FindNextAction", controller.getController());
		this.find = find;
	}

	public void actionPerformed(final ActionEvent e) {
		final Collection subterms = find.getSubterms();
		if (subterms == null) {
			UITools.informationMessage(getController().getViewController().getFrame(), ResourceBundles
			    .getText("no_previous_find"));
			return;
		}
		final boolean found = find.findNext();
		if (!found) {
			final String messageText = ResourceBundles.getText("no_more_found_from");
			final String searchTerm = messageText.startsWith("<html>") ? HtmlTools.toXMLEscapedText(find
			    .getSearchTerm()) : find.getSearchTerm();
			UITools.informationMessage(getController().getViewController().getFrame(), 
				messageText.replaceAll("\\$1",Matcher.quoteReplacement(searchTerm))
				.replaceAll("\\$2", Matcher.quoteReplacement(find.getFindFromText())));
		}
	}
}
