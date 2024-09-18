package com.winenote.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.winenote.core.resource.R

private val PretendardFontFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal)
)

private val PretendardStyle = TextStyle(
    fontFamily = PretendardFontFamily,
    fontWeight = FontWeight.W600,
)

internal val Typography = WineNoteTypography(
    bold48 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 48.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.2).sp
    ),
    bold36 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 36.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.2).sp
    ),
    bold32 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 32.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.2).sp
    ),
    bold28 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 28.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.2).sp
    ),
    bold24 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.2).sp
    ),
    bold18 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.2).sp
    ),
    bold16 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.2).sp
    ),
    bold14 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.2).sp
    ),
    bold12 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.2).sp
    ),
    bold11 = PretendardStyle.copy(
        fontWeight = FontWeight.W600,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.2).sp
    ),
    regular48 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 48.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.2).sp
    ),
    regular36 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.2).sp
    ),
    regular32 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.2).sp
    ),
    regular28 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.2).sp
    ),
    regular24 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.2).sp
    ),
    regular18 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.2).sp
    ),
    regular16 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.2).sp
    ),
    regular14 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.2).sp
    ),
    regular12 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.2).sp
    ),
    regular11 = PretendardStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.2).sp
    )
)

@Immutable
data class WineNoteTypography(
    val bold48: TextStyle,
    val bold36: TextStyle,
    val bold32: TextStyle,
    val bold28: TextStyle,
    val bold24: TextStyle,
    val bold18: TextStyle,
    val bold16: TextStyle,
    val bold14: TextStyle,
    val bold12: TextStyle,
    val bold11: TextStyle,
    val regular48: TextStyle,
    val regular36: TextStyle,
    val regular32: TextStyle,
    val regular28: TextStyle,
    val regular24: TextStyle,
    val regular18: TextStyle,
    val regular16: TextStyle,
    val regular14: TextStyle,
    val regular12: TextStyle,
    val regular11: TextStyle
)

val LocalTypography = staticCompositionLocalOf {
    WineNoteTypography(
        bold48 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 48.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.2).sp
        ),
        bold36 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            letterSpacing = (-0.2).sp
        ),
        bold32 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 32.sp,
            lineHeight = 44.sp,
            letterSpacing = (-0.2).sp
        ),
        bold28 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 28.sp,
            lineHeight = 38.sp,
            letterSpacing = (-0.2).sp
        ),
        bold24 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 24.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.2).sp
        ),
        bold18 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.2).sp
        ),
        bold16 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.2).sp
        ),
        bold14 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.2).sp
        ),
        bold12 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.2).sp
        ),
        bold11 = PretendardStyle.copy(
            fontWeight = FontWeight.W600,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = (-0.2).sp
        ),
        regular48 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.2).sp
        ),
        regular36 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.2).sp
        ),
        regular32 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.2).sp
        ),
        regular28 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.2).sp
        ),
        regular24 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.2).sp
        ),
        regular18 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.2).sp
        ),
        regular16 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.2).sp
        ),
        regular14 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.2).sp
        ),
        regular12 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.2).sp
        ),
        regular11 = PretendardStyle.copy(
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.2).sp
        )
    )
}


@Preview(widthDp = 400)
@Composable
private fun BoldTypographyPreview() {
    WineNoteTheme {
        val dummy = "Hello Wine Note!"
        val typographyList = listOf(
            WineTheme.typography.bold48,
            WineTheme.typography.bold36,
            WineTheme.typography.bold32,
            WineTheme.typography.bold28,
            WineTheme.typography.bold24,
            WineTheme.typography.bold18,
            WineTheme.typography.bold16,
            WineTheme.typography.bold14,
            WineTheme.typography.bold12,
            WineTheme.typography.bold11
        )

        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = typographyList,
                itemContent = { textStyle ->
                    Text(
                        text = dummy,
                        style = textStyle
                    )
                }
            )
        }
    }
}

@Preview(widthDp = 400)
@Composable
private fun RegularTypographyPreview() {
    WineNoteTheme {
        val dummy = "Hello Wine Note!"
        val typographyList = listOf(
            WineTheme.typography.regular48,
            WineTheme.typography.regular36,
            WineTheme.typography.regular32,
            WineTheme.typography.regular28,
            WineTheme.typography.regular24,
            WineTheme.typography.regular18,
            WineTheme.typography.regular16,
            WineTheme.typography.regular14,
            WineTheme.typography.regular12,
            WineTheme.typography.regular11
        )

        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = typographyList,
                itemContent = { textStyle ->
                    Text(
                        text = dummy,
                        style = textStyle,
                    )
                }
            )
        }
    }
}