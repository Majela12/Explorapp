package me.mariaherrera.exploraapp.ui.elements

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import me.mariaherrera.exploraapp.ui.theme.ExploraAppTheme

@Composable
fun LoginScreen(
    onSuccessfulLogin: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
) {
    val auth = Firebase.auth
    val context = LocalContext.current
    val activity = context as Activity

    // ESTADOS
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf("") }

    val primaryOrange = Color(0xFFE45D25)
    val lightGrayBg = Color(0xFFF8F9FE)
    val inputBg = Color(0xFFE5E5EA)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = lightGrayBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // HEADER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(primaryOrange),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Logo",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Explorando Colombia",
                        color = primaryOrange,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Bienvenido",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Explora la magia de Colombia hoy.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {

                // EMAIL
                Text("CORREO ELECTRÓNICO", fontSize = 12.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = inputEmail,
                    onValueChange = { inputEmail = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    placeholder = { Text("nombre@ejemplo.com") },
                    leadingIcon = { Icon(Icons.Default.Email, null) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = inputBg,
                        unfocusedContainerColor = inputBg,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                // PASSWORD
                Text("CONTRASEÑA", fontSize = 12.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = inputPassword,
                    onValueChange = { inputPassword = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    placeholder = { Text("........") },
                    leadingIcon = { Icon(Icons.Default.Lock, null) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = inputBg,
                        unfocusedContainerColor = inputBg,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                if (loginError.isNotEmpty()) {
                    Text(loginError, color = Color.Red)
                }

                // BOTÓN LOGIN
                Button(
                    onClick = {
                        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                            .addOnCompleteListener(activity!!) { task ->
                                if (task.isSuccessful) {
                                    onSuccessfulLogin()
                                } else {
                                    loginError = "Error al iniciar sesión"
                                }
                            }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(primaryOrange, Color(0xFFFF8A65))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Iniciar Sesión")
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row {
                    Text("¿No tienes cuenta? ")
                    Text(
                        "Regístrate",
                        color = primaryOrange,
                        modifier = Modifier.clickable { onNavigateToRegister() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ExploraAppTheme {
        LoginScreen()
    }
}