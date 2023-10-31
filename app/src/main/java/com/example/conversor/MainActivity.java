package com.example.conversor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText bitcoinEditText;
    private Spinner currencySpinner;
    private Button convertButton;
    private TextView resultTextView;

    // Tasas de cambio segun google
    private double[] exchangeRates = {36569.05, 22051.30, 24630.67, 509163.58}; // BTC a CAD, GBP, CHF, ZAR

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bitcoinEditText = findViewById(R.id.elNumero);
        currencySpinner = findViewById(R.id.spOpciones);
        convertButton = findViewById(R.id.btnCalcular);
        resultTextView = findViewById(R.id.tvResultado);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener la cantidad de Bitcoin ingresada por el usuario
                String bitcoinAmountStr = bitcoinEditText.getText().toString();
                double bitcoinAmount = Double.parseDouble(bitcoinAmountStr);

                // Obtener la tasa de cambio según la moneda seleccionada
                int selectedCurrencyIndex = currencySpinner.getSelectedItemPosition();
                double exchangeRate = exchangeRates[selectedCurrencyIndex - 1]; //

                // Realizar la conversión
                double convertedAmount = bitcoinAmount * exchangeRate;

                // Mostrar el resultado
                String selectedCurrency = currencySpinner.getSelectedItem().toString();
                resultTextView.setText(String.format("%.8f BTC = %.2f %s", bitcoinAmount, convertedAmount, selectedCurrency));
            }
        });
    }
}
