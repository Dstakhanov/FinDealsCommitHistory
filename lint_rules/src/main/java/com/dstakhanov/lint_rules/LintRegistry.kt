package com.dstakhanov.lint_rules


import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class LintRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(WrongAlertDialogDetector.ISSUE)

    override val api: Int
        get() = CURRENT_API

    override val minApi: Int
        get() = 8

    override val vendor: Vendor = Vendor(
        vendorName = "Dstakhanov",
        feedbackUrl = "***",
        contact = "***"
    )
}