//package com.ohand.ohandflow.fsm;
//
//public class Running {
//	  
//	  //
//	  public void run(StateOwner stateOwner){
//	     stateOwner.setCurrentState(this);
//	  }
//	  
//	  //转换到下一个状态的规则
//	  //当前是Running状态，下一个状态可能是暂停、结束或者强制退出等
//	  //状态，但是绝对不会是Not_Started这样的状态
//	  //转换规则在这里得到了体现。
//	  public State next(Event e) {
//	    if(transitions == null){
//	       addEventState(new EventImp("PAUSE"), new Suspended());
//	       addEventState(new EventImp("END"), new Completed());
//	       addEventState(new EventImp("STOP"), new Aborted());
//	    }
//	    return super.next(e);
//	  }
//	  
//}