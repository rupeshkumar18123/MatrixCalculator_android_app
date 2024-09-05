package com.example.matrixcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class solutionm extends AppCompatActivity {
    EditText matrixInputa,matrixInputd;
    Button calculateButton;
    TextView resultText;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutionm);

        spin=findViewById(R.id.spin);

        ArrayList<String> arr= new ArrayList<>();
        arr.add("choose number of variable");
        arr.add("two variable");
        arr.add("three variable");
        arr.add("four variable");

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        matrixInputa = findViewById(R.id.matrix_inputa);
        matrixInputd = findViewById(R.id.matrix_inputd);
        calculateButton = findViewById(R.id.calculate_button);
        resultText = findViewById(R.id.result_text);
       // ed2=findViewById(R.id.ed2);



        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputa = matrixInputa.getText().toString();
                double[][] matrixa = parseMatrix(inputa);
                String inputb = matrixInputd.getText().toString();
                double[][] matrixb = parseMatrix(inputb);
                int m= rows(matrixa);
                int n=column(matrixa);
                int x= rows(matrixb);
                int y=column(matrixb);
                solutionla(x,matrixa,matrixb,m);

                //resultText.setText("X=" + x+"Y="+y+"Z="+z);
            }
        });
    }


double[][] parseMatrix(String input) {
    String[] rows = input.split(",");
    double[][] matrix = new double[rows.length][];
    for (int i = 0; i < rows.length; i++) {
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

     int column(double[][] matrix) {
        int maxCols = 0;
        for (int i = 0; i < matrix.length; i++) {
            int numCols = matrix[i].length;
            if (numCols > maxCols) {
                maxCols = numCols;
            }
        }
        return maxCols;
    }


    //solution of matrix
                    public void solutionla(int x,double[][] arr,double[][] d,int m) {

                    int s=x;
                    switch (s) {
                        case 2:
                            matrix2(arr,d);
                            break;
                        case 3:
                            matrix3(arr,d);
                            break;
                        case 4:
                            matrix4(arr,d);
                            break;
                        default:
                            resultText.setText("wrong input..........");
                    }

         }


         //determinant of matrix

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

     double[][] getSubmatrix(double[][] matrix, int rowToRemove, int colToRemove) {
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


    //variables matrix
    public void matrix2(double[][] arr , double[][] d) {
        int m = 2;



        double A = (arr[0][0] * arr[1][1]) - (arr[0][1] * arr[1][0]);
        double D1 = (d[0][0] * arr[1][1]) - (arr[0][1] * d[1][0]);
        double D2 = (arr[0][0] * d[1][0]) - (d[0][0] * arr[1][0]);


        if (A == 0) {
            if (D1 != 0 || D2 != 0) {
                resultText.setText("System has NO SOLUTIOn");
            } else {
                resultText.setText("System has INFINTELY MANY SOLUTION");
            }
        } else {
            double X = D1 / A;
            double Y = D2 / A;
           resultText.setText("x="+X+"  y="+Y);
        }

    }



    // 3 variable

    public void matrix3(double[][] ar ,double[][] d) {
        int m = 3;


        double e1 = ar[0][0] * ((ar[2][2] * ar[1][1]) - (ar[2][1] * ar[1][2]));
        double e2 = ar[0][1] * ((ar[2][0] * ar[1][2]) - (ar[1][0] * ar[2][2]));
        double e3 = ar[0][2] * ((ar[2][1] * ar[1][0]) - (ar[2][0] * ar[1][1]));

        double A = e1 + e2 + e3;

        double e4 = d[0][0] * ((ar[2][2] * ar[1][1]) - (ar[2][1] * ar[1][2]));
        double e5 = ar[0][1] * ((d[2][0] * ar[1][2]) - (d[1][0] * ar[2][2]));
        double e6 = ar[0][2] * ((ar[2][1] * d[1][0]) - (d[2][0] * ar[1][1]));

        double D1 = e4 + e5 + e6;

        double e7 = ar[0][0] * ((ar[2][2] * d[1][0]) - (d[2][0] * ar[1][2]));
        double e8 = d[0][0] * ((ar[2][0] * ar[1][2]) - (ar[1][0] * ar[2][2]));
        double e9 = ar[0][2] * ((d[2][0] * ar[1][0]) - (ar[2][0] * d[1][0]));

        double D2 = e7 + e8 + e9;

        double e10 = ar[0][0] * ((d[2][0] * ar[1][1]) - (ar[2][1] * d[1][0]));
        double e11 = ar[0][1] * ((ar[2][0] * d[1][0]) - (ar[1][0] * d[2][0]));
        double e12 = d[0][0] * ((ar[2][1] * ar[1][0]) - (ar[2][0] * ar[1][1]));

        double D3 = e10 + e11 + e12;


        if (A == 0) {
            if (D1 != 0 || D2 != 0 || D3 != 0) {

                resultText.setText("System has NO SOLUTIOn");
            } else {
                resultText.setText("System has INFINTELY MANY SOLUTION");

            }
        } else {
            double X = D1 / A;
            double Y = D2 / A;
            double Z = D3 / A;
            resultText.setText(" x ="+X+"  y ="+Y+" Z =" + Z);

        }

    }

    // 4 variables
    public void matrix4(double[][] ar,double[][] d) {
        int m = 4;



        // clone of matrix ar
        double[][] arm = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                arm[i][j] = ar[i][j];
            }
        }
        double A = calculateDeterminant(ar);
        // calculate D1
        arm[0][0] = d[0][0];
        arm[1][0] = d[1][0];
        arm[2][0] = d[2][0];
        arm[3][0] = d[3][0];

        double D1 = calculateDeterminant(arm);

        // claculate D2
        arm[0][0] = ar[0][0];
        arm[1][0] = ar[1][0];
        arm[2][0] = ar[2][0];
        arm[3][0] = ar[3][0];

        arm[0][1] = d[0][0];
        arm[1][1] = d[1][0];
        arm[2][1] = d[2][0];
        arm[3][1] = d[3][0];

        double D2 = calculateDeterminant(arm);

        // claculate D3
        arm[0][1] = ar[0][1];
        arm[1][1] = ar[1][1];
        arm[2][1] = ar[2][1];
        arm[3][1] = ar[3][1];

        arm[0][2] = d[0][0];
        arm[1][2] = d[1][0];
        arm[2][2] = d[2][0];
        arm[3][2] = d[3][0];

        double D3 = calculateDeterminant(arm);

        // claculate D4
        arm[0][2] = ar[0][2];
        arm[1][2] = ar[1][2];
        arm[2][2] = ar[2][2];
        arm[3][2] = ar[3][2];

        arm[0][3] = d[0][0];
        arm[1][3] = d[1][0];
        arm[2][3] = d[2][0];
        arm[3][3] = d[3][0];

        double D4 = calculateDeterminant(arm);

        if (A == 0) {
            if (D1 != 0 || D2 != 0 || D3 != 0 || D4 != 0) {
                resultText.setText("System has NO SOLUTIOn");
            } else {
                resultText.setText("System has INFINTELY MANY SOLUTION");
            }
        } else {
            double X = D1 / A;
            double Y = D2 / A;
            double Z = D3 / A;
            double R = D4 / A;
            resultText.setText(" x ="+X+"  y ="+Y+" Z =" + Z+" w =" + R);

        }

    }


}