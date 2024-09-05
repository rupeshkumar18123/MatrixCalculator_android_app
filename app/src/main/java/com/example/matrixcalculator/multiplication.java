package com.example.matrixcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class multiplication extends AppCompatActivity {
    EditText matrixInputa,matrixInputb;
    Button calculateButton;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
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

                if(n==x){

                    double[][] resultMatrix = multi_matrix(matrixa,matrixb,m,n,y);

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
                } else if (x!=n) {
                    resultText.setText("column of A not equal to row B");
                } else {
                    resultText.setText("INVALID INFORMATION.....");
                }

            }
        });
    }

    private double[][] parseMatrix(String input) {
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
    public double[][] multi_matrix(double[][] arr,double[][] ar,int m,int n,int y) {

        double[][] ml = new double[m][y];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < y; j++) {

                for (int k = 0; k < n; k++) {
                    ml[i][j] = ml[i][j] + (arr[i][k] * ar[k][j]);
                }
            }
        }
        return ml;

    }
    }


