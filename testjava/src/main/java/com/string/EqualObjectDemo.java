package com.string;

/**
 * Created by zafar.imam on 18-08-2017.
 */

public class EqualObjectDemo {

    private static EqualObjectDemo obj1, obj2,obj3;
    private String name;
    private int id;

    public EqualObjectDemo(String test,int id) {
        name = test;
        this.id = id;
    }

    public static void main(String arg[]) {

        obj1 = new EqualObjectDemo("Test",8);

        obj2 = new EqualObjectDemo("Test",8);

        obj3 = new EqualObjectDemo("Name",10);

        System.out.println("hash1 :" + obj1.hashCode() + "\nhash2 :" + obj2.hashCode()+"\nhash3 :"+obj3.hashCode());

        System.out.println("Equals :" + (obj1.equals(obj2)));
        System.out.println("Equals :" + (obj1.equals(obj3)));
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = false;
        if (obj == null || obj.getClass() != getClass()){
            result = false;
        }
        else {
            EqualObjectDemo objectDemo = (EqualObjectDemo) obj;
            if (this.name == objectDemo.name && this.id == objectDemo.id){
          //  if (this.name.equals(objectDemo.name )&& this.id == objectDemo.id){
                result = true;
            }
        }
        return  result;
    }

    /*
    if two objects are equal, that is obj1.equals(obj2) is true then,
     obj1.hashCode() and obj2.hashCode() must return same integer.
     */
    @Override
    public int hashCode() {
     //just to avoid null
      /*int hash = 5;
        hash = 7*hash +this.name.hashCode();*/
        return this.name.hashCode();
       // return super.hashCode();
    }


}
