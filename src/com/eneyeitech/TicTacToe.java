package com.eneyeitech;

import java.util.Scanner;

public class TicTacToe {
    public static String [][] grid;
    public static int xCounts;
    public static int oCounts;
    public static int _Counts;
    public static Scanner scanner;
    public static boolean winner = false;
    public static String nextPlayer = "X";
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        grid = new String[3][3];

        //boolean pass = showGrid();
        initializeGame();
        visualiseGrid();
        String msg = "";
        do{
            enterCoordinate();
            msg = checkWinner();

        }while (!winner);
        System.out.println(msg);

    }
    public static void enterCoordinate(){
        boolean passed = false;
        do {
            System.out.print("Enter the coordinates: ");
            int c1 = 0;
            int c2 = 0;
            boolean t = true;
            try {
                c1 = Integer.parseInt(scanner.next());
                c2 = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                t = false;
            }

            if (t) {

                if(c1 < 1 || c1 > 3 || c2 < 1 || c2 > 3){
                    System.out.println("Coordinates should be from 1 to 3!");
                    passed = false;
                }else{
                    if(!grid[c1 - 1][c2 - 1].equals("_")){
                        System.out.println("This cell is occupied! Choose another one!");
                        passed = false;
                    }else{
                        grid[c1 - 1][c2 - 1] = nextPlayer;
                        if("X".equals(nextPlayer)){
                            nextPlayer = "O";
                        }else if("O".equals(nextPlayer)){
                            nextPlayer = "X";
                        }
                        passed = true;
                    }
                }

            }else{
                passed = false;
                System.out.println("You should enter numbers!");
            }
        }while(!passed);
        visualiseGrid();
    }

    public static void showResult(boolean p){
        if(p == true){
            //System.out.println("Analyzing");
            countCharacters();
            if (checkImpossibleState()) {
                System.out.println(checkWinner());
            } else {
                System.out.println("Impossible");
            }
        }
    }

    public static boolean showGrid(){
        int inputLength;
        boolean pass = false;
        do{
            System.out.print("Enter cells: ");
            String input = scanner.nextLine();
            inputLength = input.length();

            if (verifyLength(inputLength)) {
                if(verifyInput(input)) {
                    int count = 0;
                    System.out.println("---------");
                    for (int i = 0; i < 9; i++) {
                        String c = Character.toString(input.charAt(i));
                        count++;
                        if (i % 3 == 0) {
                            System.out.print("|");
                        }
                        System.out.print(" " + c);
                        if (count == 3) {
                            System.out.println(" |");
                            count = 0;
                        }
                    }
                    System.out.println("---------");
                    createGrid(input);
                    //System.out.println(grid);
                }else{
                    System.out.println("Grid contains invalid characters (VALID CHARACTERS: X _ O)");
                }

                pass = true;
            }else{
                pass = false;
                System.out.println("Incomplete Character, 9 required!");
            }

        }while (!pass);
        return pass;
    }


    public static boolean verifyLength(int len){
        boolean passed = false;
        if(len == 9){
            passed = true;
        }
        return passed;
    }

    public static boolean verifyInput(String input){
        boolean passed = true;
        for(int i = 0; i < 9; i++) {
            String c = Character.toString(input.charAt(i));
            if ("X".equals(c) || "_".equals(c) || "O".equals(c)) {
                passed = true;
                continue;
            } else {
                passed = false;
                break;
            }
        }
        return passed;
    }

    public static void createGrid(String string){
        int count = 0;
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                String c = Character.toString(string.charAt(count++));
                grid[i][j] = c;
            }
        }
    }

    public static void initializeGame(){
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {

                grid[i][j] = "_";
            }
        }
    }

    public static void visualiseGrid(){
        int count = 0;
        int k = 0;
        System.out.println("---------");
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                count++;
                if (k % 3 == 0) {
                    System.out.print("|");

                }
                System.out.print(" " + grid[i][j]);
                if (count == 3) {
                    System.out.println(" |");
                    count = 0;
                }
                k++;
            }
        }
        System.out.println("---------");
    }

    public static boolean checkDraw(){
       return true;
    }

    public static void countCharacters(){
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ("X".equals(grid[i][j])) {
                    xCounts++;
                }
                if ("_".equals(grid[i][j])) {
                    _Counts++;
                }
                if ("O".equals(grid[i][j])) {
                    oCounts++;
                }
            }
        }
    }

    public static boolean checkImpossibleState(){
        boolean passed;
        int diff = xCounts - oCounts;
        diff = Math.abs(diff);
        if (diff >= 2) {
            passed = false;
        } else {
            passed = true;
        }
        return passed;
    }

    public static String checkWinner(){
        String msg = "";
        boolean winnerFound = false;
        int noOfWinners = 0;
        if (grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2]) && !grid[0][0].equals("_")) {
            msg += grid[0][0]+" wins";
            winnerFound = true;
            noOfWinners++;
        } if (grid[0][0].equals(grid[0][1]) && grid[0][0].equals(grid[0][2]) && !grid[0][0].equals("_")) {
            msg += grid[0][0]+" wins";
            winnerFound = true;
            noOfWinners++;
        } if (grid[0][0].equals(grid[1][0]) && grid[0][0].equals(grid[2][0]) && !grid[0][0].equals("_")) {
            msg += grid[0][0]+" wins";
            winnerFound = true;
            noOfWinners++;
        } if (grid[0][1].equals(grid[1][1]) && grid[0][1].equals(grid[2][1]) && !grid[0][1].equals("_")) {
            msg += grid[0][1]+" wins";
            winnerFound = true;
            noOfWinners++;
        } if (grid[0][2].equals(grid[1][2]) && grid[0][2].equals(grid[2][2]) && !grid[0][2].equals("_")) {
            msg += grid[0][2]+" wins";
            winnerFound = true;
            noOfWinners++;
        } if (grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0]) && !grid[0][2].equals("_")) {
            msg += grid[0][2]+" wins";
            winnerFound = true;
            noOfWinners++;
        } if (grid[1][0].equals(grid[1][1]) && grid[1][0].equals(grid[1][2]) && !grid[1][0].equals("_")) {
            msg += grid[1][0]+" wins";
            winnerFound = true;
            noOfWinners++;
        } if (grid[2][0].equals(grid[2][1]) && grid[2][0].equals(grid[2][2]) && !grid[2][0].equals("_")) {
            msg += grid[2][0]+" wins";
            winnerFound = true;
            noOfWinners++;
        }
        if (!winnerFound) {
            if (_Counts == 0) {
                msg += "Draw";
            }
            if (_Counts > 0) {
                msg += "Game not finished";
            }
        }else{
            winner = true;
        }

        if(noOfWinners > 1){
            msg = "Impossible";
            winner = false;
        }

        return msg;
    }
}
