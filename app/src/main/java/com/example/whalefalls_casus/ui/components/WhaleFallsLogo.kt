package com.example.whalefalls_casus.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whalefalls_casus.theme.DeepGreen
import com.example.whalefalls_casus.theme.SageGreen

@Composable
fun WhaleFallsLogo(
    modifier: Modifier = Modifier,
    whaleHeight: Dp = 80.dp,
    textColor: Color = DeepGreen,
    accentColor: Color = SageGreen,
    showText: Boolean = true,
    compactText: Boolean = false
)
{
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Whale Illustration Canvas
        WhaleGraphic(
            modifier = Modifier
                .fillMaxWidth()
                .height(whaleHeight),
            primaryColor = textColor,
            secondaryColor = accentColor
        )

        if (showText) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "WHALEFALLS",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = if (compactText) 16.sp else 22.sp,
                letterSpacing = 4.sp,
                color = textColor
            )

            // Thin divider line
            Canvas(
                modifier = Modifier
                    .width(if (compactText) 100.dp else 140.dp)
                    .height(2.dp)
                    .padding(vertical = 1.dp)
            ) {
                drawLine(
                    color = textColor.copy(alpha = 0.5f),
                    start = Offset(0f, size.height / 2),
                    end = Offset(size.width, size.height / 2),
                    strokeWidth = 1f
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "C A S U S",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = if (compactText) 9.sp else 11.sp,
                letterSpacing = 6.sp,
                color = textColor.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
fun WhaleGraphic(
    modifier: Modifier = Modifier,
    primaryColor: Color = DeepGreen,
    secondaryColor: Color = SageGreen
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val centerX = w / 2f
        val centerY = h * 0.5f

        // Draw Whale Outer Body Silhouette
        val bodyPath = Path().apply {
            // Start at tail tip
            moveTo(centerX - w * 0.42f, centerY + h * 0.15f)
            // Up to tail top fluke
            quadraticTo(
                centerX - w * 0.44f, centerY - h * 0.25f,
                centerX - w * 0.38f, centerY - h * 0.30f
            )
            // Down tail base to back ridge
            quadraticTo(
                centerX - w * 0.25f, centerY - h * 0.18f,
                centerX - w * 0.05f, centerY - h * 0.35f
            )
            // Dorsal hump to snout
            quadraticTo(
                centerX + w * 0.20f, centerY - h * 0.42f,
                centerX + w * 0.42f, centerY + h * 0.10f
            )
            // Snout to jaw / mouth line
            quadraticTo(
                centerX + w * 0.38f, centerY + h * 0.32f,
                centerX + w * 0.28f, centerY + h * 0.38f
            )
            // Pectoral fin right
            cubicTo(
                centerX + w * 0.30f, centerY + h * 0.45f,
                centerX + w * 0.36f, centerY + h * 0.48f,
                centerX + w * 0.38f, centerY + h * 0.35f
            )
            // Belly contour to tail
            quadraticTo(
                centerX + w * 0.05f, centerY + h * 0.30f,
                centerX - w * 0.22f, centerY + h * 0.12f
            )
            // Bottom fluke curve
            quadraticTo(
                centerX - w * 0.36f, centerY + h * 0.35f,
                centerX - w * 0.42f, centerY + h * 0.15f
            )
            close()
        }

        // Fill body with semi-transparent Sage Green
        drawPath(
            path = bodyPath,
            color = secondaryColor.copy(alpha = 0.55f)
        )

        // Outline body with Deep Green
        drawPath(
            path = bodyPath,
            color = primaryColor,
            style = Stroke(width = 2.5f)
        )

        // Draw Skeleton / Ribs lines (Whale Falls anatomical motif)
        // Spine line
        val spinePath = Path().apply {
            moveTo(centerX - w * 0.36f, centerY - h * 0.08f)
            quadraticTo(
                centerX, centerY - h * 0.18f,
                centerX + w * 0.32f, centerY - h * 0.02f
            )
        }
        drawPath(
            path = spinePath,
            color = primaryColor,
            style = Stroke(width = 2f)
        )

        // Draw Rib arcs
        for (i in 0..10) {
            val ratio = i / 10f
            val ribX = centerX - w * 0.22f + (w * 0.42f * ratio)
            val topY = centerY - h * 0.18f + (h * 0.12f * ratio)
            val bottomY = centerY + h * 0.15f - (h * 0.05f * ratio)

            drawLine(
                color = primaryColor.copy(alpha = 0.85f),
                start = Offset(ribX, topY),
                end = Offset(ribX + w * 0.015f, bottomY),
                strokeWidth = 1.8f
            )
        }

        // Skull & Jaw contours
        val skullPath = Path().apply {
            moveTo(centerX + w * 0.20f, centerY - h * 0.12f)
            quadraticTo(
                centerX + w * 0.32f, centerY - h * 0.05f,
                centerX + w * 0.39f, centerY + h * 0.12f
            )
            moveTo(centerX + w * 0.22f, centerY + h * 0.18f)
            lineTo(centerX + w * 0.35f, centerY + h * 0.22f)
        }
        drawPath(
            path = skullPath,
            color = primaryColor,
            style = Stroke(width = 2f)
        )
    }
}
