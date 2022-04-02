
package com.dstakhanov.lint_rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiType
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UVariable
import java.util.*


@Suppress("UnstableApiUsage")
class WrongAlertDialogDetector : Detector(), Detector.UastScanner {

    override fun createUastHandler(context: JavaContext) = WrongAlertDialogDetectorHandler(context)

    override fun getApplicableUastTypes() =
        listOf<Class<out UElement>>(UVariable::class.java, UClass::class.java)

    class WrongAlertDialogDetectorHandler(
        private val context: JavaContext
    ) : UElementHandler() {
        override fun visitVariable(node: UVariable) = process(node.type, node)

        override fun visitClass(node: UClass) = node.uastSuperTypes.forEach { process(it.type, it) }

        private fun process(type: PsiType, node: UElement) {
            if (context.evaluator.typeMatches(type, WRONG_IMPORT_ANDROID_ALERT_DIALOG)) {
                context.report(ISSUE, node, context.getLocation(node), REPORT_MESSAGE)
            }
        }
    }
    companion object {

        private const val DETECTOR_ID = "WrongAlertDialogUsage"
        private const val DESCRIPTION = "Использовать необходимо AlertDialog из androidx.appcompat.app"
        private const val EXPLANATION =
            "AlertDialog из androidx.appcompat.app имеет более широкий фунционал"
        private const val PRIORITY = 6
        private const val WRONG_IMPORT_ANDROID_ALERT_DIALOG = "android.app.AlertDialog"
        private const val REPORT_MESSAGE = "Нельзя использовать $WRONG_IMPORT_ANDROID_ALERT_DIALOG"

        val ISSUE = Issue.create(
            DETECTOR_ID,
            DESCRIPTION,
            EXPLANATION,
            Category.CORRECTNESS,
            PRIORITY,
            Severity.WARNING,
            Implementation(WrongAlertDialogDetector::class.java, EnumSet.of(Scope.JAVA_FILE))
        )
    }
}
