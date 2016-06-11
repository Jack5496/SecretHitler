package com.secrethitler.Inputs;

import com.badlogic.gdx.Input.TextInputListener;
import com.jack5496.secrethitler.Main;

public class InputListener2 implements TextInputListener {
	
	
   @Override
   public void input(String text) {
	   Main.log(getClass(), "input: "+text);
   }

   @Override
   public void canceled () {
	   
   }
}