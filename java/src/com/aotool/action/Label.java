/*******************************************************************************
 * Copyright 2002-2011 David Boyce. All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package com.aotool.action;

import java.io.IOException;

import com.aotool.action.support.ActionHelper;
import com.aotool.action.support.GenericAction;
import com.aotool.entity.Ptx;
import com.aotool.entity.PtxBuilder;
import com.aotool.web.Http;

/**
 * The Label action is used to add labels.
 */
final class Label extends GenericAction {

    @Override
    public final String getSummary() {
        return ActionMessages.getString("Label.0"); //$NON-NLS-1$
    }

    @Override
    public final String getHelpMessage() {
        return ActionMessages.getString("Label.1"); //$NON-NLS-1$
    }

    @Override
    public void doAction(ActionHelper ah) throws IOException {
        if (!ah.parseOptions()) {
            return;
        }

        String label = ah.getParameter(Http.LABEL_PARAM);
        Ptx ptx = ah.getPtx();
        PtxBuilder ptxBuilder = ah.getPtxBuilder(ptx);
        ptxBuilder.label(label);
    }
}
