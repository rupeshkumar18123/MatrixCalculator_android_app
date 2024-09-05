package com.example.matrixcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addition extends AppCompatActivity {
    EditText matrixInputa,matrixInputb;
    Button calculateButton;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        matrixInputa = findViewById(R.id.matrix_inputa);
        matrixInputb = findViewById(R.id.matrix_inputb);
        calculateButton = findViewById(R.id.calculate_button);
        resultText = findViewById(R.id.result_text);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputa = matrixInputa.getText().toString();
                String inputb = matrixInputb.getText().toString();
                double[][] matrixa = parseMatrix(inputa);
                double[][] matrixb = parseMatrix(inputb);
                int m= rows(matrixa);
                int n=columns(matrixa);
                int x= rows(matrixb);
                int y=columns(matrixb);

                if(m==x && n==y){

                    double[][] resultMatrix = add_matrix(matrixa,matrixb,m,n);

                    // Format resultMatrix as a string
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < resultMatrix.length; i++) {
                        for (int j = 0; j < resultMatrix[i].length; j++) {
                            sb.append(resultMatrix[i][j]);
                            if (j < resultMatrix[i].length - 1) {
                                sb.append(" ");
                            }
                        }
                        sb.append(",");
                    }
                    String resultMatrixString = sb.toString();

                    // Display resultMatrix in EditText
                    resultText.setText(resultMatrixString);
                }
                else {
                    resultText.setText("INVALID INFORMATION.....");
                }


            }
        });

    }

    double[][] parseMatrix(String input) {
        String[] rows = input.split(",");
        double[][] matrix = new double[rows.length][];
        for (int i = 0; i < rows.length; i++) {
//        String[] values = rows[i].trim().split(",\\s*");
            String[] values = rows[i].trim().split("\\s+");

            matrix[i] = new double[values.length];
            for (int j = 0; j < values.length; j++) {
                if (!values[j].isEmpty()) {
                    matrix[i][j] = Double.parseDouble(values[j]);
                }
            }
        }
        return matrix;
    }
     int rows(double[][] matrix) {
        return matrix.length;
    }

     int columns(double[][] matrix) {
        int maxCols = 0;
        for (int i = 0; i < matrix.length; i++) {
            int numCols = matrix[i].length;
            if (numCols > maxCols) {
                maxCols = numCols;
            }
        }
        return maxCols;
    }
    public double[][] add_matrix(double[][] arr,double[][] ar,int m,int n){
        double[][] add=new double[m][n];
        for (int i = 0; i < m; i++) {
            System.out.println();
            for (int j = 0; j < n; j++) {
                add[i][j]= (arr[i][j] + ar[i][j]);

            }
        }
        return add;
    }

}