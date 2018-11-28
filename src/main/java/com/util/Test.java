package com.util;

public class Test extends Thread implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("77777");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t =new Thread(new Test());
		t.start();
	}

}
