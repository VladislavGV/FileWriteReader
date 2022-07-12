package ru.synergy.filewritereader;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.TextureView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Разобраться с автоимпортом !!!

public class MainActivity extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private File getExternalFilePath(){                           // меняем место хранения файла для общего доступа
        return new File(getExternalFilesDir(null), FILE_NAME);

    }


    // сохранение файла
    public void saveText(View view) {
        FileOutputStream fos = null;

        try {
            EditText textBox = (EditText) findViewById(R.id.editor); // находим поле EditText
            String text = textBox.getText().toString(); // достаем текст, преобразуем в String
            fos = new FileOutputStream(getExternalFilePath()); // открываем из места хранения файла
        // fos = openFileOutput(FILE_NAME, MODE_PRIVATE); // находим сам файл, (имя файла, доступ - только владелец)
            fos.write(text.getBytes()); // записываем байты текста, создаем массив байтов по размеру String
            Toast.makeText(this, "Файл успешно сохранен", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // открытие файла
    public void openText(View view) {
        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.text); // находим поле для вывода текста
        File file = getExternalFilePath();

        try {
              fin = new FileInputStream(file);
        //    fin = openFileInput(FILE_NAME); // при помощи openFileInput создаем String для FILE_NAME
            byte[] bytes = new byte[fin.available()]; // создаем массив по размеру нашего файла
            fin.read(bytes); // читаем файл/массив байтов
            String text = new String(bytes); // создаем новый String для нашего гового потока
            textView.setText(text); // показ текста

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    }
}