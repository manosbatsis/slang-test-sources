/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package org.jetbrains.kotlin.idea.liveTemplates.macro

import com.intellij.codeInsight.template.Expression
import com.intellij.codeInsight.template.ExpressionContext
import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.psiUtil.parentsWithSelf

class KotlinClassNameMacro : KotlinMacro() {
    override fun getName() = "kotlinClassName"
    override fun getPresentableName() = "kotlinClassName()"

    override fun calculateResult(params: Array<Expression>, context: ExpressionContext): Result? {
        val element = context.psiElementAtStartOffset?.parentsWithSelf?.firstOrNull {
            it is KtClassOrObject && it.name != null && !it.hasModifier(KtTokens.COMPANION_KEYWORD)
        } ?: return null
        return TextResult((element as KtClassOrObject).name!!)
    }
}