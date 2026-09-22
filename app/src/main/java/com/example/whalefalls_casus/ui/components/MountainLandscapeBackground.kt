package com.example.whalefalls_casus.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MountainLandscapeBackground(
    modifier: Modifier = Modifier,
    height: Dp = 180.dp
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val w = size.width
        val h = size.height

        // Layer 1: Background distant mountain range (Lighter misty green)
        val mountain1 = Path().apply {
            moveTo(0f, h * 0.55f)
            quadraticTo(w * 0.20f, h * 0.25f, w * 0.40f, h * 0.45f)
            quadraticTo(w * 0.65f, h * 0.15f, w * 0.85f, h * 0.38f)
            lineTo(w, h * 0.50f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(mountain1, Color(0xFF90B0A0).copy(alpha = 0.5f))

        // Layer 2: Midground mountain range
        val mountain2 = Path().apply {
            moveTo(0f, h * 0.65f)
            quadraticTo(w * 0.15f, h * 0.40f, w * 0.35f, h * 0.55f)
            quadraticTo(w * 0.50f, h * 0.30f, w * 0.70f, h * 0.50f)
            quadraticTo(w * 0.88f, h * 0.35f, w, h * 0.60f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(mountain2, Color(0xFF537868).copy(alpha = 0.75f))

        // Layer 3: Foreground mountain & pine trees silhouette (Deep Green)
        val mountain3 = Path().apply {
            moveTo(0f, h * 0.75f)
            quadraticTo(w * 0.25f, h * 0.52f, w * 0.55f, h * 0.70f)
            quadraticTo(w * 0.80f, h * 0.48f, w, h * 0.68f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(mountain3, Color(0xFF1F3A2E))

        // Draw Pine Trees on Foreground Silhouette
        val treeColor = Color(0xFF152A20)
        val treeSpacing = w / 35f
        var x = 0f
        while (x <= w) {
            val treeHeight = (18..35).random() * (h / 180f)
            val treeWidth = (10..18).random() * (w / 400f)
            val treeY = h * 0.75f + kotlin.math.sin(x * 0.02).toFloat() * 15f

            val treePath = Path().apply {
                moveTo(x, treeY - treeHeight)
                lineTo(x + treeWidth, treeY)
                lineTo(x - treeWidth, treeY)
                close()
            }
            drawPath(treePath, treeColor)

            x += treeSpacing + (0..6).random()
        }
    }
}
