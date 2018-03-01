# changetextcolor
点击改变listview的item的颜色，点击另外一项变色，该项换回原色

Listview每生成一个item都会执行一次getview()  <br>
所以我们在Adapter中加入了两个字段,这两个字段是用来判断当前的item的position和该item有无被点击的<br>
mCurrentItem的初始值为0,所以在不断执行getview()时就进行了各个item项的position与mCurrentItem进行对比<br>
最后通过if语句来执行对比后的设置<br>
在我们的Activity中,我们调用了Adapter中的两个对外开放的方法分别是点击为真和设置当前的item项的position<br>
isClick用来约束的，一开始isClick为false，编译后字是黑色的，如果没有这个约束条件，编译后第0项会是红色，因为mCurrentItem初始值为0，position也从0开始<br>
最后再调用Adapter的notifyDatasetChanged().刷新Listview<br>
<br>
参考http://blog.csdn.net/qq_14813933/article/details/50417859<br>
<br>
<br>
<br>
三个textview，点中变颜色，其他为黑色<br>
方法在点击函数设置一个方法，resetbutton（），把三个textview都先setTextColor（）为黑色，放在onClick第一行<br>
每次一点击，先重置，全部恢复到黑色状态，如何点击哪个，哪个设置为改变的颜色
<br>
<br>
实现中的错误记录<br>
1.   if (mCurrentItem==position&&isClick){                        <br>  
                textView.setTextColor(Color.RED);<br>
            }else {<br>
                textView.setTextColor(Color.BLACK);<br>
            }<br>
            if (list.get(position).equals(value)){<br>
                textView.setTextColor(Color.RED);<br>
            }else {<br>
                textView.setTextColor(Color.BLACK);<br>
            }<br>
 ![图片加载失败，将显示此文字](https://github.com/Rye10/changetextcolor/blob/master/src/main/res/drawable/3.jpg)
 点击后也无法变色，原因是java从上到下执行顺序问题，只会显示value和list相同项的红色，点击后改变了position和isClick，执行到最后不相等，颜色为黑色<br>
<br>
<br>
2.    if (list.get(position).equals(value)){<br>
                textView.setTextColor(Color.RED);<br>
            }else {<br>
                textView.setTextColor(Color.BLACK);<br>
            }<br>
            if (mCurrentItem==position&&isClick){   <br>                       
                textView.setTextColor(Color.RED);<br>
            }else {<br>
                textView.setTextColor(Color.BLACK);<br>
            }<br>
   ![图片加载失败，将显示此文字](https://github.com/Rye10/changetextcolor/blob/master/src/main/res/drawable/1.jpg)
     value和list相同项不变红色，编译后为黑色，点击变色，原因也是java执行顺序问题<br>
     <br>
     <br>
3.    if (mCurrentItem==position&&isClick){<br>
                textView.setTextColor(Color.RED);<br>
            }else {<br>
                textView.setTextColor(Color.BLACK);<br>
            }<br>
            if (list.get(position).equals(value)){<br>
                textView.setTextColor(Color.RED);<br>
            }<br>
    ![图片加载失败，将显示此文字](https://github.com/Rye10/changetextcolor/blob/master/src/main/res/drawable/4.jpg)
    一直保持value和list相同为红色，没有其他约束，点击其他的变色<br>
 
