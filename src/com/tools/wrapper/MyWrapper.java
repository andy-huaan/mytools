package com.tools.wrapper;

import java.util.Date;

import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

/**
 * Java Service Wrapper
 * @author Administrator
 *
 */
public class MyWrapper implements WrapperListener{

	@Override
	public void controlEvent(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer start(String[] arg0) {
		for (int i = 0; i < arg0.length; i++) {
			System.out.println("传入参数:"+arg0[i]);
		}
		for (int i = 0; i < 50; i++) {
			System.out.println("目前:"+new Date().getTime());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int stop(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println("1-------------------------------1");
		//30秒内必须启动成功，如果启动不成功则报错，因为对于需要长时间启动的项目，可以单开一个线程
		WrapperManager.start(new MyWrapper(), args);
	}

}
