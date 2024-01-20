package com.sabiou.lint.checks

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.Issue

class ToastIssueRegistry : IssueRegistry() {

    override val issues: List<Issue> = listOf(AndroidToastDetector.ISSUE)

    /**
     * The current API version for Lint's API. Custom checks should return this value.
     * @see <a href="https://cs.android.com/android-studio/platform/tools/base/+/mirror-goog-studio-main:lint/libs/lint-api/src/main/java/com/android/tools/lint/detector/api/Api.kt;l=1?q=api.kt">Api.kt</a>
     */
    override val api: Int
        get() = 14

    override val vendor: Vendor = Vendor(
        vendorName = "AndroidMood",
        feedbackUrl = "https://github.com/gabriel-TheCode/AestheticDialogs/issues",
        contact = "https://github.com/gabriel-TheCode"
    )
}