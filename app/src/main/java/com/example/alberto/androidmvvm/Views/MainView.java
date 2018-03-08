package com.example.alberto.androidmvvm.Views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alberto.androidmvvm.R;
import com.example.alberto.androidmvvm.ViewModel.MainViewModel;
import com.example.alberto.androidmvvm.databinding.ActivityMainViewBinding;

import java.util.Observable;
import java.util.Observer;

public class MainView extends AppCompatActivity implements Observer {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";
    private ActivityMainViewBinding activityGameBinding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayersSet(String player1, String player2){
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_view);
        viewModel = new MainViewModel(player1, player2);
        activityGameBinding.setMainViewModel(viewModel);
        viewModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEndDialog dialog = GameEndDialog.newInstance(this, (String) arg);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }
}
