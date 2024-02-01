package ru.radzze.effective_mobile.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.radzze.effective_mobile.R
import ru.radzze.effective_mobile.ui.theme.sf_pro
import ru.radzze.effective_mobile.ui.topAppBar

@Composable
fun AuthScreen(
    viewModel: AuthVieModel = hiltViewModel(),
    onMainNavigate: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        topAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
            title = stringResource(R.string.auth_title)
        )
        AuthInputBlock(
            modifier = Modifier.align(Alignment.Center),
            name = viewModel.name.value,
            surname = viewModel.surname.value,
            phone = viewModel.phone.value,
            nameValueChanged = { viewModel.nameValueChanged(it) },
            surnameValueChanged = { viewModel.surnameValueChanged(it) },
            phoneValueChanged = { viewModel.phoneValueChanged(it) },
            onClick = {
                onMainNavigate()
                viewModel.onBtnClick()
            },
            isNameError = viewModel.isNameFieldError.value,
            isSurnameError = viewModel.isSurnameFieldError.value,
            isPhoneError = viewModel.isPhoneFieldError.value
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.9f)
                .padding(vertical = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 10.sp,
                color = Color(160, 161, 163),
                fontFamily = sf_pro,
                text = "Нажимая кнопку “Войти”, Вы принимаете",
                lineHeight = 11.sp
            )
            Text(
                fontSize = 10.sp,
                color = Color(160, 161, 163),
                fontFamily = sf_pro,
                text = "условия программы лояльности",
                lineHeight = 11.sp,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}

@Composable
fun AuthInputBlock(
    modifier: Modifier = Modifier,
    name: String,
    surname: String,
    phone: String,
    nameValueChanged: (text: String) -> Unit,
    surnameValueChanged: (text: String) -> Unit,
    phoneValueChanged: (text: String) -> Unit,
    onClick: () -> Unit,
    isNameError: Boolean,
    isSurnameError: Boolean,
    isPhoneError: Boolean = false,
) {
    Box(modifier = modifier.fillMaxWidth(0.9f)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                value = name,
                stringResource(R.string.name_placeholder),
                onValueChanged = { nameValueChanged(it) },
                isNameError
            )
            AuthTextField(
                value = surname,
                stringResource(R.string.surname_placeholder),
                onValueChanged = { surnameValueChanged(it) },
                isSurnameError
            )
            AuthTextField(
                value = phone,
                stringResource(R.string.phone_placehiolder),
                onValueChanged = { phoneValueChanged(it) },
                isPhoneError,
                isPhone = true,
            )
            Button(
                onClick = onClick,
                enabled = !isNameError &&
                        !isSurnameError &&
                        !isPhoneError &&
                        name.isNotEmpty() &&
                        surname.isNotEmpty()
                        && phone.isNotEmpty(),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = sf_pro,
                    text = stringResource(R.string.auth_title),
                    fontWeight = FontWeight.Medium
                )
            }
        }

    }

}


@Composable
fun AuthTextField(
    value: String,
    placeholder: String,
    onValueChanged: (text: String) -> Unit,
    isError: Boolean,
    isPhone: Boolean = false
) {
    TextField(
        visualTransformation = if (isPhone) VisualTransformation { mobileNumberFilter(it) } else VisualTransformation.None,
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = { onValueChanged(it) },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = Color(248, 248, 248),
            unfocusedContainerColor = Color(248, 248, 248),
            disabledContainerColor = Color(248, 248, 248),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color(255, 0, 0, 255)
        ),
        placeholder = {
            Text(
                fontSize = 16.sp,
                color = Color.Gray,
                fontFamily = sf_pro,
                text = placeholder,
                fontWeight = FontWeight.Normal
            )
        },
        isError = isError,
        trailingIcon = {
            if (value.isNotEmpty()) {
                Image(modifier = Modifier.clickable {
                    onValueChanged("")
                }, painter = painterResource(id = R.drawable.close_icon), contentDescription = null)
            }

        },
        keyboardOptions = if (isPhone) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default,
    )
}

fun mobileNumberFilter(text: AnnotatedString): TransformedText {
    val trimmed =
        if (text.text.length >= 11) text.text.substring(0..10) else text.text // изменение длины

    val formattedNumber = buildAnnotatedString {
        for (i in trimmed.indices) {
            if (i == 0) {
                append("+7 ")
            }
            if (i == 2 || i == 5 || i == 7) {
                append("${trimmed[i]} ")
            } else {
                append(trimmed[i])
            }
        }
    }

    val translator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset < 1) return offset
            if (offset <= 2) return offset + 3
            if (offset <= 5) return offset + 4
            if (offset <= 7) return offset + 5
            if (offset <= 10) return offset + 6
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset < 2) return offset
            if (offset <= 3) return offset - 3
            if (offset <= 6) return offset - 4
            if (offset <= 8) return offset - 5
            if (offset <= 11) return offset - 6
            return 10
        }
    }

    return TransformedText(formattedNumber, translator)
}