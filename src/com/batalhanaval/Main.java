package com.batalhanaval;

import com.batalhanaval.controller.CriarTabuleiro;
import org.jetbrains.annotations.NotNull;

import com.batalhanaval.view.ViewBoard;

public class Main {

  public static void main(String[] args) {
    CriarTabuleiro criarTabuleiro =  new CriarTabuleiro();
    ViewBoard viewBoard = new ViewBoard();
    viewBoard.entrada();
    criarTabuleiro.posicionarNaviosJogador();
    criarTabuleiro.posicionarNaviosCPU();
    criarTabuleiro.isPlayingClass();
  }
}

