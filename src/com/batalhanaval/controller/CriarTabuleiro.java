package com.batalhanaval.controller;

import com.batalhanaval.domain.Tabuleiro;
import com.batalhanaval.view.ViewBoard;
import org.jetbrains.annotations.NotNull;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CriarTabuleiro {
  private String[][] tabuleiroJogador;
  private String[][] tabuleiroCPU;
  private Tabuleiro jogador;
  private Tabuleiro cpu;
  private String NUMEROS = "0123456789";
  String alphabet = "ABCDEFGHIJ";
  ViewBoard textosDaView = new ViewBoard();

  public CriarTabuleiro() {
    this.jogador = new Tabuleiro(0, 0, tabuleiroJogador);
    this.cpu = new Tabuleiro(0, 0, tabuleiroCPU);
  }

  public void posicionarNaviosCPU() {
//  Usado para permitir o posicionamento do navio no tabuleiro
//  Definição da matriz tabuleiro
    Random rand = new Random();
    int positionX;
    int positionY;
    int cont = 0;

    this.tabuleiroCPU = criarTabuleiro();
    cpu.setTabuleiro(this.tabuleiroCPU);
//    Posicionamento dos navios no tabuleiro da CPU
    while (cont < cpu.getTabuleiro().length) {
      positionX = rand.nextInt(cpu.getTabuleiro().length);
      positionY = rand.nextInt(cpu.getTabuleiro().length);
      System.out.printf("O navio ficará posicionado em: %d %d%n", positionX, positionY);

      while (!cpu.getTabuleiro()[positionX][positionY].equals("_")) {
        positionX = rand.nextInt(cpu.getTabuleiro().length);
        positionY = rand.nextInt(cpu.getTabuleiro().length);
        System.out.printf("O navio ficará posicionado, após revisão, em: %d %d%n", positionX, positionY);
      }
      cpu.setValueAtIndex(positionX, positionY, "C");
      cont++;

    }
    //    Imprime o tabuleiro na tela
    textosDaView.imprimirCPU();
    imprimeTabuleiro(cpu.getTabuleiro());
  }

  public void posicionarNaviosJogador() {
    Scanner input = new Scanner(System.in);
    String linha;
    int coluna;
    this.tabuleiroJogador = criarTabuleiro();
    jogador.setTabuleiro(this.tabuleiroJogador);

//   Posiciona navios de acordo com o tamanho da matriz
    for (int i = 0; i < jogador.getTabuleiro().length; i++) {
      linha = linhaTabuleiro(input, alphabet);
      int index = alphabet.toLowerCase().indexOf(linha.toLowerCase());
      coluna = colunaTabuleiro(input);

      while (jogador.getTabuleiro()[index][coluna].equals("N")) {
        linha = linhaTabuleiro(input, alphabet);
        index = alphabet.toLowerCase().indexOf(linha.toLowerCase());
        coluna = colunaTabuleiro(input);
      }
      if (coluna < jogador.getTabuleiro().length) {
        if (jogador.getTabuleiro()[index][coluna].equals("_")) {
          jogador.setValueAtIndex(index, coluna, "N");
          textosDaView.imprimirJogador();
          imprimeTabuleiro(jogador.getTabuleiro());
        }
      }
    }
  }

  public String[] @NotNull [] criarTabuleiro() {
    String[][] tabuleiro = new String[10][10];
    for (String[] strings : tabuleiro) {
      Arrays.fill(strings, "_");
    }
    textosDaView.imprimirJogador();
    imprimeTabuleiro(tabuleiro);
    return tabuleiro;
  }


  public String linhaTabuleiro(@NotNull Scanner input, String alphabet) {
    //  Linha
    String linha;
    System.out.print("Digite o valor da linha [a-j] ou [A-J]: ");
    linha = input.nextLine();
    // Validação da linha
    while (linha.length() != 1) {
      System.out.print("Digite uma linha válida [a-j] ou [A-J]: ");
      linha = input.nextLine();
    }
    int index = alphabet.indexOf(linha.toUpperCase());
    while (index == -1) {
      System.out.print("Repita a linha [a-j] ou [A-J]: ");
      linha = input.nextLine();
      index = alphabet.toLowerCase().indexOf(linha.toLowerCase());
    }

    return linha;
  }

  public int colunaTabuleiro(@NotNull Scanner input) {
    String coluna;
    int index;

    System.out.print("Digite o valor da coluna [0-9]: ");
    coluna = input.nextLine();

    // Confirma que o tamanho da 'String' é 1
    while (coluna.length() != 1) {
      System.out.print("Digite uma coluna válida [0-9]: ");
      coluna = input.nextLine();
    }
    index = NUMEROS.indexOf(coluna);

    while (index == -1) {
      System.out.print("Repita a coluna [0-9]: ");
      coluna = input.nextLine();
      index = NUMEROS.indexOf(coluna);
    }

    return index;
  }


  public void isPlayingClass() {
    boolean isPlaying = true;
    while (isPlaying) {
//  Verifica se a diferença de jogadas entre o jogador e a CPU é 1
      if ((jogador.getContadorJogadas() - cpu.getContadorJogadas()) == 1) {
        vezCPU();
        cpu.setContadorJogadas(cpu.getContadorJogadas() + 1);
        System.out.printf("Acertos - CPU: %d%n", cpu.getAcertos());
      } else {
        vezJogador();
        System.out.printf("Acertos - Jogador: %d%n", jogador.getAcertos());
        jogador.setContadorJogadas(jogador.getContadorJogadas() + 1);

      }
      if (jogador.getAcertos() == jogador.getTabuleiro().length) {
        System.out.println("Parabéns,você ganhou!!!!!");
        textosDaView.imprimirJogador();
        imprimeTabuleiro(jogador.getTabuleiro());
        textosDaView.imprimirCPU();
        imprimeTabuleiro(cpu.getTabuleiro());
        isPlaying = false;
      } else if (cpu.getAcertos() == cpu.getTabuleiro().length) {
        System.out.println("A CPU venceu o jogo!");
        textosDaView.imprimirCPU();
        imprimeTabuleiro(cpu.getTabuleiro());
        textosDaView.imprimirJogador();
        imprimeTabuleiro(jogador.getTabuleiro());
        isPlaying = false;
      }
    }
  }


  public void vezJogador() {
//    . Navio posicionado N (ene maiúsculo)
//    . Tiro certeiro * (asterisco)
//    . Tiro na água – (traço)
//    . Tiro certeiro com navio posicionado X (xis maiúsculo)
//    . Tiro na água com navio posicionado n (ene minúsculo)

    Scanner input = new Scanner(System.in);
    String linha;
    int coluna;

    linha = linhaTabuleiro(input, alphabet);
    int index = alphabet.toLowerCase().indexOf(linha.toLowerCase());
    coluna = colunaTabuleiro(input);

    while (!jogador.getTabuleiro()[index][coluna].equals("_") && !jogador.getTabuleiro()[index][coluna].equals("N")) {
      linha = linhaTabuleiro(input, alphabet);
      index = alphabet.toLowerCase().indexOf(linha.toLowerCase());
      coluna = colunaTabuleiro(input);
    }

    if ((cpu.getTabuleiro()[index][coluna].equals("C") || cpu.getTabuleiro()[index][coluna].equals("c") || cpu.getTabuleiro()[index][coluna].equals("X")) && !(jogador.getTabuleiro()[index][coluna].equals("N") || jogador.getTabuleiro()[index][coluna].equals("n"))) {
      jogador.setValueAtIndex(index, coluna, "*");
      jogador.setAcertos(jogador.getAcertos() + 1);
      cpu.setValueAtIndex(index, coluna, "O");
    } else if ((cpu.getTabuleiro()[index][coluna].equals("C") || cpu.getTabuleiro()[index][coluna].equals("c")) && (jogador.getTabuleiro()[index][coluna].equals("N") || jogador.getTabuleiro()[index][coluna].equals("n"))) {
      jogador.setValueAtIndex(index, coluna, "X");
      jogador.setAcertos(jogador.getAcertos() + 1);
      cpu.setValueAtIndex(index, coluna, "O");
    } else if (!(cpu.getTabuleiro()[index][coluna].equals("C") || cpu.getTabuleiro()[index][coluna].equals("c") || cpu.getTabuleiro()[index][coluna].equals("X")) && (jogador.getTabuleiro()[index][coluna].equals("N") || jogador.getTabuleiro()[index][coluna].equals("n"))) {
      jogador.setValueAtIndex(index, coluna, "n");
    } else if (!(cpu.getTabuleiro()[index][coluna].equals("C") || cpu.getTabuleiro()[index][coluna].equals("c") || cpu.getTabuleiro()[index][coluna].equals("X")) && !(jogador.getTabuleiro()[index][coluna].equals("N") || jogador.getTabuleiro()[index][coluna].equals("n"))) {
      jogador.setValueAtIndex(index, coluna, "-");
    } else {
      vezJogador(); // Recursão
      System.out.println("Repetir a jogada!");
    }
    textosDaView.imprimirJogador();
    imprimeTabuleiro(jogador.getTabuleiro());

  }

  public void vezCPU() {
    Random rand = new Random();
    int positionX;
    int positionY;
    positionX = rand.nextInt(jogador.getTabuleiro().length);
    positionY = rand.nextInt(jogador.getTabuleiro().length);


    // Verifica se já existe alguma tentativa na posicao
    while (!cpu.getTabuleiro()[positionX][positionY].equals("_") && !cpu.getTabuleiro()[positionX][positionY].equals("C")) {
      positionX = rand.nextInt(jogador.getTabuleiro().length);
      positionY = rand.nextInt(jogador.getTabuleiro().length);
    }

    System.out.printf("CPU coordinates: [%d][%d]%n", positionX, positionY);

    if ((jogador.getTabuleiro()[positionX][positionY].equals("N") || jogador.getTabuleiro()[positionX][positionY].equals("n") || jogador.getTabuleiro()[positionX][positionY].equals("X")) && !cpu.getTabuleiro()[positionX][positionY].equals("C")) {
      cpu.setValueAtIndex(positionX, positionY, "*");
      cpu.setAcertos(cpu.getAcertos() + 1);
      jogador.setValueAtIndex(positionX, positionY, "O");
    } else if ((jogador.getTabuleiro()[positionX][positionY].equals("N") || jogador.getTabuleiro()[positionX][positionY].equals("n")) && (cpu.getTabuleiro()[positionX][positionY].equals("C") || cpu.getTabuleiro()[positionX][positionY].equals("c"))) {
      cpu.setValueAtIndex(positionX, positionY, "X");
      cpu.setAcertos(cpu.getAcertos() + 1);
      jogador.setValueAtIndex(positionX, positionY, "O");
    } else if (!(jogador.getTabuleiro()[positionX][positionY].equals("N") || jogador.getTabuleiro()[positionX][positionY].equals("n") || cpu.getTabuleiro()[positionX][positionY].equals("X")) && (cpu.getTabuleiro()[positionX][positionY].equals("C") || cpu.getTabuleiro()[positionX][positionY].equals("c"))) {
      cpu.setValueAtIndex(positionX, positionY, "c");
    } else if (!(jogador.getTabuleiro()[positionX][positionY].equals("N") || jogador.getTabuleiro()[positionX][positionY].equals("n") || cpu.getTabuleiro()[positionX][positionY].equals("X")) && !(cpu.getTabuleiro()[positionX][positionY].equals("C") || cpu.getTabuleiro()[positionX][positionY].equals("c"))) {
      cpu.setValueAtIndex(positionX, positionY, "-");
    } else {
      // Repetir a jogada;
      vezCPU();
      System.out.println("Repetir a jogada!");
    }
//    System.out.println("\nAqui vai o tabuleiro: \n");

//    textosDaView.imprimirCPU();
//    imprimeTabuleiro(cpu.getTabuleiro());
  }

  public void imprimeTabuleiro(String[] @NotNull [] board) {
    //    Impressão do tabuleiro formatado
    int indiceLetras = 0;
    for (int i = 0; i < board[0].length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (j == 0) {
          System.out.printf("| %s |", alphabet.charAt(indiceLetras));
          indiceLetras++;
        }
        if (j == (board.length - 1)) {
          System.out.printf(" %s |%n", board[i][j]);
          System.out.println("---------------------------------------------");
        } else {
          System.out.printf(" %s |", board[i][j]);
        }
      }
    }
  }


}
