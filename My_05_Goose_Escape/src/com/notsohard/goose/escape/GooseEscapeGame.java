package com.notsohard.goose.escape;

import com.notsohard.framework.Screen;
import com.notsohard.framework.android.gl.GLGame;

public class GooseEscapeGame extends GLGame {
		public Screen getStartScreen(){
			return new CannonScreen(this);
		}
}
