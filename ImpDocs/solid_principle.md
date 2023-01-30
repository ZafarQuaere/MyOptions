# S.O.L.I.D Principle in Android

We must all have heard about the <b>S.O.L.I.D</b> principle when developing software tools. I wanted to highlight the basics of the principle and provide some examples on how this would be applicable to Android development.

So let's begin by stating the 5 principles of <b>S.O.L.I.D</b>.

* <b>The Single Responsibility Principle (SRP)</b>: </br>
   * This principle states that <i>"A class should have only one reason to change"</i>
   * This means that one class should only have one responsibility.
   * For instance: let's take the ```OnBindViewHolder``` method in ```RecyclerView.Adapter``` class. The role of the ```OnBindViewHolder``` is to map an list item to a view. There should be no logic in this method.
      * Example that demonstrates how this principle is violated:
     ```
      @Override 
       public void onBindViewHolder(ViewHolder holder, int position) {
           Movie movie = movies.get(position);
           holder.title.setText(movie.getTitle());  
           holder.rating.setText(movie.getRating());
           //SRP violation, onBindViewHolder has only the responsibility to display data
           // no make operations like concatenations!
           String[] genres = song.getGenres();
           StringBuilder builder = new StringBuilder();
           foreach (String genre : genres){
             builder.append(genre).append(",");
           }
           holder.genres.setText(builder.toString());
       }
     ```

      * Example that demonstrates how this principle is used:

     ```
     @Override 
     public void onBindViewHolder(ViewHolder holder, int position) {
         Movie movie = movies.get(position);
         holder.title.setText(movie.getTitle());  
         holder.rating.setText(movie.getRating())
         //all the logic is moved into util class...now is clean!
         holder.authors.setText(AppUtils.getGenres(movie))
     }
     ```

  </br> 
* <b>The Open-Closed Principle (OCP)</b>:</br>
   * This principle states that <i>"Software entities such as classes, functions, modules should be open for extension but not modification."</i>
   * This means that if we are required to  add a new feature to the project, it is good practice to not modify the existing code but rather write new code that will be used by the existing code.
   * For instance, let's say we have a class called ```TimeOfDayGreeting``` with a single method ```getGreetingFromTimeOfDay```. We would like to display a greeting message when the user opens the app. This message must be based on the time of the day.

      * Example that demonstrates how this principle is violated:
     ```
       public class TimeOfDayGreeting {
         private String timeOfDay;
          public String getGreetingFromTimeOfDay() {
             if (this.timeOfDay == "Morning") {
                 return "Good Morning, sir.";
             }
             else if (this.formality == "Afternoon") {
                 return "Good Afternoon, sir.";
             }
             else if (this.formality == "Evening") {
                 return "Good Evening, sir.";
             }
             else {
                 return "Good Night, sir.";
             }
          }
          public void setTimeOfDay(String timeOfDay) {
             this.timeOfDay = timeOfDay;
          }
       }
     ```


      * Example that demonstrates how this principle can used used:

      ```
        /* Create an interface called TimeOfDay and let the Morning, Afternoon, Evening classes implement this interface. 
         * This interface can then be called inside the timeOfDayGreeting class  
         */
        public class TimeOfDayGreeting {
            private TimeOfDay timeOfDay;
            public TimeOfDayGreeting(TimeOfDay timeOfDay) {
                this.timeOfDay = timeOfDay;
            }
            public String getGreetingFromTimeOfDay() {
                return this.timeOfDay.greet();
            }
        }
        public interface TimeOfDay {
            public String greet();
        }
        /*  Morning class  */
        public class Morning implements TimeOfDay {
            public String greet() {
                return "Good morning, sir.";
            }
        }
        /*  Afternoon class  */
        public class Afternoon implements TimeOfDay {
            public String greet() {
                return "Good afternoon, sir.";
            }
        }
        /*  Evening class  */
        public class Evening implements TimeOfDay {
            public String greet() {
                return "Good evening, sir.";
            }
        }
        /*  Night class  */
        public class Night implements TimeOfDay {
            public String greet() {
                return "Good night, sir.";
            }
        }
      ```

  </br>

* <b>The Liskov Substitution Principle (LSP)</b>:</br>
   * This principle states that <i>"Child classes should never break the parent class’ type definitions."</i>
   * This means that a sub class should override the methods from a parent class that does not break the functionality of the parent class.
   * For example, let's say we have a interface ```ClickListener```. This interface is implemented by the fragments 1 & 2.

      * Example that demonstrates how this principle is violated:
     ```
       public interface ClickListener {
         public void onClick();
       }
       
       public class Fragment1 implements ClickListener {
         @Override
         public void onClick() {
             //handle logic            
         }
       }
       
       public class Fragment2 implements ClickListener {
         @Override
         public void onClick() {
             //handle logic            
         }
         
         public void updateClickCount() {
             
         }
       } 
       public void onButtonClick(ClickListener clickListener) {
          //IF we have a requirement where we need to increment the click count in framgent2 but not in fragment1, 
          // we would have to follow something like this, which is bad practice.
          if(clickListener instanceOf Fragment2) {
             clickListener.updateClickCount();  
          }
          clickListener.onClick();
       }        
     ```

      * Example that demonstrates how this principle can be used:

     ```
       public interface ClickListener {
         public void onClick();
       }
       
       public class Fragment1 implements ClickListener {
         @Override
         public void onClick() {
             //handle logic            
         }
       }
       
       public class Fragment2 implements ClickListener {
         @Override
         public void onClick() {
             updateClickCount();
             //handle logic
         }
         
         public void updateClickCount() {
             
         }
       }
       
       public void onButtonClick(ClickListener clickListener) {
          clickListener.onClick();
       }        
     ```      

</br>      

* <b>The Interface Segregation Principle (ISP)</b>:</br>
   * This principle states that <i>"The interface-segregation principle (ISP) states that no client should be forced to depend on methods it does not use."</i>
   * This means that if an interface becomes too fat, then it should be split into smaller interfaces so that the client implementing the interface does not implement methods that are of no use to it.
   * For example, let's take the ```TextWatcher``` interface in Android. we know that the ```TextWatcher``` interface has 3 methods:

      * Example of how this principle is violated:
        ```
          editText.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                  
              }