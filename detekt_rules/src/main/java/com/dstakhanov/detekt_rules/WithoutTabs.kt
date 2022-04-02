package com.dstakhanov.detekt_rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.rules.isPartOf
import io.gitlab.arturbosch.detekt.rules.isPartOfString
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtStringTemplateEntryWithExpression
import org.jetbrains.kotlin.psi.psiUtil.forEachDescendantOfType


class WithoutTabs(config: Config = Config.empty) : Rule(config) {

    override val issue = Issue(
        "WithoutTabs",
        Severity.Style,
        "Проверить есть ли Tab в файлах",
        Debt.FIVE_MINS
    )

    fun findTabs(file: KtFile) {
        file.forEachDescendantOfType<PsiWhiteSpace> {
            if (it.isTab()) report(CodeSmell(issue, Entity.from(it), "Tab символ используется"))
        }
    }

    private fun PsiWhiteSpace.isTab(): Boolean {
        return (!isPartOfString() || isStringInterpolated()) && text.contains('\t')
    }

    private fun PsiWhiteSpace.isStringInterpolated(): Boolean =
        this.isPartOf<KtStringTemplateEntryWithExpression>()
}