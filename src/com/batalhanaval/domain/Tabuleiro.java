package com.batalhanaval.domain;

import org.jetbrains.annotations.NotNull;

public class Tabuleiro {
  private int contadorJogadas;
  private int acertos;
  private String[][] tabuleiro;

  public Tabuleiro(int contadorJogadas, int acertos, String[][] tabuleiro) {
    this.contadorJogadas = contadorJogadas;
    this.acertos = acertos;
    this.tabuleiro = tabuleiro;
  }

  public int getContadorJogadas() {
    return contadorJogadas;
  }

  public void setContadorJogadas(int contadorJogadas) {
    this.contadorJogadas = contadorJogadas;
  }

  public int getAcertos() {
    return acertos;
  }

  public void setAcertos(int acertos) {
    this.acertos = acertos;
  }

  public String[][] getTabuleiro() {
    return tabuleiro;
  }

  public void setTabuleiro(String[] @NotNull [] tabuleiro) {
    this.tabuleiro = tabuleiro;
  }

  public void setValueAtIndex(int linha, int coluna, String value) {
    this.tabuleiro[linha][coluna] = value;
  }

}
