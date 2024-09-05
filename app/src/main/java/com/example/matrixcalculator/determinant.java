package com.example.matrixcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class determinant extends AppCompatActivity {
    EditText matrixInput;
     Button calculateButton;
    TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determinant);
        matrixInput = findViewById(R.id.matrix_input);
        calculateButton = findViewById(R.id.calculate_button);
        resultText = findViewById(R.id.result_text);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = matrixInput.getText().toString();
                double[][] matrix = parseMatrix(input);
                double determinant = calculateDeterminant(matrix);
                resultText.setText("Determinant: " + determinant);
            }
        });
    }

//    private double[][] parseMatrix(String input) {
//        String[] rows = input.split(";");
//        double[][] matrix = new double[rows.length][];
//        for (int i = 0; i < rows.length; i++) {
//            String[] values = rows[i].trim().split(",\\s*");
//            matrix[i] = new double[values.length];
//            for (int j = 0; j < values.length; j++) {
//                matrix[i][j] = Double.parseDouble(values[j]);
//            }
//        }
//        return matrix;
//    }
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


    private double calculateDeterminant(double[][] matrix) {
        if (matrix.length == 2 && matrix[0].length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            double determinant = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                determinant += matrix[0][i] * Math.pow(-1, i) * calculateDeterminant(getSubmatrix(matrix, 0, i));
            }
            return determinant;
        }
    }

    private double[][] getSubmatrix(double[][] matrix, int rowToRemove, int colToRemove) {
        double[][] submatrix = new double[matrix.length - 1][matrix[0].length - 1];
        int newRow = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == rowToRemove) {
                continue;
            }
            int newCol = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == colToRemove) {
                    continue;
                }
                submatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }
        return submatrix;
    }


}