# Multiple Rows creation using Linear Layout
Create Multiple rows

 ```       
  public void setSingleRowMultiTime(LinearLayout paramLayout, List itemlist){

	    for(int i = 0; i < itemlist.size(); i++){
		    LinearLayout layout = (LinearLayout)Inflater.inflate(R.layout.simple_row,false,null);
		    TextView text = (TextView) findViewById(R.id.text);
		    text.setText("SetValue");
		    paramLayout.addView(layout);
	    }
  }
  
 ```