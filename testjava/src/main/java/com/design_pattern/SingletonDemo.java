package com.design_pattern;

 class SingletonDemo {

    //creating instance
    private static SingletonDemo instance ;

    //creating private constructor
    private SingletonDemo() {
    }

    //getting instance from any where using class name.
    public synchronized static SingletonDemo getInstance() {
        if (instance == null)
            instance = new SingletonDemo();
        return instance;
    }

    public void showMessage(){
        System.out.println("Singleton Show Message");
    }
}

class CallSingleton{
    public static void main(String[] arg){

        //you cannot create the object but the the only object available
        SingletonDemo demo = SingletonDemo.getInstance();
        demo.showMessage();
    }
}
