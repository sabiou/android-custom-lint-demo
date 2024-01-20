package com.sabiou.lint.checks

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

class AndroidToastDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE = Issue.create(
            id = "AvoidClassicToast",
            briefDescription = "This is issued to prevent usage of classic android toast widget",
            explanation = "AestheticDialogs should be used",
            category = Category.CORRECTNESS,
            priority = 8,
            severity = Severity.WARNING,
            implementation = Implementation(
                AndroidToastDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableMethodNames(): List<String> {
        return listOf("makeText")
    }

    override fun visitMethodCall(
        context: JavaContext,
        node: UCallExpression,
        method: PsiMethod
    ) {
        // Ignore the method call if it is named "makeText" but doesn't belong to
        // the `android.widget.Toast` class.
        if (!context.evaluator.isMemberInClass(method, "android.widget.Toast")) {
            return
        }
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getLocation(node),
            message = "The default android.widget.Toast class should not be used. Use AestheticDialogs instead"
        )
    }
}