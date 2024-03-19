package com.mygdx.game.Screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.gdxGame;

import java.awt.*;

public class MenuScreen implements Screen, InputProcessor {
    private Texture menuIcon = new Texture("tower-defense-game.png");
    private gdxGame game;

    public MenuScreen(gdxGame game){
        this.game = game;
    }
}
