package ru.novak.cezar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputText = findViewById(R.id.inputText);
        EditText inputShift = findViewById(R.id.inputShift);
        Button encryptButton = findViewById(R.id.encryptButton);
        Button decryptButton = findViewById(R.id.decryptButton);
        TextView resultText = findViewById(R.id.resultText);

        // Обработчик для кнопки "Зашифровать"
        encryptButton.setOnClickListener(v -> {
            String text = inputText.getText().toString();
            int shift = Integer.parseInt(inputShift.getText().toString());
            String encryptedText = encrypt(text, shift);
            resultText.setText("Зашифрованный текст: " + encryptedText);
        });

        // Обработчик для кнопки "Расшифровать"
        decryptButton.setOnClickListener(v -> {
            String text = inputText.getText().toString();
            int shift = Integer.parseInt(inputShift.getText().toString());
            String decryptedText = decrypt(text, shift);
            resultText.setText("Расшифрованный текст: " + decryptedText);
        });
    }

    // Метод для шифрования текста
    // Шифрование с поддержкой русского и английского языков
    private String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (isEnglishLetter(ch)) { // Если английская буква
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char encryptedChar = (char) (((ch - base + shift) % 26) + base);
                result.append(encryptedChar);
            } else if (isRussianLetter(ch)) { // Если русская буква
                char base = Character.isUpperCase(ch) ? 'А' : 'а';
                char encryptedChar = (char) (((ch - base + shift) % 32) + base);
                result.append(encryptedChar);
            } else {
                result.append(ch); // Прочие символы не меняем
            }
        }

        return result.toString();
    }

    // Расшифровка с поддержкой русского и английского языков
    private String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (isEnglishLetter(ch)) { // Если английская буква
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char decryptedChar = (char) (((ch - base - shift + 26) % 26) + base);
                result.append(decryptedChar);
            } else if (isRussianLetter(ch)) { // Если русская буква
                char base = Character.isUpperCase(ch) ? 'А' : 'а';
                char decryptedChar = (char) (((ch - base - shift + 32) % 32) + base);
                result.append(decryptedChar);
            } else {
                result.append(ch); // Прочие символы не меняем
            }
        }

        return result.toString();
    }

    // Проверка, является ли символ английской буквой
    private boolean isEnglishLetter(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    // Проверка, является ли символ русской буквой
    private boolean isRussianLetter(char ch) {
        return (ch >= 'А' && ch <= 'Я') || (ch >= 'а' && ch <= 'я');
    }

}