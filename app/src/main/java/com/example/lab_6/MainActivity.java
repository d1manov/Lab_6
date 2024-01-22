package com.example.lab_6;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private DBHelper helper;
    private SQLiteDatabase database;
    private ListView sedan_data, crossover_data, SUV_data;
    private Button sedan_btn, crossover_btn, SUV_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sedan_data = findViewById(R.id.sedan_data);
        crossover_data = findViewById(R.id.crossover_data);
        SUV_data = findViewById(R.id.SUV_data);

        sedan_btn = findViewById(R.id.sedan_btn);
        crossover_btn = findViewById(R.id.crossover_btn);
        SUV_btn = findViewById(R.id.SUV_btn);

        helper = new DBHelper(getApplicationContext());
        try {
            database = helper.getWritableDatabase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        sedan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HashMap<String, String>> cars = new ArrayList<>();
                HashMap<String, String> car;
                Cursor cursor = database.rawQuery("SELECT * FROM car_db WHERE \"group\" = 1 ", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    car = new HashMap<>();
                    car.put("carName", cursor.getString(1));
                    car.put("carInfo", "category: " + cursor.getString(2));
                    cars.add(car);
                    cursor.moveToNext();
                }
                cursor.close();
                SimpleAdapter adapter = new SimpleAdapter(
                        getApplicationContext(),
                        cars,
                        android.R.layout.simple_list_item_2,
                        new String[]{"carName","carInfo"},
                        new int[]{android.R.id.text1, android.R.id.text2}
                );
                sedan_data.setAdapter(adapter);
            }
        });

        crossover_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HashMap<String, String>> cars = new ArrayList<>();
                HashMap<String, String> car;
                Cursor cursor = database.rawQuery("SELECT * FROM car_db WHERE \"group\" = 2 ", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    car = new HashMap<>();
                    car.put("carName", cursor.getString(1));
                    car.put("carInfo", "category: " + cursor.getString(2));
                    cars.add(car);
                    cursor.moveToNext();
                }
                cursor.close();
                SimpleAdapter adapter = new SimpleAdapter(
                        getApplicationContext(),
                        cars,
                        android.R.layout.simple_list_item_2,
                        new String[]{"carName","carInfo"},
                        new int[]{android.R.id.text1, android.R.id.text2}
                );
                crossover_data.setAdapter(adapter);
            }
        });

        SUV_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HashMap<String, String>> cars = new ArrayList<>();
                HashMap<String, String> car;
                Cursor cursor = database.rawQuery("SELECT * FROM car_db WHERE \"group\" = 3 ", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    car = new HashMap<>();
                    car.put("carName", cursor.getString(1));
                    car.put("carInfo", "category: " + cursor.getString(2));
                    cars.add(car);
                    cursor.moveToNext();
                }
                cursor.close();
                SimpleAdapter adapter = new SimpleAdapter(
                        getApplicationContext(),
                        cars,
                        android.R.layout.simple_list_item_2,
                        new String[]{"carName","carInfo"},
                        new int[]{android.R.id.text1, android.R.id.text2}
                );
                SUV_data.setAdapter(adapter);
            }
        });
    }
}