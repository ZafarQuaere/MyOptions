# Optimization of layout hierarchy:
1) Use Constraint or Relative layout rather than linear to avoid nested.
2) Use Lint
    A.	Use compound drawables - A LinearLayout which contains an ImageView and a TextView can be more efficiently handled as a
        compound drawable.
    B.	Merge root frame - If a FrameLayout is the root of a layout and does not provide background or padding etc, it can be replaced
        with a merge tag which is slightly more efficient.
    C.	Useless leaf - A layout that has no children or no background can often be removed (since it is invisible) for a flatter and
        more efficient layout hierarchy.
    D.	Useless parent - A layout with children that has no siblings, is not a ScrollView or a root layout, and does not have a background,
        can be removed and have its children moved directly into the parent for a flatter and more efficient layout hierarchy.
    E.	Deep layouts - Layouts with too much nesting are bad for performance. Consider using flatter layouts such as RelativeLayout or
        GridLayout to improve performance. The default maximum depth is 10.

3) Create a Reusable layout with <include> tag.
4) Use <merge/> tag.
    The <merge /> tag helps eliminate redundant view groups in your view hierarchy when including one layout within another.
    For example, if your main layout is a vertical LinearLayout in which two consecutive views can be re-used in multiple layouts,
    then the re-usable layout in which you place the two views requires its own root view. However, using another LinearLayout
    as the root for the re-usable layout would result in a vertical LinearLayout inside a vertical LinearLayout.
    The nested LinearLayout serves no real purpose other than to slow down your UI performance.

<merge xmlns:android="http://schemas.android.com/apk/res/android">
    <Button
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="@string/add"/>
    <Button
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="@string/delete"/>
</merge>
    Now, when you include this layout in another layout (using the <include/> tag), the system ignores the <merge> element and places
    the two buttons directly in the layout, in place of the <include/> tag.

Making list view scrolling smooth :
1) Use a background thread.
2) Hold view objects in ViewHolder.
