package com.winenote.core.designsystem

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "light theme", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "dark theme", uiMode = UI_MODE_NIGHT_YES)
annotation class ThemePreviews

@Preview(name = "foldable", device = "spec:shape=Normal, width=680, height=841, unit=dp, dpi=480")
annotation class FoldablePreviews