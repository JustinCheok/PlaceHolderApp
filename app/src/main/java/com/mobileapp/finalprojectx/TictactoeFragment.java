package com.mobileapp.finalprojectx;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mobileapp.finalprojectx.databinding.FragmentTictactoeBinding;

import org.w3c.dom.Text;

import java.util.Arrays;

public class TictactoeFragment extends Fragment{

    private FragmentTictactoeBinding binding;
    int [] gameState = {2,2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {
            {1,2,3}, {4,5,6}, {7,8,9},  // rows
            {1,4,7}, {2,5,8}, {3,6,9}, // columns
            {1,5,9}, {3,5,7}    // cross
    };
    int[] combo1 = {1, 2, 3};
    int[] combo2 = {4, 5, 6};
    int[] combo3 = {7, 8, 9};
    int[] combo4 = {1, 4, 7};
    int[] combo5 = {2, 5, 8};
    int[] combo6 = {3, 6, 9};
    int[] combo7 = {1, 5, 9};
    int[] combo8 = {3, 5, 7};

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private Button resetGame;
    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;
    int playerStatusTurn;   // odd 1 = player 1 (X)     // even 2 = player 2 (O)
    int playerOneScoreCountInt, playerTwoScoreCountInt;
    String text;
    String playerOneStr, playerTwoStr;
    char[] charArray1;
    char[] charArray2;

    int p1Score, p2Score;

    boolean contains1, contains2, contains3, contains4, contains5, contains6, contains7, contains8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTictactoeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean activePlayer;
        playerOneStr = "";
        playerTwoStr = "";


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TictactoeFragment.this)
                        .navigate(R.id.action_TictactoeFragment_to_HomeFragment);
            }
        });

        playerOneScore = (TextView) getView().findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) getView().findViewById(R.id.playerTwoScore);
        //playerStatus = (TextView) getView().findViewById(R.id.playerStatus);

        resetGame = (Button) getView().findViewById(R.id.resetButton);

        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;
        //String buttonID;
        playerStatusTurn = 1;


        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 1 Clicked");

                String buttonID = "button1";
                int gameStatePointer = 1;
                String gsPointerStr = "1";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn1 = (Button) getView().findViewById(R.id.button1);
                    btn1.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn1 = (Button) getView().findViewById(R.id.button1);
                    btn1.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerTwoStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;


                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray", playerOneStr);
                Log.i("charArray", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }


                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));


                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }


                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });





        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 2 Clicked");

                String buttonID = "button2";
                int gameStatePointer = 2;
                String gsPointerStr = "2";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn2 = (Button) getView().findViewById(R.id.button2);
                    btn2.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn2 = (Button) getView().findViewById(R.id.button2);
                    btn2.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;

                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray", playerOneStr);
                Log.i("charArray", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));


                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }


                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 3 Clicked");

                String buttonID = "button3";
                int gameStatePointer = 3;
                String gsPointerStr = "3";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn3 = (Button) getView().findViewById(R.id.button3);
                    btn3.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn3 = (Button) getView().findViewById(R.id.button3);
                    btn3.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;


                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray1", playerOneStr);
                Log.i("charArray1", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));


                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }

                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 4 Clicked");

                String buttonID = "button4";
                int gameStatePointer = 4;
                String gsPointerStr = "4";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn4 = (Button) getView().findViewById(R.id.button4);
                    btn4.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn4 = (Button) getView().findViewById(R.id.button4);
                    btn4.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;


                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray1", playerOneStr);
                Log.i("charArray1", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));


                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }

                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 5 Clicked");

                String buttonID = "button5";
                int gameStatePointer = 5;
                String gsPointerStr = "5";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn5 = (Button) getView().findViewById(R.id.button5);
                    btn5.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn5 = (Button) getView().findViewById(R.id.button5);
                    btn5.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;


                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray1", playerOneStr);
                Log.i("charArray1", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));


                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }

                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8)) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }



                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 6 Clicked");

                String buttonID = "button6";
                int gameStatePointer = 6;
                String gsPointerStr = "6";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn6 = (Button) getView().findViewById(R.id.button6);
                    btn6.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn6 = (Button) getView().findViewById(R.id.button6);
                    btn6.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;

                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray1", playerOneStr);
                Log.i("charArray1", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));



                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }

                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8)) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 7 Clicked");

                String buttonID = "button7";
                int gameStatePointer = 7;
                String gsPointerStr = "7";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn7 = (Button) getView().findViewById(R.id.button7);
                    btn7.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn7 = (Button) getView().findViewById(R.id.button7);
                    btn7.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;


                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray1", playerOneStr);
                Log.i("charArray1", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));



                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }

                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8)) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 8 Clicked");

                String buttonID = "button8";
                int gameStatePointer = 8;
                String gsPointerStr = "8";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn8 = (Button) getView().findViewById(R.id.button8);
                    btn8.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn8 = (Button) getView().findViewById(R.id.button8);
                    btn8.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;

                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray1", playerOneStr);
                Log.i("charArray1", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));


                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }

                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8)) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }



                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test", "Button 9 Clicked");

                String buttonID = "button9";
                int gameStatePointer = 9;
                String gsPointerStr = "9";

                if (playerStatusTurn % 2 == 1) {      //odd player 1 X
                    btn9 = (Button) getView().findViewById(R.id.button9);
                    btn9.setText("X");
                    gameState[gameStatePointer] = 0;
                    playerOneStr = playerOneStr + gsPointerStr;
                    Log.i("playerOneStr", playerOneStr);
                }

                if (playerStatusTurn % 2 == 0) {       //even player 2 O
                    btn9 = (Button) getView().findViewById(R.id.button9);
                    btn9.setText("O");
                    gameState[gameStatePointer] = 1;
                    playerTwoStr = playerTwoStr + gsPointerStr;
                    Log.i("playerOneStr", playerTwoStr);
                }
                playerStatusTurn++;
                rountCount++;


                // PlayerOneStr to CharArray1
                charArray1 = playerOneStr.toCharArray();
                Arrays.sort(charArray1);
                Log.i("playerOneStr After charArray1", playerOneStr);
                Log.i("charArray1", Arrays.toString(charArray1));

                int[] tempArrayConvert1 = new int[charArray1.length];
                for(int i = 0; i < charArray1.length; i++){
                    tempArrayConvert1[i] = Character.getNumericValue(charArray1[i]);
                }

                contains1 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo1).contains(n));
                contains2 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo2).contains(n));
                contains3 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo3).contains(n));
                contains4 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo4).contains(n));
                contains5 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo5).contains(n));
                contains6 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo6).contains(n));
                contains7 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo7).contains(n));
                contains8 = Arrays.stream(tempArrayConvert1).anyMatch(n -> Arrays.asList(combo8).contains(n));


                if(Arrays.equals(tempArrayConvert1, combo1) || Arrays.equals(tempArrayConvert1, combo2)
                        || Arrays.equals(tempArrayConvert1, combo3) || Arrays.equals(tempArrayConvert1, combo4) ||
                        Arrays.equals(tempArrayConvert1, combo5) || Arrays.equals(tempArrayConvert1, combo6) ||
                        Arrays.equals(tempArrayConvert1, combo7) || Arrays.equals(tempArrayConvert1, combo8) ||
                        contains1 || contains2 || contains3 || contains4 || contains5 || contains6 || contains7 || contains8) {
                    Toast.makeText(getActivity(), "Player One Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerOneWinTest", "Player 1 WON!");
                    playerOneScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                // PlayerTwoStr to CharArray2
                charArray2 = playerTwoStr.toCharArray();
                Arrays.sort(charArray2);
                Log.i("playerTwoStr After charArray2", playerTwoStr);
                Log.i("charArray2", Arrays.toString(charArray2));

                int[] tempArrayConvert2 = new int[charArray2.length];
                for(int i = 0; i < charArray2.length; i++){
                    tempArrayConvert2[i] = Character.getNumericValue(charArray2[i]);
                }

                if(Arrays.equals(tempArrayConvert2, combo1) || Arrays.equals(tempArrayConvert2, combo2)
                        || Arrays.equals(tempArrayConvert2, combo3) || Arrays.equals(tempArrayConvert2, combo4) ||
                        Arrays.equals(tempArrayConvert2, combo5) || Arrays.equals(tempArrayConvert2, combo6) ||
                        Arrays.equals(tempArrayConvert2, combo7) || Arrays.equals(tempArrayConvert2, combo8)) {
                    Toast.makeText(getActivity(), "Player Two Won!", Toast.LENGTH_SHORT).show();
                    Log.i("PlayerTwoWinTest", "Player 2 WON!");
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    playAgain();
                }


                Log.i("rountCount", " " + rountCount);
                Log.i("gameState", " " + gameState);
            }
        });



        // Reset Game Button
        binding.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;

                playerOneScore.setText(Integer.toString(playerOneScoreCount));
                playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
                playAgain();

            }
        });


    }





    public boolean checkWinner() {
        boolean winnerResult = false;

        for(int [] winningPostion : winningPositions) {
            if(gameState[winningPostion[0]] == gameState[winningPostion[1]] &&
                    gameState[winningPostion[1]] == gameState[winningPostion[2]] &&
                    gameState[winningPostion[0]] !=2) {
                winnerResult = true;
            }
        }
        return winnerResult;
    }



    public void playAgain() {
        rountCount = 0;
        activePlayer = true;

        charArray1 = null;
        charArray2 = null;
        playerOneStr = "";
        playerTwoStr = "";
        playerStatusTurn = 1;


        for(int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;

            btn1 = (Button) getView().findViewById(R.id.button1);
            btn1.setText(" ");

            btn2 = (Button) getView().findViewById(R.id.button2);
            btn2.setText(" ");

            btn3 = (Button) getView().findViewById(R.id.button3);
            btn3.setText(" ");

            btn4 = (Button) getView().findViewById(R.id.button4);
            btn4.setText(" ");

            btn5 = (Button) getView().findViewById(R.id.button5);
            btn5.setText(" ");

            btn6 = (Button) getView().findViewById(R.id.button6);
            btn6.setText(" ");

            btn7 = (Button) getView().findViewById(R.id.button7);
            btn7.setText(" ");

            btn8 = (Button) getView().findViewById(R.id.button8);
            btn8.setText(" ");

            btn9 = (Button) getView().findViewById(R.id.button9);
            btn9.setText(" ");

        }
    }


    public void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
