/**
 *  This file is part of RefactorGuidance project. Which explores possibilities to generate context based
 *  instructions on how to refactor a piece of Java code. This applied in an education setting (bachelor SE students)
 *
 *      Copyright (C) 2018, Patrick de Beer, p.debeer@fontys.nl
 *
 *          This program is free software: you can redistribute it and/or modify
 *          it under the terms of the GNU General Public License as published by
 *          the Free Software Foundation, either version 3 of the License, or
 *          (at your option) any later version.
 *
 *          This program is distributed in the hope that it will be useful,
 *          but WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *          GNU General Public License for more details.
 *
 *          You should have received a copy of the GNU General Public License
 *          along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package analysis.context;

import aig.CodeContext;

import java.util.List;

public class MethodExtractSingleResult extends MethodExtract {

    public MethodExtractSingleResult(ContextConfiguration cc) {
        super(cc);
    }

    public boolean detect() throws Exception {

        if (validDataFlowAnalyzer()) {
            _analyzer.start();
            List<String> vfo = _analyzer.variablesForOutput();

            if (vfo.size() == 1) {
                parameters.addResultName(vfo.get(0));
            }
        }

        return !parameters.getCollection().isEmpty();
    }

    @Override
    public CodeContext.CodeContextEnum getType() {
        return CodeContext.CodeContextEnum.MethodExtractSingleResult;
    }
}
