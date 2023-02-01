# for generating random alphanumeric string like captcha.

```
    private String generateRandomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(len);
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        displayCaptcha(sb.toString());
        return sb.toString();
    }
```
<b> After generating alphanumeric string we need to display as </b>

```
    private void displayCaptcha(String radomValue) {
        Utils.Debug("Random Value : " + radomValue);
        lytCaptcha = (LinearLayout) view.findViewById(R.id.lytCaptcha);
        //setting length for edittext
        edtCaptcha.setFilters(new InputFilter[]{new InputFilter.LengthFilter(radomValue.length())});

        if (radomValue != null && (!radomValue.equals(""))) {
            for (int i = 0; i < radomValue.length(); i++) {
                TextView rndText = new TextView(getActivity());
                rndText.setText(radomValue.charAt(i) + "");
                rndText.setTextColor(Color.parseColor("#ffffff"));
                rndText.setTextSize(25);
                rndText.setPadding(5, 5, 5, 5);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                //layoutParams.gravity = i%2==0 ? Gravity.BOTTOM : Gravity.TOP;
                rndText.setLayoutParams(layoutParams);
                rndText.setGravity(i % 2 == 0 ? Gravity.BOTTOM : Gravity.TOP);
                lytCaptcha.addView(rndText);
            }
        }
    }
```

// where random value or string is :
    radomValue = generateRandomString(6);