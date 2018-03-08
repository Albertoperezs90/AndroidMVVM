package com.example.alberto.androidmvvm.ViewModel;

import android.databinding.ObservableArrayMap;

import com.example.alberto.androidmvvm.Model.Cell;
import com.example.alberto.androidmvvm.Model.Game;
import com.example.alberto.androidmvvm.Model.Player;

import java.util.Observable;

import static com.example.alberto.androidmvvm.utilities.StringUtility.stringFromNumbers;

public class MainViewModel extends Observable {

    private static final String TAG = MainViewModel.class.getSimpleName();
    private static final String NO_WINNER  = "No hay ganador";
    public ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public Game game;

    public MainViewModel(String player1, String player2){
        game = new Game();
        beginGame(new Player(player1, "x"), new Player(player2, "o"));
    }

    public void beginGame(Player x, Player o) {
        game.player1 = x;
        game.player2 = o;
        game.currentPlayer = x;
    }

    public void onClickedCellAt(int row, int column){
        game.cells[row][column] = new Cell(game.currentPlayer);
        cells.put(stringFromNumbers(row, column), game.currentPlayer.value);
        if (game.hasGameEnded()){
            onGameHasEnded();
        }
        game.switchPlayer();
    }

    private void onGameHasEnded() {
        setChanged();
        notifyObservers(game.winner == null ? NO_WINNER : game.winner.name);
        game.reset();
    }

}