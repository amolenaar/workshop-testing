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
			return TURN_ACTION;
		}
	},
	ROLLED_SAME_ONCE {
		@Override
		public TurnState transition(Player player) {
			if (Dice.getInstance().isSameEyes()) {
				return ROLLED_SAME_TWICE;
			}
			return TURN_ACTION;
		}
	},
	ROLLED_SAME_TWICE {
		@Override
		public TurnState transition(Player player) {
			if (Dice.getInstance().isSameEyes()) {
				return JAILED;
			}
			return TURN_ACTION;
		}
	},
	// perform actions like taking cards and buying realty
	TURN_ACTION {
		@Override
		public TurnState transition(Player player) {
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
			return START_TURN;
		}
	};
	
	public abstract TurnState transition(Player player);
}
