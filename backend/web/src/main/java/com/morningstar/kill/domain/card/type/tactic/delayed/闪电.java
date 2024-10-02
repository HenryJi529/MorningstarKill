package com.morningstar.kill.domain.card.type.tactic.delayed;

import com.morningstar.kill.domain.card.type.tactic.DelayedTacticType;

/**
 * 使用时机：出牌阶段。
 * 使用目标：你。
 * 作用效果：目标对应的角色判定。若结果为黑桃2-9，其受到3点无来源的雷电伤害。
 * ◆在【闪电】因判定阶段而进行的特殊的使用流程结束之前，若目标对应的角色于此次使用流程中执行此【闪电】的效果判定的结果不为黑桃2~9，或此【闪电】因被抵消而未执行效果，须将默认插入的系统将此【闪电】对应的实体牌置入弃牌堆的移动事件中移动的目标区域改为其下家的判定区，若其下家不是此【闪电】的合法目标，将目标区域改为其下家的下家的判定区，以此类推。但若所有角色均不是此【闪电】的合法目标或于此次使用流程中执行此【闪电】的效果判定的结果为黑桃2-9，则目标区域不会改变，依然为弃牌堆。
 */
public class 闪电 extends DelayedTacticType {
    public 闪电(){
        super(
                """
                        出牌阶段，对你使用。将【闪电】置入你的判定区。若判定结果为黑桃2-9，则目标角色受到3点雷电伤害，否则将之置入其下家的判定区。
                        """
        );
    }
}
