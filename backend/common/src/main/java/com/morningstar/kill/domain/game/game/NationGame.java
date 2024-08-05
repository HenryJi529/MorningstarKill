package com.morningstar.kill.domain.game.game;

import com.morningstar.kill.domain.deck.Deck;
import com.morningstar.kill.domain.deck.DeckPool;
import com.morningstar.kill.domain.deck.deck.ConquestDeck;
import com.morningstar.kill.domain.game.Game;
import com.morningstar.kill.domain.game.Mode;
import com.morningstar.kill.domain.lobby.Table;

/**
 * 国战
 */
public class NationGame extends Game {
    private NationGame(Table table, Mode mode){
        super(table, mode);
    }

}
