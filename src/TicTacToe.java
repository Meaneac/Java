import static java.lang.Character.toUpperCase;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToe {

  public static char[][] board = new char[3][3];
  public static Character player_char;
  public static int player_num;
  public static Character computer_char;
  static Scanner scnr = new Scanner(System.in);
  public static int move = 1;

  public static void main(String[] args) { game(); }

  public static void game() {
    loading();
    set_chars();
    move();
  }

  public static void clear(int lines) {
    String blank_line = "\n";
    System.out.print(blank_line.repeat(lines));
  }

  public static void loading() {

    clear(100);
    title();
    System.out.println("\u001B[34m");
    System.out.println("              Created by Kyle Schang");
    System.out.print("\u001B[37m");
    System.out.println();

    int loading_number = 0;

    System.out.print("                     \u001B[31mLoading");
    System.out.print("\u001B[37m\n           [");

    do {
      try {
        TimeUnit.MILLISECONDS.sleep(25);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.print("\u001B[31m#");
      ++loading_number;
    } while (loading_number < 25);

    System.out.print("\u001B[37m]");
    try {
      TimeUnit.MILLISECONDS.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println();
    System.out.println();

    clear(100);
  }

  public static void title() {
    System.out.print("\u001B[31m");
    System.out.println(
        "_____    ____   _____   _    ____   _____  ____  ___         ");
    System.out.println(
        "  |   |  |        |    /_\\   |        |    |  |  |__       ");
    System.out.println(
        "  |   |  |___     |   /   \\  |___     |    |__|  |__         ");
    System.out.println("\u001B[37m");
  }

  public static void starting_board() {
    title();
    System.out.println("\n");
    int spot = 1;
    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        if (col == 0) {
          System.out.print("                   ");
        }
        if (col != 2) {
          System.out.print(" "
                           + "\u001B[31m" + spot + "\u001B[37m"
                           + " |");
        }
        if (col == 2) {
          System.out.print(" "
                           + "\u001B[31m" + spot + "\u001B[37m");
        }
        if (row != 2 & col == 2) {
          System.out.println("\n                   -----------");
        }
        if (row == 2 & col == 2)
          System.out.println();
        ++spot;
      }
    }
  }

  public static Character pick_char() {
    System.out.println();
    System.out.println();
    try {
      System.out.println(
          "\u001B[34m          Do you want to be \u001B[31m'X'\u001B[34m or \u001B[31m'O'\u001B[37m");
      char choice = scnr.next().charAt(0);
      if (choice == 'X' || choice == 'x' || choice == 'O' || choice == 'o') {
        if (toUpperCase(choice) == 'X') {
          player_num = 1;
        } else {
          player_num = 2;
        }
        return choice;
      } else {
        throw new notCharException(choice);
      }
    } catch (notCharException e) {
      e.print();
      return pick_char();
    }
  }

  public static void set_chars() {

    starting_board();

    char player = toUpperCase(pick_char());
    player_char = player;

    if (player == 'X') {
      computer_char = 'O';
    } else {
      computer_char = 'X';
    }

    System.out.println("You are now \u001B[33m" + player_char + "\u001B[37m");

    board = new char[][] {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    display_board();
  }

  public static void display_board() {

    clear(100);
    title();
    System.out.println();

    System.out.println("\u001B[37m         Player is \u001B[33m" + player_char +
                       "\u001B[37m         Computer is \u001B[36m" +
                       computer_char + "\u001B[37m");

    System.out.println();

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        if (col == 0) {
          System.out.print("                    ");
        }
        if (board[row][col] == toUpperCase(player_char)) {
          System.out.print("\u001B[33m" + board[row][col] + "\u001B[37m");
        } else {
          System.out.print("\u001B[36m" + board[row][col] + "\u001B[37m");
        }
        if (col != 2) {
          System.out.print(" | ");
        }
        if (row != 2 & col == 2) {
          System.out.println("\n                   -----------");
        }
        if (row == 2 & col == 2) {
          System.out.println();
        }
      }
    }
  }

  public static void display_winning_board(Character winning_car) {

    clear(100);
    title();
    System.out.print("\u001B[34m");
    System.out.println("              Created by Kyle Schang");
    System.out.print("\u001B[37m");
    System.out.println();
    System.out.println();

    System.out.println("\u001B[37m         Player is \u001B[33m" + player_char +
                       "\u001B[37m         Computer is \u001B[36m" +
                       computer_char + "\u001B[37m");

    System.out.println();

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        if (col == 0) {
          System.out.print("                    ");
        }
        if (board[row][col] == toUpperCase(winning_car)) {
          System.out.print("\u001B[32m" + board[row][col] + "\u001B[37m");
        } else {
          System.out.print("\u001B[37m" + board[row][col] + "\u001B[37m");
        }
        if (col != 2) {
          System.out.print(" | ");
        }
        if (row != 2 & col == 2) {
          System.out.println("\n                   -----------");
        }
        if (row == 2 & col == 2) {
          System.out.println();
        }
      }
    }
  }

  public static void move() {

    if (move == 10) {
    }

    // player gets odd moves, player starts
    else if (player_num % 2 == move % 2) {
      choose_location();
      display_board();
    }
    // player gets even moves, computer starts
    else if (player_num % 2 != move % 2) {
      if (move == 1) {
        computer_random_move();
      } else if (!computer_win()) {
        if (!computer_block()) {
          computer_random_move();
        }
      }
      display_board();
    }

    if (check_for_win(player_char)) {
      player_won();
    } else if (check_for_win(computer_char)) {
      computer_won();
    } else if (move == 10) {
      tie();
    } else {
      move();
    }
  }

  public static void computer_random_move() {
    int row;
    int col;
    int first_index = (int)Math.floor(Math.random() * (9 - 1 + 1) + 1);

    if (first_index == 1) {
      row = 0;
      col = 0;
    } else if (first_index == 2) {
      row = 0;
      col = 1;
    } else if (first_index == 3) {
      row = 0;
      col = 2;
    } else if (first_index == 4) {
      row = 1;
      col = 0;
    } else if (first_index == 5) {
      row = 1;
      col = 1;
    } else if (first_index == 6) {
      row = 1;
      col = 2;
    } else if (first_index == 7) {
      row = 2;
      col = 0;
    } else if (first_index == 8) {
      row = 2;
      col = 1;
    } else {
      row = 2;
      col = 2;
    }

    if (check_location(row, col)) {
      board[row][col] = computer_char;
      ++move;
    } else {
      computer_random_move();
    }
  }

  public static boolean check_location(int row, int col) {

    return board[row][col] != 'X' && board[row][col] != 'O';
  }

  public static void choose_location() {

    System.out.println("\n\u001B[34m    Where would like to place the next " +
                       player_char + "? (1-9)\u001B[37m");
    int location = scnr.nextInt();

    int row;
    int col;

    if (location == 1) {
      row = 0;
      col = 0;
    } else if (location == 2) {
      row = 0;
      col = 1;
    } else if (location == 3) {
      row = 0;
      col = 2;
    } else if (location == 4) {
      row = 1;
      col = 0;
    } else if (location == 5) {
      row = 1;
      col = 1;
    } else if (location == 6) {
      row = 1;
      col = 2;
    } else if (location == 7) {
      row = 2;
      col = 0;
    } else if (location == 8) {
      row = 2;
      col = 1;
    } else {
      row = 2;
      col = 2;
    }

    if (check_location(row, col)) {
      board[row][col] = player_char;
      ++move;
    } else {
      System.out.println("There is already a move there. Play somewhere else.");
      choose_location();
    }
  }

  public static boolean check_for_win(char letter) {

    // Checking horizontal
    for (int row = 0; row < 3; ++row) {
      int count = 0;
      for (int col = 0; col < 3; ++col) {
        if (board[row][col] == letter) {
          ++count;
        }
      }
      if (count == 3) {
        return true;
      }
    }

    // Checking Vertical
    for (int row = 0; row < 3; ++row) {
      int count = 0;
      for (int col = 0; col < 3; ++col) {
        if (board[col][row] == letter) {
          ++count;
        }
      }
      if (count == 3) {
        return true;
      }
    }

    // Checking upper left diagonal
    int count = 0;
    for (int row = 0; row < 3; ++row) {
      if (board[row][row] == letter) {
        ++count;
      }
      if (count == 3) {
        return true;
      }
    }

    // Checking upper right diagonal
    count = 0;
    for (int row = 0; row < 3; ++row) {
      if (board[row][2 - row] == letter) {
        ++count;
      }
      if (count == 3) {
        return true;
      }
    }

    return false;
  }

  public static boolean computer_block() {

    // Checking horizontal
    for (int row = 0; row < 3; ++row) {
      int count = 0;
      for (int col = 0; col < 3; ++col) {
        if (board[row][col] == player_char) {
          ++count;
        }
      }
      if (count == 2) {
        for (int com_col = 0; com_col < 3; ++com_col) {
          if (board[row][com_col] == ' ') {
            board[row][com_col] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    // Checking Vertical
    for (int row = 0; row < 3; ++row) {
      int count = 0;
      for (int col = 0; col < 3; ++col) {
        if (board[col][row] == player_char) {
          ++count;
        }
      }
      if (count == 2) {
        for (int com_col = 0; com_col < 3; ++com_col) {
          if (board[com_col][row] == ' ') {
            board[com_col][row] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    // Checking upper left diagonal
    int count = 0;
    for (int row = 0; row < 3; ++row) {
      if (board[row][row] == player_char) {
        ++count;
      }
      if (count == 2) {
        for (int com_row = 0; com_row < 3; ++com_row) {
          if (board[com_row][com_row] == ' ') {
            board[com_row][com_row] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    // Checking upper right diagonal
    count = 0;
    for (int row = 0; row < 3; ++row) {
      if (board[row][2 - row] == player_char) {
        ++count;
      }
      if (count == 2) {
        for (int com_row = 0; com_row < 3; ++com_row) {
          if (board[com_row][2 - com_row] == ' ') {
            board[com_row][2 - com_row] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    return false;
  }

  public static boolean computer_win() {

    // Checking horizontal
    for (int row = 0; row < 3; ++row) {
      int count = 0;
      for (int col = 0; col < 3; ++col) {
        if (board[row][col] == computer_char) {
          ++count;
        }
      }
      if (count == 2) {
        for (int com_col = 0; com_col < 3; ++com_col) {
          if (board[row][com_col] == ' ') {
            board[row][com_col] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    // Checking Vertical
    for (int row = 0; row < 3; ++row) {
      int count = 0;
      for (int col = 0; col < 3; ++col) {
        if (board[col][row] == computer_char) {
          ++count;
        }
      }
      if (count == 2) {
        for (int com_col = 0; com_col < 3; ++com_col) {
          if (board[com_col][row] == ' ') {
            board[com_col][row] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    // Checking upper left diagonal
    int count = 0;
    for (int row = 0; row < 3; ++row) {
      if (board[row][row] == computer_char) {
        ++count;
      }
      if (count == 2) {
        for (int com_row = 0; com_row < 3; ++com_row) {
          if (board[com_row][com_row] == ' ') {
            board[com_row][com_row] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    // Checking upper right diagonal
    count = 0;
    for (int row = 0; row < 3; ++row) {
      if (board[row][2 - row] == computer_char) {
        ++count;
      }
      if (count == 2) {
        for (int com_row = 0; com_row < 3; ++com_row) {
          if (board[com_row][2 - com_row] == ' ') {
            board[com_row][2 - com_row] = computer_char;
            ++move;
            return true;
          }
        }
      }
    }

    return false;
  }

  public static void player_won() {

    if (player_char == 'X') {
      display_winning_board('X');
    } else {
      display_winning_board('O');
    }
    System.out.println("\u001B[33m");
    System.out.println("              _               _       _       ");
    System.out.println("        \\_/  | |  |  |    \\  / \\  /  | |  |\\ |");
    System.out.println("         |   |_|  |__|     \\/   \\/   |_|  | \\|");
    System.out.print("\u001B[37m");
    play_again();
  }

  public static void computer_won() {
    if (computer_char == 'X') {
      display_winning_board('X');
    } else {
      display_winning_board('O');
    }
    System.out.println("\u001B[36m");
    System.out.println("              _                  _    __   ___ ");
    System.out.println("        \\_/  | |  |  |    |     | |   |_    |  ");
    System.out.println("         |   |_|  |__|    |__   |_|   __|   |  ");
    System.out.print("\u001B[37m");
    play_again();
  }

  public static void tie() {
    display_board();
    System.out.println("\u001B[35m");
    System.out.println("                  _____    ___");
    System.out.println("                    |   |  |__");
    System.out.println("                    |   |  |__");
    System.out.print("\u001B[37m");
    play_again();
  }

  public static void thanks_for_playing() {
    System.out.println();
    System.out.println("\u001B[31m");
    System.out.println(
        "_____        _            ___   ___  __   _     _        _                ____         ");
    System.out.println(
        "  |   |__|  /_\\  |\\ | |/  |__   |__ |  | |_|   |_| |    /_\\   \\_/  | |\\ | | __       ");
    System.out.println(
        "  |   |  | /   \\ | \\| |\\  __|   |   |__| | \\   |   |__ /   \\   |   | | \\| |__|         ");
  }

  public static void play_again() {
    System.out.println();
    System.out.println();
    try {
      System.out.println(
          "\u001B[34m       Do you want to play again (y or n)?\u001B[37m");
      char choice = scnr.next().charAt(0);
      if (choice == 'N' || choice == 'n' || choice == 'Y' || choice == 'y') {
        if (toUpperCase(choice) == 'Y') {
          board =
              new char[][] {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
          move = 1;
          game();
        } else {
          thanks_for_playing();
        }
      } else {
        throw new notCharException(choice);
      }
    } catch (notCharException e) {
      e.print_2();
      play_again();
    }
  }

  static class notCharException extends Exception {

    Character choice;

    notCharException(Character choice) { this.choice = choice; }

    void print() {
      System.out.println(
          choice +
          " is not a choice. Please pick only \u001B[31m'X'\u001B[37m or \u001B[31m'O'\u001B[37m.");
    }

    void print_2() {
      System.out.println(
          choice +
          " is not a choice. Please pick only \u001B[31m'Y'\u001B[37m or \u001B[31m'N'\u001B[37m.");
    }
  }
}