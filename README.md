# changetextcolor
点击改变listview的item的颜色，点击另外一项变色，该项换回原色

Listview每生成一个item都会执行一次getview()  <br>
所以我们在Adapter中加入了两个字段,这两个字段是用来判断当前的item的position和该item有无被点击的<br>
mCurrentItem的初始值为0,所以在不断执行getview()时就进行了各个item项的position与mCurrentItem进行对比<br>
最后通过if语句来执行对比后的设置<br>
在我们的Activity中,我们调用了Adapter中的两个对外开放的方法分别是设置点击事件和设置当前的item项的position<br>
最后再调用Adapter的notifyDatasetChanged().刷新Listview<br>
<br>
参考http://blog.csdn.net/qq_14813933/article/details/50417859<br>
<br>
<br>
<br>
三个textview，点中变颜色，其他为黑色<br>
方法在点击函数设置一个方法，resetbutton（），把三个textview都先setTextColor（）为黑色，放在onClick第一行<br>
每次一点击，先重置，全部恢复到黑色状态，如何点击哪个，哪个设置为改变的颜色
