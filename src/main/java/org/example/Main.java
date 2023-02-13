package org.example;


import java.io.PrintStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.*;
class TicTacToe
{
    static PrintStream l=new PrintStream((new FileOutputStream(FileDescriptor.out)));
    static char[][]board=new char[3][3];
    TicTacToe(){
        initBoard();
    }
    static void initBoard()
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board.length;j++)
            {
                board[i][j]=' ';
            }
        }
    }
    void printBoard()
    {
        l.println("-----------");
        for(int i=0;i<board.length;i++)
        {
            l.print("|");
            for(int j=0;j<board[i].length;j++)
            {
                l.print(board[i][j]+ " | ");
            }
            l.println();
            l.println("----------");
        }
    }
    static void placeMark(int row,int col,char mark) {
        board[row][col]=mark;
    }
    static boolean checkRow()
    {
        for(int i=0;i<3;i++) {
            if(board[i][0]!=' ' && board[i][0] == board[i][1] && board[i][1]== board[i][2]) {
                return true;
            }
        }
        return false;
    }
    static boolean checkColumn()
    {
        for(int j=0;j<3;j++)
        {
            if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
            {
                return true;
            }
        }
        return false;
    }
    static boolean checkDiagonal() {
        return board [0][0]!=' ' && board[0][0] ==  board[1][1] && board[1][1] ==  board[2][2]  || board[0][2]!=' ' && board[0][2] == board[1][1] && board[1][1]==board[2][0];

    }
}
class HumanPlayer
{
    String name;
    char mark;
    HumanPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }

    void makeMove()
    {
        Scanner sc=new Scanner(System.in);
        int row;
        int col;
        do
        {
            TicTacToe.l.println("Enter row and col:");
            row=sc.nextInt();
            col=sc.nextInt();
        }while(!validMove(row,col));
        TicTacToe.placeMark(row,col,mark);
    }
    boolean validMove(int row,int col)
    {
        TicTacToe.l.println("Wrong Input ");

        return row>=0 && row<=2 && col>=0 && col<=2 && TicTacToe.board[row][col]==' ';

    }
}
public class Main
{
    public static void main( String[] args )
    {
        TicTacToe tic=new TicTacToe();


        int count=0;
        tic.printBoard();
        String name="Player 1";
        HumanPlayer player1=new HumanPlayer(name,'X');
        String name1="Player 2";
        HumanPlayer player2=new HumanPlayer(name1,'O');
        HumanPlayer cp;
        cp = player1;

        while(count<=8) {
            TicTacToe.l.println(cp.name + " Turn");
            cp.makeMove();
            count++;
            tic.printBoard();
            if(TicTacToe.checkRow() || TicTacToe.checkColumn() || TicTacToe.checkDiagonal() )
            {
                TicTacToe.l.println(cp.name +" Win");
                break;
            }
            else
            {
                if(cp==player1)
                {
                    cp=player2;
                }
                else
                {
                    cp=player1;
                }
            }
        }
        if(count==9) {
            TicTacToe.l.println("Draw !");
        }
    }


}