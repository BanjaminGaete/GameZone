package com.example.gamezoneapp.views

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CamaraScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val previewView = remember { PreviewView(context) }

    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    var permisoConcedido by remember { mutableStateOf(false) }
    var flashAlpha by remember { mutableStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            permisoConcedido = granted
            if (!granted) {
                Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
    )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    LaunchedEffect(lensFacing, permisoConcedido) {
        if (!permisoConcedido) return@LaunchedEffect
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        val selector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, selector, preview)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = flashAlpha))
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            IconButton(
                onClick = {
                    flashAlpha = 0.8f
                    coroutineScope.launch {
                        delay(100)
                        flashAlpha = 0f
                    }
                },
                modifier = Modifier
                    .size(72.dp)
                    .background(Color.White, shape = CircleShape)
            ) {}
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = {
                lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK)
                    CameraSelector.LENS_FACING_FRONT
                else
                    CameraSelector.LENS_FACING_BACK
            }) {
                Icon(Icons.Default.Cached, contentDescription = "Cambiar cámara", tint = Color.White)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}
