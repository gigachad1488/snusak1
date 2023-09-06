package com.example.ktoprochitaltotzdohnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private Button[][] buttons;
    private boolean isPlayerX = true;
    private boolean gameEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView;
        webView = findViewById(R.id.webView);
        // включаем поддержку JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        webView.loadUrl("https://gametable.org/games/tic-tac-toe/");

        WebView webView1;
        webView1 = findViewById(R.id.webView1);
        // включаем поддержку JavaScript
        webView1.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        webView1.loadUrl("https://animego.org/anime/ya-pererodilsya-torgovym-avtomatom-i-skitayus-po-labirintu-2346#video-watch2");
        gridLayout = findViewById(R.id.gridLayout);
        Button resbutton = findViewById(R.id.buttonRes);
        resbutton.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             initializeButtons();
                                         }
                                     });
        initializeButtons();
    }

    private void initializeButtons() {
        gridLayout.removeAllViews();
        gameEnded = false;
        isPlayerX = true;
        buttons = new Button[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setTextSize(24);
                buttons[i][j].setTag(R.id.gridCell, new int[]{i, j});
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onGridButtonClick((Button) view);
                    }
                });
                gridLayout.addView(buttons[i][j]);
            }
        }
    }

    private void onGridButtonClick(Button button) {
        if (gameEnded) {
            return;
        }

        int[] position = (int[]) button.getTag(R.id.gridCell);
        int row = position[0];
        int col = position[1];

        if (buttons[row][col].getText().toString().isEmpty()) {
            if (isPlayerX) {
                buttons[row][col].setText("X");
            } else {
                buttons[row][col].setText("O");
            }
            isPlayerX = !isPlayerX;

            if (checkForWin(row, col)) {
                String winner = isPlayerX ? "O" : "X";
                Toast.makeText(this, "Игрок " + winner + " выиграл!", Toast.LENGTH_SHORT).show();
                gameEnded = true;
            } else if (isBoardFull()) {
                Toast.makeText(this, "Ничья!", Toast.LENGTH_SHORT).show();
                gameEnded = true;
            }
        }
    }

    private boolean checkForWin(int row, int col) {
        if (buttons[row][0].getText().equals(buttons[row][1].getText()) && buttons[row][1].getText().equals(buttons[row][2].getText())) {
            buttons[row][0].setBackgroundColor(Color.GREEN);
            buttons[row][1].setBackgroundColor(Color.GREEN);
            buttons[row][2].setBackgroundColor(Color.GREEN);
            return true;
        } else if (buttons[0][col].getText().equals(buttons[1][col].getText()) && buttons[1][col].getText().equals(buttons[2][col].getText())) {
            buttons[0][col].setBackgroundColor(Color.GREEN);
            buttons[1][col].setBackgroundColor(Color.GREEN);
            buttons[2][col].setBackgroundColor(Color.GREEN);
            return true;
        } else if (row == col && buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText())) {
            buttons[0][0].setBackgroundColor(Color.GREEN);
            buttons[1][1].setBackgroundColor(Color.GREEN);
            buttons[2][2].setBackgroundColor(Color.GREEN);
            return true;
        } else if ((row + col) == 2 && buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText())) {
            buttons[0][2].setBackgroundColor(Color.GREEN);
            buttons[1][1].setBackgroundColor(Color.GREEN);
            buttons[2][0].setBackgroundColor(Color.GREEN);
            return true;
        } else {
            return false;
        }
    }


    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}