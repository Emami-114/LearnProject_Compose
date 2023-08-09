package com.example.learnproject_compose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val DarkColorScheme = darkColors(
    primary = LightGreen1,
    secondary = Beige3,
    onBackground = DeepBlue,
    background = DeepBlue,
    onSurface = DeepBlue,
    surface = DeepBlue

)

//
private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun LearnProject_ComposeTheme(
    windowsSizeClass: WindowsSizeClass,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {


    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        DarkColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {

            val window = (view.context as Activity).window
//            window.statusBarColor = statusBarColor.toArgb()
//            window.statusBarColor = Color.Transparent.toArgb()

//            window.navigationBarColor = colorScheme.background.toArgb()
//            window.setDecorFitsSystemWindows(false)
            WindowCompat.setDecorFitsSystemWindows(window, true)

            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }


    val orientation = when {
        windowsSizeClass.width.size > windowsSizeClass.height.size -> Orientation.Landscope
        else -> Orientation.Portroit
    }


    val sizeThatMatters = when (orientation) {
        Orientation.Portroit -> windowsSizeClass.width
        else -> windowsSizeClass.height
    }

    val dimensions = when (sizeThatMatters) {
        is WindowsSize.Small -> smallDimensions
        is WindowsSize.Compact -> compactDimensions
        is WindowsSize.Medium -> mediumDimensions
        else -> largeDimensions
    }

    val typography = when (sizeThatMatters) {
        is WindowsSize.Small -> typographySmall
        is WindowsSize.Compact -> typographyCompact
        is WindowsSize.Medium -> typographyMedium
        else -> typographyBig
    }

    ProvideAppUtils(dimensions = dimensions, orientation = orientation) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            content = content,
        )
    }
}

object AppTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
    val orientation: Orientation
        @Composable
        get() = LocalorientationMode.current

}