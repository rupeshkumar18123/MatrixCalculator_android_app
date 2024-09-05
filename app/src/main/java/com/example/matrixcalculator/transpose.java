package com.example.matrixcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class transpose extends AppCompatActivity {
    EditText matrix1Input;
    EditText resultMatrixOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transpose);
        matrix1Input = findViewById(R.id.matrix1);
        resultMatrixOutput = findViewById(R.id.result_matrix);

        // Attach click listener to transpose button
        Button transposeButton = findViewById(R.id.transpose_button);
        transposeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input matrix from EditText
                String matrix1String = matrix1Input.getText().toString();

                // Parse matrix1String into a 2D array of doubles
                String[] rows = matrix1String.split(",");
                double[][] matrix1 = new double[rows.length][];
                for (int i = 0; i < rows.length; i++) {
                    String[] cols = rows[i].split(" ");
                    matrix1[i] = new double[cols.length];
                    for (int j = 0; j < cols.length; j++) {
                        matrix1[i][j] = Double.parseDouble(cols[j]);
                    }
                }

                // Compute transpose of matrix1
                double[][] resultMatrix = transpose(matrix1);

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
                resultMatrixOutput.setText(resultMatrixString);
            }
        });
    }

    // Helper function to compute the transpose of a matrix
    private double[][] transpose(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}