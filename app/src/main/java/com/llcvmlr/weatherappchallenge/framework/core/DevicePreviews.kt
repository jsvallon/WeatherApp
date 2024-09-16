package com.llcvmlr.weatherappchallenge.framework.core

import androidx.compose.ui.tooling.preview.Preview

/**
 * Custom annotation for previewing Jetpack Compose UI components across various device sizes and orientations.
 *
 * This annotation allows developers to render composables in different device configurations to ensure
 * that the UI behaves correctly across a range of screen sizes and orientations. By applying this annotation
 * to a composable function, you can visualize how your UI will look on various devices without having to run
 * the app on multiple physical devices or emulators.
 *
 * The following device configurations are represented by this annotation:
 *
 * - **Phone**: A standard phone screen with a resolution of 360x640 dp.
 * - **Landscape**: A landscape-oriented phone screen with a resolution of 640x360 dp.
 * - **Foldable**: A foldable device with a resolution of 673x841 dp.
 * - **Tablet**: A tablet screen with a resolution of 1280x800 dp.
 *
 * Each configuration is specified with the following parameters:
 * - `shape=Normal`: Specifies the shape of the device as normal (rectangular).
 * - `width`: The width of the device screen in density-independent pixels (dp).
 * - `height`: The height of the device screen in density-independent pixels (dp).
 * - `unit=dp`: The unit of measurement used for width and height.
 * - `dpi=480`: The screen density of the device.
 *
 * All previews are rendered with a background for better visibility.
 *
 * Example usage:
 * ```
 * @DevicePreviews
 * @Composable
 * fun MyComposablePreview() {
 *     MyComposable()
 * }
 * ```
 *
 * @see Preview
 */
@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480", showBackground = true)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480", showBackground = true)
@Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480", showBackground = true)
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480", showBackground = true)
annotation class DevicePreviews
