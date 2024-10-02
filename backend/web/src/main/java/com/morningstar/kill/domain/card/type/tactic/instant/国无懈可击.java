package com.morningstar.kill.domain.card.type.tactic.instant;

import com.morningstar.kill.domain.card.type.tactic.InstantTacticType;

public class 国无懈可击 extends InstantTacticType {
    public 国无懈可击(){
        super(
                """
                        当一张锦囊牌对一名角色生效前，抵消此牌对该角色或该角色势力的所有角色的效果；或抵消另一张【无懈可击】的效果。
                        """
        );
    }
}
