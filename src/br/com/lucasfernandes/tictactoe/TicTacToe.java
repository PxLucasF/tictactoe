package br.com.lucasfernandes.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
  static ArrayList<Integer> playerPositions = new ArrayList<>();
  static ArrayList<Integer> cpuPositions = new ArrayList<>();

  public static void main(String[] args) {
    char[][] gameBoard = {
      {' ', '|', ' ', '|', ' '},
      {'-', '+', '-', '+', '-'},
      {' ', '|', ' ', '|', ' '},
      {'-', '+', '-', '+', '-'},
      {' ', '|', ' ', '|', ' '}
    };

    printGameBoard(gameBoard);

    while(true) {
      Scanner scan = new Scanner(System.in);
      System.out.println("Escolha uma posição entre 1 e 9");

      int playerPos = scan.nextInt();
      while(playerPos < 1 || playerPos > 9) {
        System.out.println("Posição inválida, escolha uma posição entre 1 e 9");
        playerPos = scan.nextInt();
      }

      while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
        System.out.println("Essa posição já foi preenchida, escolha outra");
        playerPos = scan.nextInt();
      }
      placePiece(gameBoard, playerPos, true);

      String result = checkWinner();
      if(result.length() > 0) {
        System.out.println(result);
        break;
      }

      Random rand = new Random();
      int cpuPos = rand.nextInt(9) + 1;
      while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
        cpuPos = rand.nextInt(9) + 1;
      }
      placePiece(gameBoard, cpuPos, false);

      result = checkWinner();
      if(result.length() > 0) {
        System.out.println(result);
        break;
      }

      printGameBoard(gameBoard);
    }
  }

  public static void printGameBoard(char[][] gameBoard) {
    for(char[] row : gameBoard) {
      for(char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  public static void placePiece(char[][] gameBoard, int pos, boolean isPlayer) {
    int row = 0, column = 0;
    char symbol = 'O';

    if(isPlayer) {
      symbol = 'X';
      playerPositions.add(pos);
    } else {
      cpuPositions.add(pos);
    }

    switch(pos) {
      case 1:
      case 2:
      case 3:
        row = 0;
        break;
      case 4:
      case 5:
      case 6:
        row = 2;
        break;
      case 7:
      case 8:
      case 9:
        row = 4;
        break;
    }

    switch (pos) {
      case 1:
      case 4:
      case 7:
        column = 0;
        break;
      case 2:
      case 5:
      case 8:
        column = 2;
        break;
      case 3:
      case 6:
      case 9:
        column = 4;
        break;
    }

    gameBoard[row][column] = symbol;
  }

  public static String checkWinner() {
    List<Integer> topRow   = Arrays.asList(1, 2, 3);
    List<Integer> midRow   = Arrays.asList(4, 5, 6);
    List<Integer> botRow   = Arrays.asList(7, 8, 9);

    List<Integer> leftCol  = Arrays.asList(1, 4, 7);
    List<Integer> midCol   = Arrays.asList(2, 5, 8);
    List<Integer> rightCol = Arrays.asList(3, 6, 9);

    List<Integer> cross1   = Arrays.asList(1, 5, 9);
    List<Integer> cross2   = Arrays.asList(7, 5, 3);

    List<List> winConditions = new ArrayList<List>();
    winConditions.add(topRow);
    winConditions.add(midRow);
    winConditions.add(botRow);
    winConditions.add(leftCol);
    winConditions.add(midCol);
    winConditions.add(rightCol);
    winConditions.add(cross1);
    winConditions.add(cross2);

    for(List l : winConditions) {
      if(playerPositions.containsAll(l)) {
        return "Parabéns, você venceu!";
      } else if (cpuPositions.containsAll(l)) {
        return "Você perdeu :(";
      } else if(playerPositions.size() + cpuPositions.size() == 9) {
        return "Vishe, deu velha!";
      }
    }

    return "";
  }
}
