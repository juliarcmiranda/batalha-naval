package com.batalhanaval.view;

import com.batalhanaval.controller.CriarTabuleiro;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class ViewBoard {

  public ViewBoard() {

  }

  public void imprimirJogador() {
    System.out.println("---------------------------------------------");
    System.out.println("                   JOGADOR                   ");
    System.out.println("---------------------------------------------");
    System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
    System.out.println("---------------------------------------------");
  }

  public void imprimirCPU() {
    System.out.println("---------------------------------------------");
    System.out.println("                     CPU                     ");
    System.out.println("---------------------------------------------");
    System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
    System.out.println("---------------------------------------------");
  }

  public void entrada() {
    System.out.println("_____________________________________________");
    System.out.println("|                                           |");
    System.out.println("|               BATALHA NAVAL               |");
    System.out.println("|                                           |");
    System.out.println("|    Por: Ewerton Xavier e Julia Miranda    |");
    System.out.println("|                                           |");
    System.out.println("|___________________________________________|\n");
  }

}
