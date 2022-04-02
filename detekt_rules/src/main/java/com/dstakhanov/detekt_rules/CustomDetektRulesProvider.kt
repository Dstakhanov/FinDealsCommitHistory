package com.dstakhanov.detekt_rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomDetektRulesProvider : RuleSetProvider {
    override val ruleSetId: String = "detekt_rule"

    override fun instance(config: Config) = RuleSet(
        ruleSetId,
        listOf(WithoutTabs())
    )
}