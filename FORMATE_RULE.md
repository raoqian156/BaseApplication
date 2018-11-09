## 以下为本分支做的项目优化内容
> 本文档阅读优化插件 Markdown support 

1.所有可视文件（png、webp、jpeg）全部移至 <font size=6 color="#FF0000">mipmap</font>  对应文件夹

2.<font color="#aabb00">点九图、由xml写成的图片资源 </font>普通用法都放到<font color="#aabb00">drawable</font>文件夹，
有屏幕适配等特殊要求才放到对应的尺寸下面

3.三色以内非渐变图片可以直接用psd、svg文件生成 Vector 图片

4.layout 控件命名方式，常用省略写法表

 View命名全称 | 简称| View命名全称 | 简称|View命名全称 | 简称| View命名全称 | 简称
----------:|:-------|----------:|:---------|----------:|:---------|----------:|:-------
View|view|TextView|tv|EditText|et|Spinner	|pn
Button|btn|ImageView|iv|ListView|lv|ToggleButton|tb
GridView|gv|ProgressBar|pb|SeekBar|sb|RadioButtion|rb
CheckBox|cb|ScrollView|sv|LinearLayout|ll|FrameLayout|fl
RelativeLayout|rl|Recyclerview|rv|WebView|web|VideoView|vv

在确定控件只有点击事件的情况下可以用click_替换上述简称，

5.图片类xml命名方式
>1.select_ched_pres_<font color="#FF0000">c</font><font color="#F3B524">000000_ffffff<font>

表示在 state_checked or state_pressed == true 为000<font color="#FF0000">颜色</font>（c->color  p->png  d->drawable）,
<font color="#F3B524">000_fff 表示true ->000,若为000<font>