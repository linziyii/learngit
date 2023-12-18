<template>
  <div style="background: #ececec; padding: 30px">
    <a-card title="关于MyKi项目" :bordered="false" >
      <h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="SpringBootVue3_WIKI_0"></a>SpringBoot+Vue3 MyKi项目</h1>
<h2 class="code-line" data-line-start=2 data-line-end=3 ><a id="1_2"></a>1、项目说明：</h2>
<h3 class="code-line" data-line-start=2 data-line-end=3 ><a id="1_2"></a>MyKi译为my wiki,是新一代简洁的知识库系统</h3>
<h2 class="code-line" data-line-start=4 data-line-end=5 ><a id="11_4"></a>1.1安装说明：</h2>
<h3 class="code-line" data-line-start=6 data-line-end=7 ><a id="111_6"></a>1.1.1项目初始化：</h3>
<pre><code class="has-line-data" data-line-start="9" data-line-end="12">                  cd web
                  npm install
启动前端： npm run serve
</code></pre>
<ul>
<li class="has-line-data" data-line-start="13" data-line-end="14">sql文件在doc文件夹下可以找到</li>
<li class="has-line-data" data-line-start="14" data-line-end="16">修改application.properties下的spring.datasource改为你的数据库连接地址和用户密码以及redis地址</li>
</ul>
<h3 class="code-line" data-line-start=16 data-line-end=17 ><a id="12_16"></a>1.2项目结构说明</h3>
<p class="has-line-data" data-line-start="18" data-line-end="19">后端Springboot:</p>
<p class="has-line-data" data-line-start="24" data-line-end="25">wiki目录下存放了后端代码，domain实体类+mapper持久层+service服务层+controller接口层，</p>
<p class="has-line-data" data-line-start="26" data-line-end="27">exception处理异常,filter实现过滤器，aspect为切面类，config存放配置类，util存放工具类。</p>
<p class="has-line-data" data-line-start="30" data-line-end="31">前端项目结构：</p>
<p class="has-line-data" data-line-start="34" data-line-end="35">常见的vue 项目结构：</p>
<p class="has-line-data" data-line-start="36" data-line-end="37">dist为编译vue后生成的执行目录直接部署在服务器上运行，router中指定页面路由跳转的信息，views定义视图层。</p>
<h3 class="code-line" data-line-start=38 data-line-end=39 ><a id="13_38"></a>1.3项目所使用的技术栈：</h3>
<h2 class="code-line" data-line-start=42 data-line-end=43 ><a id="2_42"></a>2、项目展示</h2>
<h3 class="code-line" data-line-start=46 data-line-end=47 ><a id="21_46"></a>2.1访客浏览界面：</h3>
<h4 class="code-line" data-line-start=48 data-line-end=49 ><a id="211__48"></a>2.1.1 未登录状态下的首页</h4>
<p class="has-line-data" data-line-start="56" data-line-end="57">使用echarts图表自动展示访问人数,并实时的显示点赞数和总阅读量，点赞量和点赞率，左侧显示的电子书分类在登陆后由管理员进行编辑。</p>
<h4 class="code-line" data-line-start=58 data-line-end=59 ><a id="212__58"></a>2.1.2 游客访问电子书页面：</h4>
<p class="has-line-data" data-line-start="62" data-line-end="63"><img src="image/vistor_ebook.png" alt="vistor_ebook"></p>
<p class="has-line-data" data-line-start="66" data-line-end="67">使用层级树的方式展示电子书，并实现了阅读量统计以及点赞功能，在访客点击了点赞按钮后，会对管理员进行通知：如下图所示：</p>
<p class="has-line-data" data-line-start="68" data-line-end="69"><img src="image/vistor_like.png" alt="vistor_like"></p>
<h3 class="code-line" data-line-start=72 data-line-end=73 ><a id="22_72"></a>2.2管理员页面</h3>
<h4 class="code-line" data-line-start=74 data-line-end=75 ><a id="21_74"></a>2.1登录页面</h4>
<p class="has-line-data" data-line-start="76" data-line-end="77">首页点击登录按钮自动跳转：</p>
<p class="has-line-data" data-line-start="80" data-line-end="81">管理员具有以下几个权限：</p>
<ul>
<li class="has-line-data" data-line-start="82" data-line-end="83">用户管理权限</li>
<li class="has-line-data" data-line-start="83" data-line-end="84">电子书管理权限</li>
<li class="has-line-data" data-line-start="84" data-line-end="85">电子书分类权限</li>
</ul>
<h4 class="code-line" data-line-start=88 data-line-end=89 ><a id="221_88"></a>2.2.1用户管理页面</h4>
<p class="has-line-data" data-line-start="92" data-line-end="93"><img src="image/admin_manage_user.png" alt="admin_manage_user"></p>
<p class="has-line-data" data-line-start="94" data-line-end="95">管理员可查询用户列表，新增或删除用户，修改用户密码，密码进行了二次加密，在服务器端并不会明文存储用户的密码信息，保证登录的安全性。</p>
<h3 class="code-line" data-line-start=98 data-line-end=99 ><a id="222__98"></a>2.2.2 电子书管理页面</h3>
<p class="has-line-data" data-line-start="100" data-line-end="101">电子书管理页面可以对项目进行一个简单的编辑</p>
<p class="has-line-data" data-line-start="102" data-line-end="103"><img src="image/edit_ebook.gif" alt="edit_ebook"></p>
<p class="has-line-data" data-line-start="106" data-line-end="107">在编辑文档页面可以选择父文档，从而实现层级树管理，集成了wangeditor富文本编辑器，样式丰富，可以设置标题，加粗，选择字体，引入视频，引入代码块等功能。编辑完成后，电子书就可以显示在访客界面被游客浏览。</p>
<h3 class="code-line" data-line-start=110 data-line-end=111 ><a id="223_110"></a>2.2.3电子书分类页面：</h3>
<p class="has-line-data" data-line-start="112" data-line-end="113"><img src="image/category.png" alt="category"></p>
<p class="has-line-data" data-line-start="114" data-line-end="115">可以自定义页面，同样实现了层级树管理，编辑后，电子书可以选择分类，并显示在首页上。</p> 
    </a-card>
  </div>
</template>



