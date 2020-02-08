package view;

import models.TileMod;

public class TilePresenter {
    private TileMod tileModel;
    private Tile tile;

    public TilePresenter(TileMod tileModel, Tile tile){
        this.tileModel = tileModel;
        this.tile = tile;
    }
}
