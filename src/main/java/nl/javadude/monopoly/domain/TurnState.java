/**
 * 
 */
package nl.javadude.monopoly.domain;

/**
 * StateMachine for the Player.
 */
public enum TurnState {
	START_TURN {
		@Override
		public TurnState transition(Player player) {
			if (Dice.getInstance().isSameEyes()) {
				return ROLLED_SAME_ONCE;
			}
			return END_TURN;
		}
	},
	ROLLED_SAME_ONCE {
		@Override
		public TurnState transition(Player player) {
			if (Dice.getInstance().isSameEyes()) {
				return ROLLED_SAME_TWICE;
			}
			return END_TURN;
		}
	},
	ROLLED_SAME_TWICE {
		@Override
		public TurnState transition(Player player) {
			if (Dice.getInstance().isSameEyes()) {
				return JAILED;
			}
			return END_TURN;
		}
	},
	JAILED {
		@Override
		public TurnState transition(Player player) {
			if (Dice.getInstance().isSameEyes()) {
				return ROLLED_SAME_ONCE;
			}
			return END_TURN;
		}
	},
	END_TURN {
		@Override
		public TurnState transition(Player player) {
			return END_TURN;
		}
	};
	
	public abstract TurnState transition(Player player);
}
