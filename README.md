# 基于Servlet+JSP的图书管理系统

## 一、项目介绍

### 1.项目说明

本项目是一个完全从0带领大家实现的一个非常基础的WEB项目，非常适合零基础和在校的大学生来动手实现。既能提升技术熟练度了解软件开发的具体过程。同时也能帮助大家搞定毕业设计的需求。

### 2.技术栈介绍

本项目用到的技术栈主要还是 Servlet + JSP + MySQL ，对数据库的操作是通过Apache的DBUtils来实现的。简化了JDBC的操作。在页面呈现这块这次会使用HPlus一套基于bootstrap的页面模板来实现。

**整体是一个B/S结构的WEB项目**

![图片1](/images/图片1.png)

具体用到的技术栈如下:

![图片2](/images/图片2.png)

同时也是告诉大家要完成这个项目，必须提前准备的技术栈：

- Servlet + JSP

- Maven

- HTML + CSS + JS

- Ajax + JQuery

- MySQL

- Tomcat

- ...

### 3.表结构介绍

数据库表结构这块我们涉及到三大块：

- 系统管理
- 学生管理
- 图书管理

系统管理的表结构都以 sys_ 开头，其他的业务表以 t_ 开头。

![图片3](/images/图片3.png)



具体的建表SQL查看百度网盘资料即可。

链接：https://pan.baidu.com/s/1SgvumGh7emrONTVyjpTC_A?pwd=8haw 
提取码：8haw 

## 二、环境搭建

### 1.项目工具

本项目涉及到的工具都有在云盘提供，自行下载即可

- JDK8
- IDEA2021
- Tomcat8.5
- MySQL的客户端工具SQLYog
- ...

链接：https://pan.baidu.com/s/1A8XgbKvBksVkTeqOjUSh_A?pwd=boge 
提取码：boge

### 2.项目搭建

通过IDEA创建maven项目。勾选脚手架工具。选择 `maven-archetype-webapp`

![图片4](/images/图片4.png)

设置项目的基础信息

![图片5](/images/图片5.png)

### 3.基本配置

#### 3.1 JDK配置

JDK使用的是**JDK8**。我们也需要配置下：在 **File** 中选择 `Project Structure...`

![图片6](/images/图片6.png)

然后指定JDK版本即可

![图片7](/images/图片7.png)

#### 3.2 Maven配置

Maven会管理我们的相关jar包依赖。需要去中央仓库下载相关的jar。但是中央仓库在国外。下载速度很慢。这时我们需要添加下阿里云的镜像地址。我们先找到Maven的配置信息。

![图片8](/images/图片8.png)



然后把提前给大家准备的 **settings.xml** 文件放到该目录下即可

![图片9](/images/图片9.png)

#### 3.3 Tomcat配置

Tomcat是我们的基础Web环境。先通过提供的Tomcat压缩文件。在本地解压缩一个Tomcat环境。我就在E盘的Tomcat目录下放了一个Tomcat8.5的环境。

![图片10](/images/图片10.png)

然后在IDEA中配置Tomcat，点击下图中的 **`Add Comfiguration`** 弹出下面的窗口。

![图片11](/images/图片11.png)

然后点击左侧的 **`Add new ...`**

![图片12](/images/图片12.png)

得到下面的窗口。然后配置Tomcat8的环境。

![图片13](/images/图片13.png)

然后需要选择下需要部署的项目。

![图片14](/images/图片14.png)

![图片15](/images/图片15.png)

把项目的应用路径统一设置为 **`/`**

![图片16](/images/图片16.png)

Tomcat服务界面介绍

![图片17](/images/图片17.png)

启动Tomcat服务出现端口被占的情况。我们修改端口即可。

![图片18](/images/图片18.png)

看到如下的界面。表示Tomcat配置成功。

![图片19](/images/图片19.png)

启动Tomcat在控制台看到的乱码情况，我们只需要调整下配置文件中的编码方式。

![图片20](/images/图片20.png)

调整 logging.properties 中的如下编码方式为 GBK 即可。

![图片21](/images/图片21.png)

搞定。

![图片22](/images/图片22.png)

### 4.项目结构

项目是一个Maven项目。所以我们需要创建相关的目录结构。比如 **java** 和 **resources**

![图片23](/images/图片23.png)

![图片24](/images/图片24.png)

项目结构介绍

- java：存放相关的java代码
- resources： 存放相关的配置文件
- webapp：web资源【图片、css、js、jsp文件】
- target：运行时的编译目录
- pom.xml：maven的核心文件，我们需要添加的相关的依赖都是在该文件中配置的

![图片25](/images/图片25.png)

### 5.添加依赖

然后我们可以在 `pom.xml` 中，添加如下的相关依赖。

```xml
<dependencies>
	<!-- mysql驱动 -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.19</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>

    <!-- 通过Apache DBUtils来完成数据库的操作 -->
    <dependency>
        <groupId>commons-dbutils</groupId>
        <artifactId>commons-dbutils</artifactId>
        <version>1.6</version>
    </dependency>
    <!-- 前端页面模板使用的是JSP 那么jstl标签库是必须的 -->
    <dependency>
        <groupId>javax.servlet.jsp.jstl</groupId>
        <artifactId>jstl-api</artifactId>
        <version>1.2</version>
    </dependency>
    <dependency>
        <groupId>taglibs</groupId>
        <artifactId>standard</artifactId>
        <version>1.1.2</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.web</groupId>
        <artifactId>jstl-impl</artifactId>
        <version>1.2</version>
    </dependency>
    <!-- 添加JSON数据的管理工具 FastJSON -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.80</version>
    </dependency>
	<!-- 单元测试 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
    </dependency>
    
</dependencies>
```

注意添加了依赖后，我们一定要加载依赖。

![图片26](/images/图片26.png)

看到下面信息表示添加成功。

![图片27](/images/图片27.png)

### 6.整合静态资源

#### 6.1 整合登录页面

前端页面展示这块我们就通过已经准备好的页面静态文件来实现，我们先把相关的文件导入进去。先来看看页面效果。

登录页面效果：

![图片28](/images/图片28.png)

首先我们需要拷贝相关的静态资源文件

- css
- js
- 图片
- 插件

![图片29](/images/图片29.png)

然后在 **`webapp`** 目录下创建 **`login.jsp`** 文件。然后把静态资源文件中的 **`login-v2.html`** 中的 **`HTML`** 代码拷贝到 **`login.jsp`** 文件中。

![图片30](/images/图片30.png)

如果要修改登录页面的背景图片。你只需要添把更新的图片保存到 **webapp/img** 目录中。同时覆盖文件名 **`login-background.jpg`**

![图片31](/images/图片31.png)

然后搞定！

#### 6.2 整合首页页面

先来看下主页面的布局效果

![图片32](/images/图片32.png)

然后我们需要在 **`webapp`** 目录下创建两个jsp文件[**main.jsp**、**home.jsp**]

![图片33](/images/图片33.png)

然后分别把模板资源中的 **`index.html`** 中的代码拷贝到 **`main.jsp`** 中。然后把 **`index_v2.html`** 中的资源拷贝到 **`home.jsp`** 中。同时修改 **`main.jsp`** 中对首页资源的访问从原来的 **` index_v2.html`** 修改为 **`home.jsp`** 即可。

![图片34](/images/图片34.png)

## 三、系统功能

### 1.用户管理

#### 1.1 查询用户信息

##### 1.1.1 流程分析

我们需要展示的数据是 **`sys_user`** 表结构中的数据。

![图片35](/images/图片35.png)

然后对应的实现逻辑。

![图片36](/images/图片36.png)

##### 1.1.2 代码结构

清楚了我们要操作的数据。我们就可以来创建相关的代码。整个项目的结构我们分为 **`com.boge.sys`** 和 **`com.boge.book`** 两个模块。

![图片37](/images/图片37.png)

然后创建 **`SysUser`** 实体对象。

```java
@Data
public class SysUser {

    private Integer id; 
    private String username; 
    private String password; 
    private String nickname; 
    private Integer roleId;
    private String rolename; 
    private String img; 
    private Date createTime;
}
```

我们在这块使用了 **`lombok`** 来简化实体的定义。这块需要添加对应的依赖。

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.2</version>
</dependency>
```

然后在idea中，我们需要添加lombok的插件。

![图片38](/images/图片38.png)

然后添加 **`Dao`** 的接口定义和对应的实现。

```java
/**
* Dao层的接口对象
*/
public interface IUserDao {

    /**
    *查询所有的用户信息
    *@return
    */
    public List<SysUser> list(SysUser user);
}

public class UserDaoImpl implements IUserDao {

    @Override
    public List<SysUser> list(SysUser user) {

        return null;
    }

}
```

##### 1.1.3 DBUtils封装

定义的Dao的实现类。然后我们就需要通过JDBC来实现对数据库表结构中数据的 **`CRUD`** 操作。为了简化操作我们通过 **`Apache Dbutils`** 来实现。那么我们定义一个公共的 **`MyDbUtils `** 工具类。

```java
/**
* 操作数据库的工具类
*/
public class MyDbUtils {

    // 定义的数据源
    private static MysqlDataSource dataSource;

    static {
        // 完成数据源的初始化操作
        dataSource = new MysqlDataSource();
        // 注意默认安装的数据的端口是 3306 这块需要结构自己数据库的情况调整
        dataSource.setUrl("jdbc:mysql://localhost:3320/books?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
    }

    public static QueryRunner getQueryRunner(){ 
        return new QueryRunner(dataSource);
    }
}
```

然后就可以在 **`UserDaoImpl`** 中，通过我们提供的工具类来完成数据库的操作操作了。

```java
@Override
public List<SysUser> list(SysUser user) {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
    String sql = "select * from sys_user ";
    try {
        // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
        // 此处不会通过驼峰命名法 装换
        // List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser>(SysUser.class));
        List<SysUser> list = queryRunner.query(sql, new ResultSetHandler<List<SysUser>>
        () {
            @Override
            public List<SysUser> handle(ResultSet resultSet) throws SQLException {
                // 存储返回结果的容器
                List<SysUser> list = new ArrayList<>();
                while(resultSet.next()){
                    // 每次循环一次 user 存储一条数据
                    SysUser user = new SysUser(); 	
                    user.setId(resultSet.getInt("id")); 
                    user.setUsername(resultSet.getString("username")); 
                    user.setPassword(resultSet.getString("password")); 
                    user.setNickname(resultSet.getString("nickname")); 
                    user.setRoleId(resultSet.getInt("role_id")); 
                    user.setRolename(resultSet.getString("rolename"));
                    user.setCreateTime(resultSet.getDate("create_time")); 
                    list.add(user); // 把查询的记录封装到了集合容器中
                }
                return list; // 返回查询的结果
            }
        });
        return list;
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return null;
}

public static void main(String[] args) { 
    IUserDao dao = new UserDaoImpl(); 
    List<SysUser> list = dao.list(null); 
    for (SysUser sysUser : list) {
        System.out.println(sysUser);
    }
}
```

然后通过测试代码完成查询操作，看到如下结果表示成功。

![图片39](/images/图片39.png)

##### 1.1.4 service

service这块我们同样的需要定义相关的接口和实现类。

```java
/**
*系统用户
*业务处理的接口定义
*/
public interface IUserService {

    public List<SysUser> list(SysUser user);
}

public class UserServiceImpl implements IUserService {

    private IUserDao dao = new UserDaoImpl();

    @Override
    public List<SysUser> list(SysUser user) {
        return dao.list(user);
    }
}
```

##### 1.1.5 Servlet

servlet的作用是接口浏览器传递的请求。然后根据service的处理把相关的结果响应给浏览器。我们基于Servlet的规范来创建对应的Servlet。那么Servlet需要继承 `HttpServlet` 然后我们重写对应的 `doGet` 和 `doPost` 方法。

```java
/**
*  urlPatterns:这个是提供给客户端访问Servlet的请求路径
*/
@WebServlet(name = "userServlet",urlPatterns = {"/userServlet"}) 
public class UserServlet extends HttpServlet {

    private IUserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有的用户信息
        List<SysUser> list = service.list(null);
        // System.out.println(list);
        // 把数据绑定在对应的作用域中
        req.setAttribute("list",list); 
        req.getRequestDispatcher("/sys/user/list.jsp").forward(req,resp);
    }
}
```

##### 1.1.6 页面展示

数据展示页面我们通过 **`sys/user/list.jsp`** 来实现。注意我们需要调整 **`web.xml`** 中的版本信息。

![图片40](/images/图片40.png)

EL表达式没有被识别，我们需要更新 **`web.xml`** 中的信息。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
version="4.0">
</web-app>
```

然后我们通过 **`jstl`** 标签来实现数据的呈现。

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>用户管理</h1>
        <table>
            <th>
            <td>id</td>
            <td>账号</td>
            <td>昵称</td>
            <td>角色</td>
            <td>创建时间</td>
            </th>
        <c:forEach items="${requestScope.list}" var="entity">
            <tr>
                <td>${entity.id}</td>
                <td>${entity.username}</td>
                <td>${entity.nickname}</td>
                <td>${entity.rolename}</td>
                <td>${entity.createTime}</td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
```

然后对应效果如下：

![图片41](/images/图片41.png)

把 **`sys/user/list.jsp`** 整合到系统的 **`main.jsp`** 页面中。也就是在我们整体的菜单中点击 **`用户管理`** 需要展示 **`list.jsp`** 中的数据。

![图片42](/images/图片42.png)

然后我们需要在 **`sys/user/list.jsp`** 中使用 **`bootstrap`** 的样式要调整数据的展示。最终的效果如下：

![图片43](/images/图片43.png)

#### 1.2 添加用户信息

##### 1.2.1 后端逻辑处理

我们先实现了后端的用户数据添加的逻辑。Dao的实现：

```java
public interface IUserDao {

    /**
*查询所有的用户信息
*@return
*/
    public List<SysUser> list(SysUser user);

    public int save(SysUser user);
}

@Override
public int save(SysUser user) {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner();
    String sql = "insert into sys_user(username,password,nickname)values(?,?,?)"; try {
        return
            queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getNickname());
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return -1;
}
```

然后是service的处理。

```java
public interface IUserService {

    public List<SysUser> list(SysUser user);

    public int save(SysUser user);
}

public class UserServiceImpl implements IUserService {

    private IUserDao dao = new UserDaoImpl();

    @Override
    public List<SysUser> list(SysUser user) { return dao.list(user);
                                            }

    @Override
    public int save(SysUser user) {
        return dao.save(user);
    }
}
```

然后是 Servlet 的处理，在 Servlet 中需要处理的请求很多。所以我们通过 action 来识别。

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 让客户端提交一个具体请求的标识符
    String action = req.getParameter("action");
    if("save".equals(action)){
        // 表示添加数据
        // 获取客户端提交的数据
        String username = req.getParameter("username"); 
        String password = req.getParameter("password"); 
        String nickname = req.getParameter("nickname"); 
        SysUser user = new SysUser(); 
        user.setUsername(username); 
        user.setPassword(password); 
        user.setNickname(nickname// 调用Service的方法完成数据的存储 
        service.save(user);
        System.out.println(user);
     }else{
        // 表示查询数据
        // 查询所有的用户数据
        List<SysUser> list = service.list(null);
        // 把查询的用户数据存储在 request作用域中 
        req.setAttribute("list",list);
        // 通过服务端转发的方式跳转页面
        req.getRequestDispatcher("/sys/user/list.jsp").forward(req,resp);
    });
}
```

##### 1.2.2 前端表单处理

然后我们就需要来处理下前端页面的表单逻辑。首先是点击添加按钮需要跳转到添加数据的页面。

![图片44](/images/图片44.png)

然后我们在 userServlet 中需要添加跳转的逻辑处理。

![图片45](/images/图片45.png)

然后添加 **`addOrUpdate.jsp`** 页面。在页面中添加数据的表单信息。具体如下：

![图片46](/images/图片46.png)

数据提交的地址为 **`/sys/userServlet?action=saveOrUpdate`** 提交成功后数据出现乱码问题。

![图片47](/images/图片47.png)

##### 1.2.3 中文乱码

针对于客户端提交数据到服务器涉及到中文的情况下。因为编码不一致的情况会出现中文乱码问题。我们对应的解决方案如下：

![图片48](/images/图片48.png)

针对上面表单提交数据乱码的问题。

![图片49](/images/图片49.png)

我们再添加数据的时候就没有出现乱码了。

![图片50](/images/图片50.png)

为了对编码方式的统一处理。我们添加过滤器 **`filter/EncodingFilter.java`**。然后在过滤器中统一做编码问题的解决。

```java
/**
* 统一设置Post方式提交数据的编码方式
*/
@WebFilter(filterName = "encodingFiler",urlPatterns = "/*") 
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
        throws IOException, ServletException {
    	req.setCharacterEncoding("utf-8"); // 设置编码方式为utf-8 
        chain.doFilter(req,res); // 放过请求
    }

}
```

##### 1.2.4 头像功能

在系统用户中我们需要维护用户的头像信息。这块需要涉及到文件上传和下载的操作。这块也是非常基础的功能。正好在这块介绍下在当前项目中的应用。在Servlet中我们需要操作文件上传下载功能我们需要引入相关的依赖 **`commons-fileupload`** 和 **`commons-io`** 具体如下：

```xml
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.3</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.6</version>
</dependency>
```

然后具体的上传我们通过 **`百度插件`** 来实现这块的功能。[**http://fex.baidu.com/webuploader/**](http://fex.baidu.com/webuploader/)

```html
<!-- 百度WebUpload插件 -->
<link rel="stylesheet" type="text/css" href="/css/plugins/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="/css/demo/webuploader-demo.css">

<!-- Web Uploader -->
<script type="text/javascript">
    // 添加全局站点信息
    var BASE_URL = '/js/plugins/webuploader';
</script>
<script src="/js/plugins/webuploader/webuploader.min.js"></script>

<script src="/js/demo/webuploader-demo.js"></script>
```

然后在表单中添加头像的表单域信息。

![图片51](/images/图片51.png)

然后添加对应的 **`js`** 处理逻辑

```js
<script>
    function resetPage(){ 
    	window.location.href="/sys/userServlet"
    }
    $(document).ready(function () { })

    var $ = jQuery,
        $list = $('#thelist'),
        $btn = $('#ctlBtn'), 
        state = 'pending', 
        uploader;

    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: BASE_URL + '/js/Uploader.swf',
        // 文件接收服务端。
        server: '/sys/uploadServlet',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash. pick: '#filePicker',
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png', mimeTypes: 'image/*'
        }
    });

    uploader.on( 'uploadSuccess', function( file,data ) {
        $( '#'+file.id ).addClass('upload-state-done');
        $("#imgName").val(data._raw);
        $("#msg").text(data._raw)
    });

</script>
```

有了这些操作后我们就可以看下访问的效果。

![图片52](/images/图片52.png)

在改插件中我们指定的上传地址是 **`/sys/uploadServlet`** ,然后我们需要创建该Servlet。

```java
/**
* 处理文件上传操作的Servlet
*/
@WebServlet(name = "uploadServlet",urlPatterns = {"/sys/uploadServlet"}) 
public class UploadServlet extends HttpServlet {

    public static final String UPLOAD_DIRECTORY = "d:\\shopFile\\";
    //private static final String UPLOAD_DIRECTORY = "upload";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        // 检测是否是文件
        // 这个路径相对当前应用的目录
        String uploadPath = UPLOAD_DIRECTORY;
        // 如果目录不存在则创建
        File uploadDir = new File(UPLOAD_DIRECTORY); 
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory(); 
        ServletFileUpload upload = new ServletFileUpload(factory); 
        try {
            List<FileItem> fileItems = upload.parseRequest(request); 
            if(fileItems != null && fileItems.size() > 0){
                for (FileItem fileItem : fileItems) { 
                    if(!fileItem.isFormField()){
                    // 表明该信息上传的文件信息
                    String fileName = new File(fileItem.getName()).getName();
                    // 上传的文件名称
                    fileName = new Date().getTime() + fileName.substring(fileName.lastIndexOf("."));

                    String filePath = uploadPath + File.separator + fileName; 
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    fileItem.write(storeFile);
                    //request.setAttribute("message",  "文件上传成功!");
                    // 把生成的文件名称返回给客户端
                    PrintWriter writer = response.getWriter(); 
                    writer.write(fileName);
                    writer.flush();
                    writer.close();
                }
               }
            }
        }catch (Exception e){ 
            e.printStackTrace();
        }
    }
}
```

文件上传成功后。Servlet中会返回上传成功的文件的名称。我们会把这个名称绑定在表单中的一个隐藏属性中。这样在表单提交的时候会把名称存储在数据库中。

![图片53](/images/图片53.png)

![图片54](/images/图片54.png)

同时我们需要修改下保存用户数据和查询数据的逻辑。添加 **`img`** 字段的处理。

![图片55](/images/图片55.png)

Dao中的处理调整。

![图片56](/images/图片56.png)

上传成功后，提交表单我们就会在数据库中存储图片名称。

![图片57](/images/图片57.png)

最后在展示用户信息的时候同时展示用户的头像信息。这时我们需要增加一个下载文件的Servlet。

```java
@WebServlet(name = "downloadServet",urlPatterns = {"/sys/download"}) 
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        // 下载文件
        String basePath = UploadServlet.UPLOAD_DIRECTORY;
        // 获取需要下载的文件的名称
        String fileName = req.getParameter("fileName");
        // 处理文件上传
        InputStream in = new FileInputStream(basePath+"/"+fileName); 
        int size = in.available();
        byte[] data = new byte[size]; 
        in.read(data);
        in.close();
        // 判断是否是图片
        if(fileName.contains(".jpg")||fileName.contains(".png")){ 
            resp.setContentType("image/jpg");
         }else{
            resp.setCharacterEncoding("utf-8"); 
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;filename="+  fileName
                           +";filename*=utf-8''"+  URLEncoder.encode(fileName,"UTF-8"));
        }
        ServletOutputStream outputStream = resp.getOutputStream(); 
        outputStream.write(data);
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
        ServletException, IOException { 
        this.doGet(req,resp);
    }
}
```

然后在 **`list.jsp`** 中做相关的调整。

![图片58](/images/图片58.png)

展示效果：

![图片59](/images/图片59.png)

#### 1.3 BaseServlet

随着我们的业务需求越来越多那么在Servlet中我们需要通过if语句来判断各种请求。那么会造成 **`doGet`** 或者 **`doPost`** 方法中的代码越来越复杂。不便于开发。这时我们虽然可以通过方法抽取的方式来简化。

![图片60](/images/图片60.png)

虽然简化了doPost方法。但是还是需要很多的if语句来判断。这时我们可以再进一步的优化，也就是我们约定浏览器提交的请求中携带的 **`action`** 参数即使对应的Servlet中要处理这个请求的方法的名称。这样我们就可以通过反射方式来替换掉上面的 **`if`** 语句处理的情况。彻底分离出各个处理请求的业务方法。

```java
/**
*系统公共的Servlet
*我们约定客户端提交的请求中的action就是在Servlet要处理的方法的名称
*/
public abstract class BaseServlet extends HttpServlet {

    /**
*通过反射的方式调用对象中的Action对应的方法
*@param req
*@param resp
*@throws ServletException
*@throws IOException
*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
        ServletException, IOException {
        // 获取传递的Action参数
        String action = req.getParameter("action"); 
        try {
            // 获取当前对象对应的Method对象
            Method method = this.getClass()
                .getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 调用方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            // 调用方法执行的时候出现了异常
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    // 定义增删改查的基础方法
    public abstract void list(HttpServletRequest req, HttpServletResponse resp) throws
        Exception;
    public abstract void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
```

**`utils/Constant.java`** 请求 **action** 参数常量设置

```java
package com.huangpw.sys.utils;

/**
 * 系统常量
 */
public class Constant {

    // 上传文件目录
//    public static final String UPLOAD_DIRECTORY = "upload";
    public static final String UPLOAD_DIRECTORY = "D:\\Images\\upload";

    public static final String BASE_ACTION_LIST = "list";
    public static final String BASE_ACTION_SAVEORUPDATE = "saveOrUpdate";
    public static final String BASE_ACTION_REMOVE = "remove";
    public static final String BASE_ACTION_FINDBYID = "findById";
    public static final String BASE_ACTION_SAVEORUPDATEPAGE = "saveOrUpdatePage";
}
```

同时在 **`BaseServlet`** 中又做了抽象的处理。声明了增删改查常用的相关的方法。这样在具体的 **`Servlet`** 中我们就不用繁琐的自己去创建相关的基础放了。然后就可以改造 **`UserServlet`** 中的处理。

```java
@WebServlet(name = "userServlet",urlPatterns = {"/sys/userServlet"}) 
public class UserServlet extends BaseServlet{

    private IUserService service = new UserServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {
        // 表示查询数据
        // 查询所有的用户数据
        List<SysUser> list = service.list(null);
        // 把查询的用户数据存储在 request作用域中 req.setAttribute("list",list);
        // 通过服务端转发的方式跳转页面
        req.getRequestDispatcher("/sys/user/list.jsp").forward(req,  resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 表示添加数据
        // 获取客户端提交的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password"); 
        String nickname = req.getParameter("nickname"); 
        String img = req.getParameter("img");
        SysUser user = new SysUser(); 
        user.setUsername(username); 
        user.setPassword(password); 
        user.setNickname(nickname); 
        user.setImg(img);
        // 调用Service的方法完成数据的存储
        service.save(user);
        // 要做数据的查询
        resp.sendRedirect("/sys/userServlet?action="+  Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("/sys/user/addOrUpdate.jsp").forward(req,resp);
    }
}
```

最后注意调整之前用户操作的相关请求的地址中的 **`action`** 参数即可。

**原来的写法：**

```java
package com.huangpw.sys.servlet;

import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.service.IUserService;
import com.huangpw.sys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 自定义的系统用户Servlet
 * 处理客户端对用户的相关请求
 *  1.需要继承自HttpServlet
 *  2.重写对应的doGet和doPost方法
 *  3.通过@WebServlet注解定义相关的信息
 * urlPatterns:这个是提供给客户端访问Servlet的请求路径
 */
@WebServlet(name = "userServlet", urlPatterns = {"/sys/userServlet"})
public class UserServlet extends HttpServlet {

    private final IUserService userService = new UserServiceImpl();

    /**
     * 处理客户端的GET请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 处理客户端的POST请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("saveOrUpdate".equals(action)){
            // 表示添加数据
            // 获取客户端端提交的数据
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String nickname = req.getParameter("nickname");
            String img = req.getParameter("img");
            SysUser user = new SysUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setImg(img);
            userService.save(user);
            resp.sendRedirect("/sys/userServlet"); // 重定向到当前Servlet
        } else if("addOrUpdatePage".equals(action)) {
            // 表示跳转到添加或修改页面
            req.getRequestDispatcher("/sys/user/addOrUpdate.jsp").forward(req, resp);
        } else {
            // 表示查询数据
            // 查询所有的用户信息
            List<SysUser> list = userService.list(null);
            req.setAttribute("list", list); // 将用户信息放入请求域中
            req.getRequestDispatcher("/sys/user/list.jsp").forward(req, resp); // 跳转到用户列表页面
        }
    }
}

```



#### 1.4 更新用户信息

用户信息的更新操作，实现的逻辑是：

- 1.点击更新按钮，传递用户编号到后端
- 2.后端服务获取到id查询出对应的用户数据
- 3.跳转到更新页面。回写数据到表单中
- 4.提交更新的数据到服务
- 5.服务器获取到更新的数据后更新到数据库中

点击更新按钮传递编号到后端服务的实现。

![图片61](/images/图片61.png)

![图片62](/images/图片62.png)

然后后端处理逻辑，Dao增加根据id查询的方法

```java
@Override
public SysUser findById(int id) {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
    String sql = "select * from sys_user where id = ?"; 
    try {
        // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
        // 此处不会通过驼峰命名法 装换
        SysUser user = queryRunner.query(sql, new ResultSetHandler<SysUser>() { 
            @Override
            public SysUser handle(ResultSet resultSet) throws SQLException {
                // 存储返回结果的容器
                if(resultSet.next()){
                    // 每次循环一次 user 存储一条数据
                    SysUser user = new SysUser(); 
                    user.setId(resultSet.getInt("id")); 
                    user.setUsername(resultSet.getString("username")); 
                    user.setPassword(resultSet.getString("password")); 
                    user.setNickname(resultSet.getString("nickname"));
                    user.setRoleId(resultSet.getInt("role_id")); 
                    user.setRolename(resultSet.getString("rolename")); 
                    user.setImg(resultSet.getString("img")); 
                    user.setCreateTime(resultSet.getDate("create_time"));
                    return user;
                }
                return null; // 返回查询的结果
            }
        },id);
        return user;
    } catch (SQLException throwables) { 
        throwables.printStackTrace();
    }
    return null;
}
```

service 同样需要添加对应的方法，然后就是 Servlet 中的处理。

```java
@Override
public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws
    Exception {
    // 需要获取客户端提交的id信息
    String id = req.getParameter("id"); 
    if(StringUtils.isNotEmpty(id)){
        // 说明是更新操作。那么我们需要根据id查询出用户的具体的信息 
        SysUser user = service.findById(Integer.parseInt(id)); 
        req.setAttribute("entity",user);
    }
    req.getRequestDispatcher("/sys/user/addOrUpdate.jsp").forward(req,resp);
}
```

![图片132](/images/图片132.png)

然后在表单中回写数据。

```html
<form class="form-horizontal m-t" id="signupForm" action="/sys/userServlet? action=saveOrUpdate" method="post">
    <input type="hidden" name="id" value="${entity.id}">
    <div class="form-group">
        <label class="col-sm-3 control-label">用户名：</label>
        <div class="col-sm-8">
            <input id="username" name="username" class="form-control" value="${entity.username}"
                   type="text" aria-required="true" aria-invalid="true" class="error">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">昵称：</label>
        <div class="col-sm-8">
            <input id="lastname" name="nickname" class="form-control"
                   value="${entity.nickname}"
                   type="text" aria-required="true" aria-invalid="false" class="valid">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label">密码：</label>
        <div class="col-sm-8">
            <input id="password" name="password" class="form-control"
                   value="${entity.password}" type="password">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">确认密码：</label>
        <div class="col-sm-8">
            <input id="confirm_password" name="confirm_password" value="${entity.password}"
                   class="form-control" type="password">
            <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
        </div>
    </div>
    <<div class="form-group">
    <input type="hidden" name="img" value="${entity.img}" id="imgName">
    <label class="col-sm-3 control-label">头像：</label>
    <div class="col-sm-8">
        <!--dom结构部分-->
        <div id="uploader-demo">
            <!--用来存放item-->
            <div id="fileList" class="uploader-list">${entity.img}</div>
            <div id="filePicker">选择图片</div>
        </div>
    </div>
    </div>

    <div class="form-group">
        <div class="col-sm-8 col-sm-offset-3">
            <button class="btn btn-default" onclick="resetPage()" type="button">取消
            </button>
            <button class="btn btn-primary" type="submit">提交</button>
        </div>
    </div>
</form>
```

需要注意的是更新数据需要在表单中携带 **`id`** ，我们在上面通过 `<input type="hidden" name="id" value="${entity.id}">` 实现。提交后。我们对应的要修改Servlet中的逻辑。

![图片63](/images/图片63.png)

最后需要添加Dao中的更新方法。

```java
@Override
public int updateById(SysUser user) {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner();
    String sql = "update sys_user set username=?,password=?,nickname=?,img=? where id=?";
        try {
            return queryRunner.update(sql,user.getUsername()
                                      ,user.getPassword(),user.getNickname(),user.getImg(),user.getId());
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
    return -1;
}
```

**注意**：更新功能没有问题。我们还需要检查下添加功能是否受到影响!!!

#### 1.5 删除用户信息

删除功能是一个非常基础的功能。在点击 **`删除按钮`** 的时候，我们需要给出提示框，防止用户误操作，这块的提示框我们通过 **`SweetAlert`** 来实现。效果如下：

![图片64](/images/图片64.png)

引入 **`sweetAlert`** 组件需要添加相关的css和js文件。

```html
<!-- Sweet Alert -->
<link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<!-- Sweet alert -->
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>
```

添加对应的点击事件的处理方法。

```js
function removeData(id){ 
    swal({
        title: "您确定要删除这条信息吗", 
        text: "删除后将无法恢复，请谨慎操作！", 
        type: "warning", 
        showCancelButton: true,
        confirmButtonColor: "#DD6B55", 
        confirmButtonText: "删除", 
        closeOnConfirm: false
    }, function () {
        swal("删除成功！", "您已经永久删除了这条信息。", "success");
        $.get("/sys/userServlet?action=remove&id="+id,function(msg){
            // 再发起一个查询的操作
            window.location.href="/sys/userServlet?action=list"
        })
	});                 
}
```

然后就是对应的后端处理逻辑。

```java
@Override
public void remove(HttpServletRequest req, HttpServletResponse resp) 
    throws Exception { 
    String id = req.getParameter("id");
	int count = service.deleteById(Integer.parseInt(id)); 
    PrintWriter writer = resp.getWriter(); 
    writer.write(count+"");
	writer.flush();
    writer.close();
}
```

Dao层

```java
@Override
public int deleteById(Integer id) {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner();
    String sql = "delete from sys_user where id = ?";
    try {
        return queryRunner.update(sql, id);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1;
}
```

#### 1.6 分页查询

##### 1.6.1 分页介绍

分页功能在数据展示中是非常有必要的。原因有两块：

1. 展示太多的数据用户是否能够消化
2. 太多的数据我们的浏览器是否能够支持(服务器是否能够支持)

![图片65](/images/图片65.png)

##### 1.6.2 后端分页处理

我们在后端处理查询操作的时候需要做分页的处理。我们首先需要了解MySQL中的分页语句。需要使用到 **`limit`** 关键字。

```sql
# 分页的SQL实现-结合不同的数据库来实现
SELECT * FROM sys_user LIMIT 0,3 # limit 开始的位置,取几条记录
```

搞清楚了分页SQL的实现那么就可以处理Dao中分页逻辑的实现了，先在Dao接口中声明分页的方法。

```java
/**
* Dao层的接口对象
*/
public interface IUserDao {

    /**
    *查询所有的用户信息
    *@return
    */
    public List<SysUser> list(SysUser user);

    /**
    *分页查询的方法
    *@param user 查询条件
    *@param pageNum 页码
    *@param pageSize 每页显示的条数
    *@return
    */
    public List<SysUser> listPage(SysUser user,int pageNum,int pageSize);

    public int save(SysUser user);

    public SysUser findById(int id);

    public int updateById(SysUser user);

    int deleteById(int id);
}
```

然后就是对应的分页查询实现。

```java
@Override
public List<SysUser> listPage(SysUser user, int pageNum, int pageSize) {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
    String sql = "select * from sys_user limit ?,?";
    // 计算 分页开始的位置
    int startNo = (pageNum-1) * pageSize; 
    try {
        // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
        // 此处不会通过驼峰命名法 装换
        // List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser> (SysUser.class));
        List<SysUser> list = queryRunner.query(sql, new ResultSetHandler<List<SysUser>>
                                               () {
            
            @Override
            public List<SysUser> handle(ResultSet resultSet) throws SQLException {
                                                       // 存储返回结果的容器
                List<SysUser> list = new ArrayList<>(); 
                while(resultSet.next()){
                    // 每次循环一次 user 存储一条数据
                    SysUser user = new SysUser(); 
                    user.setId(resultSet.getInt("id")); 
                    user.setUsername(resultSet.getString("username")); 
                    user.setPassword(resultSet.getString("password"));                                     
                    user.setNickname(resultSet.getString("nickname"));
                    user.setRoleId(resultSet.getInt("role_id")); 
                    user.setRolename(resultSet.getString("rolename")); 
                    user.setImg(resultSet.getString("img")); 
                    user.setCreateTime(resultSet.getDate("create_time"));
                    list.add(user); // 把查询的记录封装到了集合容器中
                 }
               return list; // 返回查询的结果
              }
         },startNo,pageSize);
        return list;
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return null;
}
```

可以通过 `main` 方法来测试。

![图片66](/images/图片66.png)

然后针对分页操作的相关数据比较多，我们可以自定义一个 **`PageUtils`** 来封装分页相关的信息。具体如下：

```java
/**
* 分页的工具类
*/
public class PageUtils {

    private String key; // 查询的关键字

    private int pageSize = 5; // 每页显示的条数 
    
    private int pageNum = 1; // 第几页 默认查询第一页 
    
    private int totalCount ; // 总的记录数
    
    private int totalPage ; // 总的页数

    private List<?> list ; // 分页显示的数据

    /**
    *SQL分页中的 开始的位置
    *@return
    */
    public int getStart(){
        return (this.getPageNum() - 1) * this.getPageSize();
    }

    public int getEnd(){
        return this.getPageNum() * this.getPageSize();
    }


    public String getKey() { 
        return key;
    }

    public void setKey(String key) { 
        this.key = key;     
    }

    public int getPageSize() { 
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;           
    }

    public int getPageNum() { 
        return pageNum;
    }

    public void setPageNum(int pageNum) { 
        this.pageNum = pageNum;
    }

    public int getTotalCount() { 
        return totalCount;     
    }

    public void setTotalCount(int totalCount) { 
        this.totalCount = totalCount;                
    }

    public int getTotalPage() { 
        return totalPage;
    }

    public void setTotalPage(int totalPage) { 
        this.totalPage = totalPage;                    
    }

    public List<?> getList() { 
        return list;              
    }

    public void setList(List<?> list) { 
        this.list = list;
    }
}
```

然后在Servlet和Service中实现分页处理。同时调整Dao中的实现代码。

```java
@Override
public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    // 查询分页相关的参数
    String ps = req.getParameter("pageSize");
    String pn = req.getParameter("pageNum");
    // 声明默认的分页参数
    int pageSize = 5; // 默认每页显示5条 
    int pageNum = 1; // 默认第一页 
    if(StringUtils.isNotEmpty(ps)){
        pageSize = Integer.parseInt(ps);
    }

    if(StringUtils.isNotEmpty(pn)){ 
        pageNum = Integer.parseInt(pn);
    }
    PageUtils pageUtils = new PageUtils();
    pageUtils.setPageNum(pageNum); 
    pageUtils.setPageSize(pageSize);

    // 做分页的查询
    service.listPage(pageUtils);

    // 表示查询数据
    // 查询所有的用户数据
    //List<SysUser> list = service.list(null);
    // 把查询的用户数据存储在 request作用域中 
    req.setAttribute("pageUtils",pageUtils);
    // 通过服务端转发的方式跳转页面
    req.getRequestDispatcher("/sys/user/list.jsp").forward(req,  resp);
}
```

然后对应的 **Service** 中，既有查询分页数据也得查询总的记录数。

```java
@Override
public void listPage(PageUtils pageUtils) { 
    List<SysUser> list = dao.listPage(pageUtils); 
    int count = dao.count();
    // 封装分页的数据
    pageUtils.setList(list); 
    pageUtils.setTotalCount(count);
}
```

然后在 **Dao** 中，提供这两个数据操作的支持。

```java
@Override
public List<SysUser> listPage(PageUtils pageUtils) { 
    QueryRunner queryRunner = MyDbUtils.getQueryRunner();
	String sql = "select * from sys_user limit ?,?";
	// 计算 分页开始的位置
	int startNo = pageUtils.getStart(); 
    try {
   		// BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
		// 此处不会通过驼峰命名法 装换
		// List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser>(SysUser.class));
		List<SysUser> list = queryRunner.query(sql, new ResultSetHandler<List<SysUser>>() {


            @Override
            public List<SysUser> handle(ResultSet resultSet) throws SQLException {
                // 存储返回结果的容器
                List<SysUser> list = new ArrayList<>();
                while(resultSet.next()){
                    // 每次循环一次 user 存储一条数据
                    SysUser user = new SysUser(); 
                    user.setId(resultSet.getInt("id")); 
                    user.setUsername(resultSet.getString("username")); 
                    user.setPassword(resultSet.getString("password")); 
                    user.setNickname(resultSet.getString("nickname"));
                    user.setRoleId(resultSet.getInt("role_id")); 
                    user.setRolename(resultSet.getString("rolename")); 
                    user.setImg(resultSet.getString("img")); 
                    user.setCreateTime(resultSet.getDate("create_time"));
                    list.add(user); // 把查询的记录封装到了集合容器中
                }
                return list; // 返回查询的结果
            }
        },startNo,pageUtils.getPageSize()); 
        return list;
    } catch (SQLException throwables) { 
        throwables.printStackTrace();
    }
    return null;
}

@Override
public int count() {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner();
    String sql = "select count(1) from sys_user "; 
    try {
        return queryRunner.query(sql, new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet resultSet) throws SQLException {
                resultSet.next();
                return resultSet.getInt(1);
            }
        });
    } catch (SQLException throwables) { 
        throwables.printStackTrace();
    }
    return 0;
}
```

然后就可以测试分页的数据了：

![图片67](/images/图片67.png)

针对 **`UserServlet`** 中查询分页信息的方法有很多分页处理的逻辑造成整个方法显得臃肿，不是很简洁针对这种情况我们可以把公共代码提取到 **`BaseServlet`** 中。

```java
/**
*系统公共的Servlet
*我们约定客户端提交的请求中的action就是在Servlet要处理的方法的名称
*/
public abstract class BaseServlet extends HttpServlet {

    protected PageUtils pageUtils ;


    /**
    *通过反射的方式调用对象中的Action对应的方法
    *@param req
    *@param resp
    *@throws ServletException
    *@throws IOException
    */ 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        // 获取传递的Action参数
        String action = req.getParameter("action"); 
        try {
            // 获取当前对象对应的Method对象
            Method method = this.getClass()
                .getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 调用方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            // 调用方法执行的时候出现了异常
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        doPost(req,resp);
    }

    // 定义增删改查的基础方法
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        pageUtils = new PageUtils();
        // 查询分页相关的参数
        String ps = req.getParameter("pageSize");
        String pn = req.getParameter("pageNum");
        // 声明默认的分页参数
        int pageSize = 5; // 默认每页显示5条
        int pageNum = 1; // 默认第一页
        if(StringUtils.isNotEmpty(ps)){
            pageSize = Integer.parseInt(ps);
        }

        if(StringUtils.isNotEmpty(pn)){ 
            pageNum = Integer.parseInt(pn);
        }
        pageUtils.setPageNum(pageNum); 
        pageUtils.setPageSize(pageSize);

    };
    public abstract void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) 
        throws Exception;
}
```

然后在业务代码中只需要调用父类中的方法即可：如下，代码整个就非常直观和简洁了。

```java
@Override
public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception { 
    super.list(req,resp); // 调用父类中的方法完成分页数据的处理
	// 做分页的查询
	service.listPage(pageUtils);
	// 把查询的用户数据存储在 request作用域中 
    req.setAttribute("pageUtils",pageUtils);
	// 通过服务端转发的方式跳转页面
	req.getRequestDispatcher("/sys/user/list.jsp").forward(req,  resp);
}
```

##### 1.6.3 前端分页功能

前端分页展示的效果如下：

![图片68](/images/图片68.png)

首页是下面的分页条目。这块我们是通过 **`DataTables`** 插件中的分页栏来实现的。

```html
<div class="row">
    <div class="col-sm-6">
        <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria- live="polite" aria-relevant="all">显示
            1 到 10 项，共 57 项
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
            <ul class="pagination">
                <li class="paginate_button previous disabled" aria-
                    controls="DataTables_Table_0" tabindex="0"
                    id="DataTables_Table_0_previous"><a  href="#">上一页</a></li>
                <li class="paginate_button active" aria-controls="DataTables_Table_0" tabindex="0"><a href="#">1</a>
                </li>
                <li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0"><a href="#">2</a></li>
                <li class="paginate_button " aria-controls="DataTables_Table_0"
                    tabindex="0"><a href="#">3</a></li>
                <li class="paginate_button " aria-controls="DataTables_Table_0"
                    tabindex="0"><a href="#">4</a></li>
                <li class="paginate_button " aria-controls="DataTables_Table_0"
                    tabindex="0"><a href="#">5</a></li>
                <li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0"><a href="#">6</a></li>
                <li class="paginate_button next" aria-controls="DataTables_Table_0"
                    tabindex="0"
                    id="DataTables_Table_0_next"><a  href="#">下一页</a></li>
            </ul>
        </div>
    </div>
</div>
```

然后需要引入 **DataTabls** 的样式文件。

```html
<!-- Data Tables -->
<link href="/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
```

绑定我们对应的分页的数据。

```html
<div class="col-sm-6">
    <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria- live="polite"
         aria-relevant="all">显示 ${pageUtils.start +1 } 到 ${pageUtils.end} 项，共
        ${pageUtils.totalCount} 项
    </div>
</div>
```

![图片69](/images/图片69.png)

然后处理动态分页中的数字页。我们在 **PageUtils **中定义 **`getPageList`** 方法，提供对应的分页数字。

```java
public List<String> getPageList(){ 
    pageList = new ArrayList<>(); 
    totalPage = getTotalPage();
    // 获取总的页数
    if(totalPage < 7){
        // 总共没有7条记录
        for (int i = 1; i <= totalPage; i++) {
            pageList.add(i+"");
        }
    }else{
        if(pageNum == 1 || pageNum == 2 || pageNum == 3){
            pageList = Arrays.asList("1","2","3","...",totalPage+"");
        }else{
            pageList = Arrays.asList((pageNum-2)+"",(pageNum- 1)+"",pageNum+"","..."+totalPage);
        }
    }
    return pageList;
}
```

**代码调整为：**

```java
public List<String> getPageList() {
    pageList = new ArrayList<>();
    totalPage = getTotalPage();
    // 获取总的页数
    if(totalPage < 7){
        // 总共没有7条记录
        for (int i = 1; i <= totalPage; i++) {
            pageList.add(i+"");
        }
    }else{
        // 总共超过7条记录
        if(pageNum == 1 || pageNum == 2 || pageNum == 3){
            pageList = Arrays.asList("1","2","3","...",totalPage+"");
        }else{
            if(pageNum != totalPage) {
                pageList = Arrays.asList((pageNum-2)+"",(pageNum- 1)+"",pageNum+"", "...", totalPage+"");
            } else {
                pageList = Arrays.asList((pageNum-4)+"",(pageNum- 3)+"",(pageNum- 2)+"", "...", totalPage+"");
            }

        }
    }
    return pageList;
}
```

对应的还需要实现 **`getTotalPage`** 方法的逻辑。

```java
/**
*获取总的页数
*@return
*/
public int getTotalPage() {
    // 总的记录数/每页显示的条数
    totalPage = (int)Math.ceil(((double)totalCount)/pageSize); 
    return totalPage;
}
```

有了这个方法的支持。对应的动态分页数字就可以实现了。

```html
<div class="row">
    <div class="col-sm-6">
        <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria- live="polite"
             aria-relevant="all">显示 ${ pageUtils.start + 1 } 到 ${pageUtils.end} 项，共 ${pageUtils.totalCount} 项
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
            <ul class="pagination">
                <li class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_previous">
                    <a href="javascript:void(0)" onclick="goPre()">上一页</a>
                </li>

                <c:forEach items="${requestScope.pageUtils.pageList}" var="page">
                    <c:if test="${page == requestScope.pageUtils.pageNum.toString()}">
                        <li class="paginate_button active" aria-controls="DataTables_Table_0" tabindex="0">
                            <a href="javascript:void(0)" onclick="goPage(${page})">${page}</a>
                        </li>
                    </c:if>
                    <c:if test="${page != requestScope.pageUtils.pageNum.toString()}">
                        <li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
                            <a href="javascript:void(0)" onclick="goPage(${page})">${page}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${requestScope.pageUtils.pageNum == requestScope.pageUtils.totalPage}">
                    <li class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_next">
                        <a href="#">下一页</a>
                    </li>
                </c:if>
                <c:if test="${requestScope.pageUtils.pageNum != requestScope.pageUtils.totalPage}">
                    <li class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_next">
                        <a href="javascript:void(0)" onclick="goNext()">下一页</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
```

要实现具体的分页功能。我们还需要提供提交数据的表单。这块和 **`关键字`** 查询关联起来。

```html
<form action="/sys/userServlet" id="myForm" method="get">
    <div class="input-group">
        <input type="text" placeholder="请输入关键词" class="input-sm form-control">
        <span class="input-group-btn">
            <button type="button" class="btn btn-sm btn-primary"> 搜索</button>
        </span>
        <input type="hidden" name="action" value="list">
        <input type="hidden" id="pageNum" name="pageNum" value="${pageUtils.pageNum}">
        <input type="hidden" id="pageSize" name="pageSize" value="${pageUtils.pageSize}">
    </div>
</form>
```

最后就是提供相关的 js 方法。

```js
// 分页相关的方法
function goPre(){
    // 前一页：当前页-1
    $("#pageNum").val(${pageUtils.pageNum - 1});
    // 提交表单
    $("#myForm").submit();
}

function goPage(page){
    // 前一页：当前页-1
    $("#pageNum").val(page);
    // 提交表单
    $("#myForm").submit();
}

function goNext(){
    // 下一页：当前页+1
    $("#pageNum").val(${pageUtils.pageNum + 1});
    // 提交表单
    $("#myForm").submit();
}
```

搞定分页功能，当然我们还需要添加关键字查询的逻辑。

#### 1.7 带条件查询

在一个基础功能模块中。带条件查询的功能也是非常用必要的。而已是需要结合在分页功能中的。在用户管理中我们也需要来实现这块的功能。在 **`PageUtils`** 中定义看一个key的属性。那么在查询的表单中我们添加一个key的表单域。

```html
<div class="col-sm-3">
    <form action="/sys/userServlet" id="myForm" method="get">
        <div class="input-group">
            <input type="text" name="key" value="${pageUtils.key}" placeholder="请输入关键词" class="input-sm form-control">
            <span class="input-group-btn">
                <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
            </span>
            <input type="hidden" name="action" value="list">
            <input type="hidden" id="pageNum" name="pageNum"
                   value="${pageUtils.pageNum}">
            <input type="hidden" id="pageSize" name="pageSize"
                   value="${pageUtils.pageSize}">
        </div>
    </form>
</div>
```

然后修改 **`PageUtils`** 中的逻辑。添加对 **`key`** 的处理。注意这块我们只需要在 **`BaseServlet`** 中处理即可。

```java
// 定义增删改查的基础方法
public void list(HttpServletRequest req, HttpServletResponse resp) 
    throws Exception{ 
    pageUtils = new PageUtils();
    // 查询分页相关的参数
    String ps = req.getParameter("pageSize"); 
    String pn = req.getParameter("pageNum"); 
    String key = req.getParameter("key");
    // 声明默认的分页参数
    int pageSize = 5; // 默认每页显示5条 
    int pageNum = 1; // 默认第一页 
    if(StringUtils.isNotEmpty(ps)){
        pageSize = Integer.parseInt(ps);
    }
	if(StringUtils.isNotEmpty(pn)){ 
    	pageNum = Integer.parseInt(pn);
    }

	//if(StringUtils.isNotEmpty(key)){
    	// 如果查询条件不为空。那么设置当前页为1 
        //pageNum = 1;
	//}

	pageUtils.setPageNum(pageNum); 
    pageUtils.setPageSize(pageSize); 
    pageUtils.setKey(key);

};
```

最后在 **`UserDaoImpl`** 中添加带条件的查询操作即可。

```java
String sql = "select * from sys_user limit ?,?"; 
if(StringUtils.isNotEmpty(pageUtils.getKey())){
	// 需要带条件查询
	sql = "select * from sys_user where username like '%"+pageUtils.getKey()+"%' or nickname like 		   '%"+pageUtils.getKey()+"%' limit ?,?";
}
```

优化 **count** 方法

```java
@Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from sys_user";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select count(1) from sys_user where username like '%"+pageUtils.getKey()+"%' or nickname like '%"+pageUtils.getKey()+"%'";
        }
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
```

**优化搜索**

```html
<form action="/sys/userServlet" id="myForm" method="get">
    <div class="input-group">
        <input type="text" name="key" value="${requestScope.pageUtils.key}" placeholder="请输入关键词" class="input-sm form-control">
        <span class="input-group-btn">
            <button type="button" class="btn btn-sm btn-primary" onclick="onSearch()">搜索</button>
        </span>
        <input type="hidden" name="action" value="list">
        <input type="hidden" id="pageNum" name="pageNum" value="${requestScope.pageUtils.pageNum}">
        <input type="hidden" id="pageSize" name="pageSize" value="${requestScope.pageUtils.pageSize}">
    </div>
</form>
```

```js
// 搜索
function onSearch() {
	$("#pageNum").val(1);
	$("#myForm").submit();
}
```

具体效果如下：

![图片70](/images/图片70.png)

把分页的公共部分可以提取出来。在更目录下创建一个 **`common/commonPage.jsp`**  页面。

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-sm-6">
        <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria- live="polite"
             aria-relevant="all">显示 ${pageUtils.start +1 } 到 ${pageUtils.end} 项，共
            ${pageUtils.totalCount} 项
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
            <ul class="pagination">
                <li class="paginate_button previous disabled" aria- controls="DataTables_Table_0"
                    tabindex="0" id="DataTables_Table_0_previous">
                    <a href="javascript:void(0)" onclick="goPre()">上一页</a>
                </li>
                <c:forEach items="${requestScope.pageUtils.pageList}" var="page">
                    <c:if test="${page == pageUtils.pageNum}">
                        <li class="paginate_button active" aria- controls="DataTables_Table_0" tabindex="0">
                            <a href="javascript:void(0)"
                               onclick="goPage(${page})">${page}</a></li>
                    </c:if>
                    <c:if test="${page != pageUtils.pageNum}">
                        <li class="paginate_button " aria-controls="DataTables_Table_0"
                            tabindex="0">
                            <a href="javascript:void(0)" onclick="goPage(${page})">${page}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${pageUtils.pageNum == pageUtils.totalPage}">
                    <li class="paginate_button next disabled" aria- controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_next">
                        <a href="#">下一页</a>
                    </li>
                </c:if>
                <c:if test="${pageUtils.pageNum != pageUtils.totalPage}">
                    <li class="paginate_button next" aria-controls="DataTables_Table_0"
                        tabindex="0"
                        id="DataTables_Table_0_next">
                        <a href="javascript:void(0)" onclick="goNext()">下一页</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
```

然后在 **`/sys/user/list.jsp`** 中通过 **`include`** 指令来引入这个分页的公共页面。

```jsp
<%@include file="/common/commonPage.jsp"%>
```

这样就能达到简化和减少冗余代码的效果。

### 2.角色功能

接下来我们可以完成角色管理的增删改查操作。

#### 2.1 Bean对象

创建 **`sys_role`** 对应的实体对象 **`SysRole`**

```java
@Data
public class SysRole {

    private Integer id;

    private String name;

    private String notes;

    private Date createTime;
}
```

#### 2.2 Dao层

现在我们就可以在Dao层创建涉及相关的数据库操作的方法。

```java
public interface IRoleDao {
    /**
    * 查询所有的用户信息
    *@return
    */
    public List<SysRole> list(SysRole entity);

    /**
    *分页查询的方法
    *@param pageUtils 分页数据
    *@return
    */
    public List<SysRole> listPage(PageUtils pageUtils);

    public int save(SysRole entity);

    public SysRole findById(int id);

    public int updateById(SysRole entity);

    int deleteById(int id);

    int count(PageUtils pageUtils);
}
```

然后是具体的实现。

```java
public class RoleDaoImpl implements IRoleDao { 
    @Override
    public List<SysRole> list(SysRole entity) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_role "; try {
            List<SysRole> list = queryRunner.query(sql, new
                                                   ResultSetHandler<List<SysRole>>() {
                @Override
             	public List<SysRole> handle(ResultSet resultSet) throws SQLException {
                  	// 存储返回结果的容器
                    List<SysRole> list = new ArrayList<>(); 
                    while(resultSet.next()){
                    	// 每次循环一次 user 存储一条数据
                        SysRole entity = new SysRole(); 
                        entity.setId(resultSet.getInt("id")); 
                        entity.setName(resultSet.getString("name")); 
                        entity.setNotes(resultSet.getString("notes")); 
                        entity.setCreateTime(resultSet.getDate("create_time"));
                        list.add(entity); // 把查询的记录封装到了集合容器中
                    }
                	return list; // 返回查询的结果
                 }
            });
            return list;
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SysRole> listPage(PageUtils pageUtils) { 
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
        String sql = "select * from sys_role limit ?,?"; 
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
        	// 需要带条件查询
        	sql = "select * from sys_role where name like '%"+pageUtils.getKey()+"%' or notes like 	
                '%"+pageUtils.getKey()+"%' limit ?,?";
    	}

        // 计算 分页开始的位置
        int startNo = pageUtils.getStart(); try {
        // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
        // 此处不会通过驼峰命名法 装换
        // List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser>(SysUser.class));
        List<SysRole> list = queryRunner.query(sql, new ResultSetHandler<List<SysRole>>() {
            @Override
            public List<SysRole> handle(ResultSet resultSet) throws SQLException {
                // 存储返回结果的容器
                List<SysRole> list = new ArrayList<>();
                while(resultSet.next()){
                    // 每次循环一次 user 存储一条数据
                    SysRole entity = new SysRole(); 
                    entity.setId(resultSet.getInt("id")); 
                    entity.setName(resultSet.getString("name")); 
                    entity.setNotes(resultSet.getString("notes")); 
                    entity.setCreateTime(resultSet.getDate("create_time")); 
                    list.add(entity); // 把查询的记录封装到了集合容器中
                }
                return list; // 返回查询的结果
            }
        },startNo,pageUtils.getPageSize());
            return list;
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(SysRole entity) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
        String sql = "insert into sys_role(name,notes) values(?,?)"; 
        try {
            return queryRunner.update(sql,entity.getName(),entity.getNotes());
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public SysRole findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_role where id = ? "; 
        try {
            return queryRunner.query(sql, new ResultSetHandler<SysRole>() {
                @Override
                public SysRole handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if(resultSet.next()){
                        // 每次循环一次 user 存储一条数据
                        SysRole entity = new SysRole(); 
                        entity.setId(resultSet.getInt("id")); 
                        entity.setName(resultSet.getString("name")); 
                        entity.setNotes(resultSet.getString("notes")); 
                        entity.setCreateTime(resultSet.getDate("create_time")); 
                        return entity;
                    }
                    return null; // 返回查询的结果
                }
            },id);

        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateById(SysRole entity) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update sys_role set name = ? ,notes=? where id = ?";
        try { 
            return queryRunner.update(sql,entity.getName(),entity.getNotes(),entity.getId());
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from sys_role where id = ?"; try {
            return queryRunner.update(sql,id);
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
        String sql = "select count(1) from sys_role "; 
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            sql = "select count(1) from sys_role where username like
                '%"+pageUtils.getKey()+"%' or nickname like '%"+pageUtils.getKey()+"%' ";
        }
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet resultSet) throws SQLException { 
                    resultSet.next();
                    return resultSet.getInt(1);
               }
            });
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return 0;
    }
}
```

#### 2.3 Service层

添加角色管理的 **`Service`** 相关的代码。

```java
public interface IRoleService {

    public List<SysRole> list(SysRole entity);

    public int save(SysRole entity);

    public SysRole findById(int id);

    public int updateById(SysRole entity);

    int deleteById(int id);

    void listPage(PageUtils pageUtils);

    int count(PageUtils pageUtils);
}
```

具体接口对应的实现类。

```java
public class RoleServiceImpl implements IRoleService {

    private IRoleDao dao = new RoleDaoImpl();

    @Override
    public List<SysRole> list(SysRole entity) { 
        return dao.list(entity);
                                              
    }

    @Override
    public int save(SysRole entity) { 
        return dao.save(entity);
                                    
    }

    @Override
    public SysRole findById(int id) {
        return dao.findById(id);
    }

    @Override
    public int updateById(SysRole entity) { 
        return dao.updateById(entity);
                                          
    }

    @Override
    public int deleteById(int id) { 
        return dao.deleteById(id);
                                  
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<SysRole> list = dao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = dao.count(pageUtils);
        // 封装分页的数据 
        pageUtils.setList(list); 
        pageUtils.setTotalCount(count);
    }

    @Override
    public int count(PageUtils pageUtils) { 
        return dao.count(pageUtils);
    }
}
```

#### 2.4 Servlet层

**`RoleServlet`** 需要继承前面定义的 **`BaseServlet`** ，然后重写定义的抽象方法。同时要重写 **list** 方法。具体如下：

```java
@WebServlet(name = "roleServlet",urlPatterns = {"/sys/roleServlet"}) 
public class RoleServlet extends BaseServlet{

    private IRoleService service = new RoleServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {
        super.list(req, resp); // 封装了分页的相关的操作
        // TODO 写我们自己的查询处理 
        service.listPage(pageUtils); 
        req.setAttribute(Constant.LIST_PAGE_UTILS,pageUtils);
        // 页面跳转
        req.getRequestDispatcher("/sys/role/list.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }
}
```

#### 2.5 角色查询

展示角色数据，那么我们需要做的操作：

- 修改 **`main.jsp`** 中的跳转地址
- 修改 **`/sys/role/list.jsp`** 中的访问地址和table数据

![图片71](/images/图片71.png)

![图片72](/images/图片72.png)

#### 2.6 添加和更新

添加和删除功能是类似的。我们一块来实现，在Servlet中完善进入添加更新页面的方法和保存和更新的方法的逻辑。

```java
@Override
public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws
    Exception {
    // 如果是更新。需要查询出当前的信息
    String id = req.getParameter("id"); 
    if(StringUtils.isNotEmpty(id)){
        // 说明是更新
        SysRole entity = service.findById(Integer.parseInt(id)); 
        req.setAttribute("entity",entity);
    }
    req.getRequestDispatcher("/sys/role/addOrUpdate.jsp").forward(req,resp);
}

@Override
public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    // 获取提交的数据
    String id = req.getParameter("id"); 
    String name = req.getParameter("name"); 
    String notes = req.getParameter("notes"); 
    
    SysRole entity = new SysRole(); 
    entity.setNotes(notes); 
    entity.setName(name); 
    if(StringUtils.isNotEmpty(id)){
        // 更新
        entity.setId(Integer.parseInt(id));
        service.updateById(entity);
    }else {
        // 保存数据
        service.save(entity);
    }
    // 做重定向查询
    resp.sendRedirect("/sys/roleServlet?action=list");

}
```

然后就是对应的页面处理。

```html
<form class="form-horizontal m-t" id="signupForm" action="/sys/roleServlet? action=saveOrUpdate" method="post">
    <input type="hidden" name="id" value="${entity.id}">
    <div class="form-group">
        <label class="col-sm-3 control-label">名称：</label>
        <div class="col-sm-8">
            <input id="name" name="name" class="form-control" value="${entity.name}" type="text" aria-required="true" aria-invalid="true" class="error">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">描述：</label>
        <div class="col-sm-8">
            <input id="notes" name="notes" class="form-control" value="${entity.notes}" type="textarea" aria-required="true" aria-invalid="false"
                   class="valid">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-8 col-sm-offset-3">
            <button class="btn btn-default" onclick="resetPage()" type="button">取消
            </button>
            <button class="btn btn-primary" type="submit">提交</button>
        </div>
    </div>
</form>
```

这样就OK了。

![图片73](/images/图片73.png)

#### 2.7 删除角色

删除角色我们需要做一个判断。也就是已经分配给用户的角色是不能被删除的。所以删除操作的时候我们需要添加这样一个判断。

```java
@Override
public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    // 删除角色信息
    String id = req.getParameter("id");
    // 删除角色信息 我们需要做校验 如果该角色已经分配给了用户。那么这个角色就不能被删除 
    boolean flag = service.checkRoleDispatch(Integer.parseInt(id)); 
    PrintWriter writer = resp.getWriter();
    if(flag){
        // 表示没有被分配，可以删除
        service.deleteById(Integer.parseInt(id));
        writer.write("ok");
    }else{
        // 表示不能被删除
        writer.write("error");
    }
    writer.flush();
    writer.close();
}
```

service中的处理。

```java
@Override
public boolean checkRoleDispatch(int roleId) {
    SysUser entity = new SysUser(); 
    entity.setRoleId(roleId);
    return userService.list(entity).size() == 0 ?true:false;
}
```

这个是在 **`UserService`** 的基础上做的扩展，实现了方法的复用。

![图片74](/images/图片74.png)

在前端页面的处理上。加了条件判断。

```js
function removeData(id) { 
    swal({
        title: "您确定要删除这条信息吗",
        text: "删除后将无法恢复，请谨慎操作！", 
        type: "warning",
    	showCancelButton: true, 
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除", 
        closeOnConfirm: false
}, function () {

        $.get("/sys/roleServlet?action=remove&id=" + id, function (msg) { 
            if(msg === 'ok'){
            // 表示删除成功
            // 再发起一个查询的操作
            window.location.href = "/sys/roleServlet?action=list"
            }else{
                // 表示不能被删除
                swal("删除失败！", "该角色已经被分配， 不能删除!!!。", "warning");
            }
        })
    })
}
```

![图片75](/images/图片75.png)

### 3.菜单功能

#### 3.1 查询功能

系统左侧菜单栏我们需要做成动态的。那么就需要维护相关的菜单数据。所以设计了  **`sys_menu`** 这张表。具体的实现。如下，先定义 **`SysMenu`** 这个实体。

```java
@Data
public class SysMenu {

	private Integer id; 
    private String name; 
    private String url; 
    private Integer parentId; 
    private int seq;
	private Date createTime;
}
```

然后维护 **`DAO`** , **`Service`** 和 **`Servlet`** 的逻辑。

```java
@WebServlet(name = "menuServlet",urlPatterns = {"/sys/menuServlet"}) 
public class MenuServlet extends BaseServlet{

    private IMenuService service = new MenuServiceImpl();

    /**
    *菜单功能不做分页
    *@param req
    *@param resp
    *@throws Exception
    */
    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception
    {
        List<SysMenu> list = service.list(null);
        req.setAttribute("list",list); 
        req.getRequestDispatcher("/sys/menu/list.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }
}
```

然后页面的展示处理。注意 **`main.jsp`** 的菜单地址和 **`/sys/menu/list.jsp`** 页面的调整，我们在此处没有做分页的处理操作。

![图片76](/images/图片76.png)

#### 3.2 添加和更新

添加和更新处理很类似。我们一并的实现。添加和更新我们已经在 **`用户管理`** 和 **`角色管理`** 中已经实现了。所以在此处的难度就降低了很多。步骤一样。

1. 进入登录页面：需要准备相关的数据(根据Id查询信息和查询所有的父菜单信息)
2. 提交表单数据：后端服务获取数据后做添加和更新的操作

在此处需要注意的是：父菜单分配功能，需要使用到下拉菜单。

![图片77](/images/图片77.png)

对应的代码。

```html
<div class="form-group">
    <label class="col-sm-3 control-label">父菜单：</label>
    <div class="col-sm-8">
        <select class="form-control m-b" name="parentId">
            <option value="-1">---本身就是父菜单---</option>
            <c:forEach items="${parents}" var="parent">
                <option value="${parent.id}" ${parent.id ==
                        entity.parentId?'selected':''}>${parent.name}</option>
            </c:forEach>
        </select>
    </div>
</div>
```

表单的提交功能。后端处理数据。

```java
@Override
public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    // 获取表单提交的数据
    String id = req.getParameter("id"); 
    String name = req.getParameter("name");
    String url = req.getParameter("url"); 
    String seq = req.getParameter("seq");
    String parentId = req.getParameter("parentId"); 
    SysMenu menu = new SysMenu(); 
    menu.setName(name);
    menu.setUrl(url); 
    if(StringUtils.isNotEmpty(seq)){
        menu.setSeq(Integer.parseInt(seq));
    }
    if(StringUtils.isNotEmpty(parentId)){ 
        menu.setParentId(Integer.parseInt(parentId));
    }
    if(StringUtils.isNotEmpty(id)){
        // 更新 
        menu.setId(Integer.parseInt(id)); 
        service.updateById(menu);
    }else{
        // 添加
        service.save(menu);
    }
    resp.sendRedirect("/sys/menuServlet?action=list");
}
```

功能搞定。

#### 3.3 菜单数据展示

菜单数据有父子菜单的关系。所以在展示数据的时候需要体现这种关系。我们可以通过双重循环的方式来实现。效果如下：

![图片78](/images/图片78.png)

同时我们可以通过序号来控制菜单的显示的顺序。关键是在查询的时候通过 **`seq`** 升序查询。

![图片79](/images/图片79.png)

#### 3.4 删除菜单

删除菜单本身很简单。但是我们要考虑父子菜单的关系和菜单被分配给角色的情况。那么有些情况是不能被删除的。所以我们在Servlet中需要做相关的判断校验。

```java
@Override
public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception { 
    int id = Integer.parseInt(req.getParameter("id"));                                      
    // 删除菜单的判断                                                          
    // 1.菜单如果分配给了角色就不能被删除
    boolean flag = service.isDispatcher(id);
    String msg = "ok";
    if(flag){
        // 表示已经被分配了
        msg = "error";
    }else{
        SysMenu entity = service.findById(id);
        // 2.需要删除的菜单是父菜单 \
        if(entity.getParentId() == -1){
            // 有子菜单的父菜单不能被删除 -- 判断是否有子菜单
            flag = service.haveSubMenu(id);
            if(flag){
                // 有子菜单
                msg = "error";
            }else{
                // 父菜单没有子菜单 可以删除
                service.deleteById(id);
            }
        }else{
            // 3.子菜单 可以被删除
            service.deleteById(id);
        }
    }
    PrintWriter writer = resp.getWriter(); 
    writer.write(msg);
    writer.flush();
}
```

### 4.动态绑定

实现用户和角色的绑定以及角色和菜单的绑定。实现整个系统动态功能分配管理的效果。

#### 4.1 角色和菜单

角色和菜单是多对多的关联关系。所以我们通过 **`sys_role_menu`** 来维护他们之间的关联关系。我们在更新角色信息的时候来维护菜单信息。

![图片80](/images/图片80.png)

**dao层**

```java
```



需要注意的地方，在jsp页面中展示数据注意样式。

![图片81](/images/图片81.png)

在展示数据的时候，我们在进入更新页面前，需要对菜单数据做处理。

1. 查询所以的菜单信息

2. 对当前角色具有的菜单需要标识

![图片82](/images/图片82.png)

保存更新数据的逻辑。针对菜单我们的步骤是：

1. 先删除该角色的所有菜单

2. 新增分配的菜单信息

![图片83](/images/图片83.png)

#### 4.2 用户和角色

用户和角色是一对一的关联关系，那么这块我们就可以在添加和更新用户的时候直接分配角色信息，这块我们操作的内容：

1. 进入更新/添加界面前需要查询所有的角色信息

2. 在更新/添加界面中我们需要添加一个下拉菜单来处理分配功能

3. 表单数据提交到后台Servlet中我们需要处理角色相关的数据。同时调整前面写的JDBC的方法

![图片84](/images/图片84.png)

然后对应的表单代码。

```html
<div class="form-group">
    <label class="col-sm-3 control-label">分配角色：</label>
    <div class="col-sm-8">
        <select class="form-control m-b" name="roleId">
            <c:forEach items="${requestScope.roles}" var="role">
                <option value="${role.id}" ${role.id == entity.roleId?'selected':''}>${role.name}</option>
            </c:forEach>
        </select>
    </div>
</div>
```

后端的处理代码。

![图片85](/images/图片85.png)

![图片86](/images/图片86.png)

### 5.登录功能

#### 5.1 认证实现

首先完成最基础的登录功能，也就是在登录页面通过表单提交 **`账号`** 和 **`密码`** 到Servlet中。做相关的校验。给出不同的反应。

![图片87](/images/图片87.png)

然后对应的Servlet中的处理逻辑。

```java
@WebServlet(name = "loginServlet",urlPatterns = {"/sys/loginServlet"}) 
public class LoginServlet extends HttpServlet {

    private IUserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 做登录认证的操作
        String userName = req.getParameter("userName"); 
        String password = req.getParameter("password");
        // 根据账号去数据库查询记录
        SysUser user = service.findByName(userName); 
        if(user == null ){
            // 说明账号不存在
            req.setAttribute("msg","登录失败!账号不存在"); 
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }else if(!password.equals(user.getPassword())){
            // 说明密码不正确
            req.setAttribute("msg","登录失败!密码错误..."); 
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }else{
            // 说明登录成功
            // 那么我们需要记录当前登录的用户
            HttpSession session = req.getSession(); 
            user.setPassword(null); // 记录的账号把密码置空。安全考虑 
            session.setAttribute(Constant.LOGIN_USER,user); 
            resp.sendRedirect("/main.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
```

#### 5.2 认证过滤器

我们加了认证的操作后就不应该可以通过地址栏直接访问后端的功能了。所以需要添加过滤器来做认证的校验。

```java
/**
*认证的过滤器：拦截所有的请求
*1.判断当前是否是登录状态
*2.请求的资源是否可以匿名访问
*3.都不满足就跳转会登录页面
*/
@WebFilter(filterName = "authName",urlPatterns = {"/*"}) 
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse
                         servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 对封装请求和响应的对象做向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest; 
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取拦截的请求的访问地址
        String requestURI = request.getRequestURI(); 
        if(checkAccssible(requestURI)){
            // 在没有登录的情况下可以访问的资源 登录页面 处理登录逻辑的Servlet 还有各种 js css img
                // 直接放过
                filterChain.doFilter(servletRequest,servletResponse);
        }else{
            // 访问的是需要登录后才能访问的资源
            HttpSession session = request.getSession();
            Object loginUser = session.getAttribute(Constant.LOGIN_USER); 
            if(loginUser != null){
                // 是登录的状态
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                // 说明不是登录的状态
                request.setAttribute("msg","请先登录!!!"); 
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }

    }

    private boolean checkAccssible(String requestURI){ 
        List<String > urls =
        Arrays.asList("login.jsp","loginServlet",".css","/js/",".png",".jpg"); 
        for (String url : urls) {
            if(requestURI.contains(url)){
                return true;                      
            }
        }                                           
        return false;                                      
    }
}
```

#### 5.3 安全退出

登录成功后我们需要安全的退出。那么就需要删除登录成功保存在 **`Session`** 中的认证凭证信息。

```java
@WebServlet(name = "logoutServlet",urlPatterns = "/sys/logoutServlet") 
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过session注销
        HttpSession session = req.getSession(); 
        session.invalidate(); // 注销的操作
        //跳转会登录页面
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
        ServletException, IOException { 
        	this.doGet(req,resp);
      	}
}
```

然后 **`main.jsp`** 中的 **`安全退出`** 按钮点击的时候访问 **`/sys/logoutServlet`** 即可。

![图片88](/images/图片88.png)

### 6.动态菜单

有了前面基础内容的铺垫我们就可以实现不同的用户基于不同的角色加载不同的菜单功能。那么在登录成功后需要查询当前登录用户具有的菜单信息。

![图片89](/images/图片89.png)

然后就是在 **`main.jsp`** 中动态加载菜单。注意不要完了 **`jstl`** 的标签库。

```html
<c:forEach items="${sessionScope.loginMenus}" var="parent">
    <c:if test="${parent.parentId == -1}" >
        <li>
            <a href="#">
                <i class="fa fa-home"></i>
                <span class="nav-label">${parent.name}</span>
                <span class="fa arrow"></span>
            </a>
            <ul class="nav nav-second-level">
                <c:forEach items="${sessionScope.loginMenus}" var="sub">
                    <c:if test="${sub.parentId == parent.id}">
                        <li>
                            <a class="J_menuItem" href="${sub.url}" data-index="0">${sub.name}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </li>
    </c:if>
</c:forEach>
```

显示的效果如下：

![图片90](/images/图片90.png)

### 7.首页小功能

左上角显示当前登录用户信息及头像。

```html
<div class="dropdown profile-element">
    <c:if test="${ not empty sessionScope.loginUser.img}">
        <span><img alt="image" class="img-circle" src="/sys/downloadServlet? fileName=${sessionScope.loginUser.img}" width="64" height="64" /></span>
    </c:if>
    <c:if test="${ empty sessionScope.loginUser.img}">
        <span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span>
    </c:if>
    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
        <span class="clear">
            <span class="block m-t-xs"><strong class="font- bold">${sessionScope.loginUser.username}</strong></span>
            <span class="text-muted text-xs block">${sessionScope.loginUser.nickname}<b class="caret"></b></span>
        </span>
    </a>
    <ul class="dropdown-menu animated fadeInRight m-t-xs">
        <li><a class="J_menuItem" href="form_avatar.html">修改头像</a></li>
        <li><a class="J_menuItem" href="profile.html">个人资料</a></li>
        <li><a class="J_menuItem" href="contacts.html">修改密码</a></li>
        <li class="divider"></li>
        <li><a href="/sys/logoutServlet">安全退出</a></li>
    </ul>
</div>
```

效果：

![图片91](/images/图片91.png)

![图片92](/images/图片92.png)

## 四、基础功能

### 1.院系管理

维护院系的基础数据。包括CRUD(增删改查)的操作。首先定义对应的 **`Bean`**

```java
@Data
public class Depart {

	private Integer id; 
    private String name; 
    private String notes; 
    private Date createTime;
}
```

然后创建对应的 **`Dao`** 接口，在接口中什么 **`CRUD`** 的基础方法。

```java
public interface IDepartDao {
    /**
    *分页查询的方法
    *@param pageUtils 分页数据
    *@return
    */
    public List<Depart> listPage(PageUtils pageUtils);

    int count(PageUtils pageUtils);

    public int save(Depart entity);

    public Depart findById(int id);

    public int updateById(Depart entity);

    int deleteById(int id);
}
```

然后创建对应的 **`Dao`** 的实现类，并实现接口中定义的相关方法。

```java
public class DepartDaoImpl implements IDepartDao {

    @Override
    public List<Depart> listPage(PageUtils pageUtils) { 
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_depart limit ?,?"; 
        if(StringUtils.isNotEmpty(pageUtils.getKey())){                         
            sql = "select * from t_depart where name like '%"+pageUtils.getKey()+"%' or
                notes like '%"+pageUtils.getKey()+"%' limit ?,? ";                            
        }
        // 计算 分页开始的位置                         
        int startNo = pageUtils.getStart(); 
        try {             
            List<Depart> list = queryRunner.query(sql, new ResultSetHandler<List<Depart>>() {               
                @Override                            
                public List<Depart> handle(ResultSet resultSet) throws SQLException {                       
                    // 存储返回结果的容器                            
                    List<Depart> list = new ArrayList<>(); 
                    while(resultSet.next()){
                                                                      
                        // 每次循环一次 user 存储一条数据                       
                        Depart entity = new Depart(); 
                        entity.setId(resultSet.getInt("id"));
                        entity.setName(resultSet.getString("name")); 
                        entity.setNotes(resultSet.getString("notes")); 
                        entity.setCreateTime(resultSet.getDate("create_time"));
                        list.add(entity); // 把查询的记录封装到了集合容器中                                       
                    }                                       
                    return list; // 返回查询的结果                                      
                }                                        
            },startNo,pageUtils.getPageSize()); 
            return list;                                   
        } catch (SQLException throwables) { 
            throwables.printStackTrace();                                                    
        }                                        
        return null;                                         
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
        String sql = "select count(1) from t_depart "; 
        if(StringUtils.isNotEmpty(pageUtils.getKey())){
            sql = "select count(1) from t_depart where name like '%"+pageUtils.getKey()+"%' or 
                notes like '%"+pageUtils.getKey()+"%' ";
        }
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() { 
                @Override
                public Integer handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            });
        } catch (SQLException throwables) { 
            throwables.printStackTrace();                
        }
        return 0;
    }

    @Override
    public int save(Depart entity) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
        String sql = "insert into t_depart(name,notes)values(?,?) "; 
        try {
            return queryRunner.update(sql,entity.getName(),entity.getNotes());
        } catch (SQLException throwables) { 
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Depart findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner(); 
        String sql = "select * from t_depart where id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Depart>() { 
                @Override
                public Depart handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if(resultSet.next()){
                        // 每次循环一次 user 存储一条数据
                        Depart entity = new Depart();
                        entity.setId(resultSet.getInt("id")); 
                        entity.setName(resultSet.getString("name")); 
                        entity.setNotes(resultSet.getString("notes")); 
                        entity.setCreateTime(resultSet.getDate("create_time")); 
                        return entity;
                    }
                    return null; // 返回查询的结果
                }
            },id);
        } catch (SQLException throwables) { 
            throwables.printStackTrace();

        }
        return null;
    }                
                                     
    @Override                       
    public int updateById(Depart entity) {         
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();           
        String sql = "update t_depart set name = ? , notes = ? where id = ?";           
        try { 
            return            
            queryRunner.update(sql,entity.getName(),entity.getNotes(),entity.getId());                
            } catch (SQLException throwables) { 
            throwables.printStackTrace();                                                       
        }                   
        return 0;                      
    }
                                     
    @Override               
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_depart where id = ?"; 
        try {                   
            return queryRunner.update(sql,id);                
        } catch (SQLException throwables) { 
            throwables.printStackTrace();                                                   
        }                    
        return 0;                
    }                           
}
```

然后就可以 **`Service`** 层的处理了。

```java
public interface IDepartService {
    public void listPage(PageUtils pageUtils);

    int count(PageUtils pageUtils);

    public int save(Depart entity);

    public Depart findById(int id);

    public int updateById(Depart entity);

    int deleteById(int id);
}
```

在Service的接口实现类中要注意分页数据查询的处理。其他都是直接调用Dao中的方法处理。

```java
public class DepartServiceImpl implements IDepartService {

    private IDepartDao dao = new DepartDaoImpl();

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<Depart> list = dao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = dao.count(pageUtils);
        // 封装分页的数据 
        pageUtils.setList(list); 
        pageUtils.setTotalCount(count);
    }

    @Override
    public int count(PageUtils pageUtils) { 
        return dao.count(pageUtils);             
    }

    @Override
    public int save(Depart entity) { 
        return dao.save(entity);
    }

    @Override
    public Depart findById(int id) { 
        return dao.findById(id);
    }

    @Override
    public int updateById(Depart entity) { 
        return dao.updateById(entity);
    }

    @Override
    public int deleteById(int id) { 
        return dao.deleteById(id);
    }
}

```

然后就是 **`DepartServlet`** 中针对 **`CRUD`** 操作需要涉及的接口方法的实现。

```java
@WebServlet(name = "departServlet",urlPatterns = {"/book/departServlet"}) 
public class DepartServlet extends BaseServlet {

	private IDepartService service = new DepartServiceImpl();

	@Override
	public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		super.list(req, resp); 
        service.listPage(pageUtils);
		req.setAttribute(Constant.LIST_PAGE_UTILS,pageUtils);
		// 页面跳转
		req.getRequestDispatcher("/book/depart/list.jsp").forward(req,resp);
	}

	@Override
	public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp)
	throws Exception {
		String id = req.getParameter("id"); 
        if(StringUtils.isNotEmpty(id)){
			// 说明是更新操作
			Depart entity = service.findById(Integer.parseInt(id)); 
            req.setAttribute("entity",entity);
		}
		req.getRequestDispatcher("/book/depart/addOrUpdate.jsp").forward(req,resp);
	}

	@Override
	public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws
	Exception {
		// 接收表单提交的数据
		String id = req.getParameter("id"); 
        String name = req.getParameter("name"); 
        String notes = req.getParameter("notes"); 
        Depart depart = new Depart(); 
        depart.setName(name);
        depart.setNotes(notes); 
        if(StringUtils.isNotEmpty(id)){
			// 更新
			depart.setId(Integer.parseInt(id)); 
            service.updateById(depart);
		}else{
			// 保存数据
			service.save(depart);
		}
		// 更新或者添加后需要重新查询相关的数据
		resp.sendRedirect("/book/departServlet?action=list");
	}

	@Override
	public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id"); 
        service.deleteById(Integer.parseInt(id));
   		PrintWriter writer = resp.getWriter();
		writer.write("ok"); 
        writer.flush();
	}

	@Override
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
	}
}
```

对应页面的处理操作，效果如下：

![图片93](/images/图片93.png)

更新和添加效果。

![图片94](/images/图片94.png)

### 2.班级管理

班级管理的功能和上面的院系功能很类似。只是多了一个所属的院系关系，笔记我们就重点突出这块。

![图片95](/images/图片95.png)

在页面处理中需要多一个下拉菜单。分配院系功能。

![图片96](/images/图片96.png)

具体的实现效果如下：

![图片97](/images/图片97.png)

![图片98](/images/图片98.png)

### 3.学生管理

学生管理是一个非常基础的 **`CRUD`** 操作，需要完成基础的 **`bean`** , **`dao`** , **`service`** , **`servlet`** 以及前端 jsp 页面，效果如下：

![图片99](/images/图片99.png)

添加和更新的效果。

![图片100](/images/图片100.png)

基本的代码就不贴了。列举需要注意的地方。

1. ajax异步获取班级信息，乱码的问题，需要在响应数据之前设置

   `resp.setContentType("application/json;charset=utf-8");`

2. 在更新数据回写班级信息的时候。我们需要在页面加载完成的相关事件中添加对应的逻辑
3. 在添加数据的时候需要同步添加账号，密码默认为 **`123`**
4. 在更新数据的还是需要同步的更新账号数据
5. 在删除学生信息的同时需要删除账号信息
6. 注意看日志报错信息，学会排查问题

#### 3.1 bean

```java
package com.huangpw.book.bean;

import lombok.Data;
import java.util.Date;

@Data
public class Student {
    private Integer id;
    private Integer account;
    private String stuno;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    private String talephone;
    private String address;
    private String wechat;
    private Integer classId;
    private String classname;
    private Integer departId;
    private String departname;
    private Date createTime;

}
```

#### 3.2 dao

**IStudentDao**

```java
package com.huangpw.book.dao;

import com.huangpw.book.bean.Student;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IStudentDao {

    public List<Student> list(Student student);

    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public List<Student> listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Student student);

    public Student findById(int id);

    public int updateById(Student student);

    public int deleteById(int id);
}
```

**StudentDaoImpl**

```java
package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.Depart;
import com.huangpw.book.bean.Student;
import com.huangpw.book.dao.IStudentDao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public List<Student> list(Student student) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_student";
        try {
            List<Student> list = queryRunner.query(sql, new ResultSetHandler<List<Student>>() {
                @Override
                public List<Student> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Student> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setAccount(resultSet.getInt("account"));
                        student.setStuno(resultSet.getString("stuno"));
                        student.setName(resultSet.getString("name"));
                        student.setAge(resultSet.getInt("age"));
                        student.setGender(resultSet.getString("gender"));
                        student.setEmail(resultSet.getString("email"));
                        student.setTalephone(resultSet.getString("talephone"));
                        student.setAddress(resultSet.getString("address"));
                        student.setWechat(resultSet.getString("wechat"));
                        student.setClassId(resultSet.getInt("class_id"));
                        student.setClassname(resultSet.getString("classname"));
                        student.setDepartId(resultSet.getInt("depart_id"));
                        student.setDepartname(resultSet.getString("departname"));
                        student.setCreateTime(resultSet.getDate("create_time"));
                        list.add(student); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            });
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Student> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_student limit ?,?";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select * from t_student where name like '%" + pageUtils.getKey() + "%' or stuno like '%" + pageUtils.getKey() + "%' limit ?,? ";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            List<Student> list = queryRunner.query(sql, new ResultSetHandler<List<Student>>() {
                @Override
                public List<Student> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Student> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setAccount(resultSet.getInt("account"));
                        student.setStuno(resultSet.getString("stuno"));
                        student.setName(resultSet.getString("name"));
                        student.setAge(resultSet.getInt("age"));
                        student.setGender(resultSet.getString("gender"));
                        student.setEmail(resultSet.getString("email"));
                        student.setTalephone(resultSet.getString("talephone"));
                        student.setAddress(resultSet.getString("address"));
                        student.setWechat(resultSet.getString("wechat"));
                        student.setClassId(resultSet.getInt("class_id"));
                        student.setClassname(resultSet.getString("classname"));
                        student.setDepartId(resultSet.getInt("depart_id"));
                        student.setDepartname(resultSet.getString("departname"));
                        student.setCreateTime(resultSet.getDate("create_time"));
                        list.add(student); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            }, startNo, pageUtils.getPageSize());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from t_student ";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select count(1) from t_student where name like '%" + pageUtils.getKey() + "%' or stuno like '%" + pageUtils.getKey() + "%' ";
        }
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int save(Student student) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_student(account,name,stuno,age,gender,talephone,email,wechat,address,class_id,classname,depart_id,departname)values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            return queryRunner.update(sql, student.getAccount(), student.getName(), student.getStuno(), student.getAge(), student.getGender(), student.getTalephone(), student.getEmail(), student.getWechat(), student.getAddress(), student.getClassId(), student.getClassname(), student.getDepartId(), student.getDepartname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_student where id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Student>() {
                @Override
                public Student handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setAccount(resultSet.getInt("account"));
                        student.setStuno(resultSet.getString("stuno"));
                        student.setName(resultSet.getString("name"));
                        student.setAge(resultSet.getInt("age"));
                        student.setGender(resultSet.getString("gender"));
                        student.setEmail(resultSet.getString("email"));
                        student.setTalephone(resultSet.getString("talephone"));
                        student.setAddress(resultSet.getString("address"));
                        student.setWechat(resultSet.getString("wechat"));
                        student.setClassId(resultSet.getInt("class_id"));
                        student.setClassname(resultSet.getString("classname"));
                        student.setDepartId(resultSet.getInt("depart_id"));
                        student.setDepartname(resultSet.getString("departname"));
                        student.setCreateTime(resultSet.getDate("create_time"));
                        return student;
                    }
                    return null; // 返回查询的结果
                }
            }, id);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public int updateById(Student student) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_student set account=?,name=?,stuno=?,age=?,gender=?,talephone=?,email=?,wechat=?,address=?,class_id=?,classname=?,depart_id=?,departname=? where id = ?";
        try {
            return queryRunner.update(sql, student.getAccount(), student.getName(), student.getStuno(), student.getAge(), student.getGender(), student.getTalephone(), student.getEmail(), student.getWechat(), student.getAddress(), student.getClassId(), student.getClassname(), student.getDepartId(), student.getDepartname(), student.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_student where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
```

#### 3.3 service

**IStudentService**

```java
package com.huangpw.book.service;

import com.huangpw.book.bean.Student;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IStudentService {

    public List<Student> list(Student student);

    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public void listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Student student);

    public Student findById(int id);

    public int updateById(Student student);

    public int deleteById(int id);
}
```

**StudentServiceImpl**

```java
package com.huangpw.book.service.impl;

import com.huangpw.book.bean.Depart;
import com.huangpw.book.bean.Student;
import com.huangpw.book.dao.IStudentDao;
import com.huangpw.book.dao.impl.StudentDaoImpl;
import com.huangpw.book.service.IStudentService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    private final IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> list(Student student) {
        return studentDao.list(student);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
// 查询分页的数据
        List<Student> list = studentDao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = studentDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return studentDao.count(pageUtils);
    }

    @Override
    public int save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public int updateById(Student student) {
        return studentDao.updateById(student);
    }

    @Override
    public int deleteById(int id) {
        return studentDao.deleteById(id);
    }
}
```

#### 3.4 StudentServlet

```java
package com.huangpw.book.servlet;

import com.huangpw.book.bean.Classes;
import com.huangpw.book.bean.Depart;
import com.huangpw.book.bean.Student;
import com.huangpw.book.service.IClassesService;
import com.huangpw.book.service.IDepartService;
import com.huangpw.book.service.IStudentService;
import com.huangpw.book.service.impl.ClassesServiceImpl;
import com.huangpw.book.service.impl.DepartServiceImpl;
import com.huangpw.book.service.impl.StudentServiceImpl;
import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.service.IRoleService;
import com.huangpw.sys.service.IUserService;
import com.huangpw.sys.service.impl.RoleServiceImpl;
import com.huangpw.sys.service.impl.UserServiceImpl;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "studentServlet", urlPatterns = {"/book/studentServlet"})
public class StudentServlet extends BaseServlet {

    private final IStudentService studentService = new StudentServiceImpl();

    private final IClassesService classesService = new ClassesServiceImpl();

    private final IDepartService departService = new DepartServiceImpl();

    private final IUserService userService = new UserServiceImpl();

    private final IRoleService roleService = new RoleServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp);
        studentService.listPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        // 页面跳转
        req.getRequestDispatcher("/book/student/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收表单提交的数据
        String id = req.getParameter("id");
        String account = req.getParameter("account");
        String stuno = req.getParameter("stuno");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String talephone = req.getParameter("talephone");
        String address = req.getParameter("address");
        String wechat = req.getParameter("wechat");
        String classId = req.getParameter("class_id");

        Student student = new Student();
        student.setAccount(Integer.valueOf(account));
        student.setStuno(stuno);
        student.setName(name);
        student.setAge(Integer.parseInt(age));
        student.setGender(gender);
        student.setEmail(email);
        student.setTalephone(talephone);
        student.setAddress(address);
        student.setWechat(wechat);
        if (!StringUtils.isEmpty(classId)) {
            // 班级
            student.setClassId(Integer.parseInt(classId));
            Classes classes = classesService.findById(Integer.parseInt(classId));
            student.setClassname(classes.getName());
            // 院系
            student.setDepartId(classes.getDepartId());
            Depart depart = departService.findById(classes.getDepartId());
            student.setDepartname(depart.getName());
        }

        if(!StringUtils.isEmpty(id)){
            // 更新
            student.setId(Integer.parseInt(id));
            SysUser user = userService.findById(student.getAccount());
            user.setUsername(student.getName());
            user.setNickname(student.getName());
            // 更新账号信息
            userService.updateById(user);
            // 更新学生信息
            studentService.updateById(student);
        } else {
            SysUser user = new SysUser();
            user.setUsername(student.getName());
            user.setNickname(student.getName());
            // 默认密码
            user.setPassword("123456");
            // 创建的学生，需要指定默认的角色为学生
            SysRole role = new SysRole();
            role.setName(Constant.ROLE_STUDENT);
            List<SysRole> roles = roleService.list(role);
            if(roles != null && roles.size() > 0){
                role = roles.get(0);
                user.setRoleId(role.getId());
                user.setRolename(role.getName());
            }
            // 创建账号
            userService.save(user);
            // 查询出刚刚创建的账号信息
            user = userService.findByName(user.getUsername());
            student.setAccount(user.getId());

            studentService.save(student);
        }
        // 更新或者添加后需要重新查询相关的数据
        resp.sendRedirect("/book/studentServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        studentService.deleteById(Integer.parseInt(id));
        Student student = studentService.findById(Integer.parseInt(id));
        if(student.getAccount() != null && student.getAccount() > 0){
            userService.deleteById(student.getAccount());
        }
        PrintWriter writer = resp.getWriter();
        writer.write("ok");
        writer.flush();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            // 说明是更新操作
            Student classes = studentService.findById(Integer.parseInt(id));
            req.setAttribute("entity", classes);
        }
        // 需要分配院系，所以此处需要查询出所有的院系信息
        List<Depart> departs = departService.list(null);
        req.setAttribute(Constant.DEPARTS, departs);
        // 需要分配班级，所以此处需要查询出所有的班级信息
        List<Classes> classesList = classesService.list(null);
        req.setAttribute(Constant.CLASSESLIST, classesList);

        req.getRequestDispatcher("/book/student/addOrUpdate.jsp").forward(req, resp);
    }
}
```

#### 3.5 前端

**list.jsp**

```jsp
<%--
  Created by IntelliJ IDEA.
  User: AlbertHPW
  Date: 2024/8/3
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>校园图书管理系统 - 学生管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <!-- Data Tables -->
    <link href="/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>学生管理</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="table_basic.html#">选项1</a>
                            </li>
                            <li><a href="table_basic.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-9 m-b-xs">
                            <a class="btn btn-success " type="button" href="/book/studentServlet?action=saveOrUpdatePage">
                                <i class="fa fa-plus"></i>&nbsp;添加
                            </a>
                        </div>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <form action="/book/studentServlet" id="myForm" method="get">
                                    <div class="input-group">
                                        <input type="text" name="key" value="${requestScope.pageUtils.key}" placeholder="请输入关键词" class="input-sm form-control">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-sm btn-primary" onclick="onSearch()">搜索</button>
                                        </span>
                                        <input type="hidden" name="action" value="list">
                                        <input type="hidden" id="pageNum" name="pageNum" value="${requestScope.pageUtils.pageNum}">
                                        <input type="hidden" id="pageSize" name="pageSize" value="${requestScope.pageUtils.pageSize}">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>

                                <th></th>
                                <th>编号</th>
                                <th>名称</th>
                                <th>学号</th>
                                <th>年龄</th>
                                <th>性别</th>
                                <th>邮箱</th>
                                <th>电话</th>
                                <th>微信</th>
                                <th>地址</th>
                                <th>所属院系</th>
                                <th>所属班级</th>
                                <th>日期</th>
                                <th style="width: 160px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.pageUtils.list}" var="entity">
                                <tr>
                                    <td>
                                        <input type="checkbox" class="i-checks" name="input[]">
                                    </td>
                                    <td>${entity.id}</td>
                                    <td>${entity.name}</td>
                                    <td>${entity.stuno}</td>
                                    <td>${entity.age}</td>
                                    <td>${entity.gender}</td>
                                    <td>${entity.email}</td>
                                    <td>${entity.talephone}</td>
                                    <td>${entity.wechat}</td>
                                    <td>${entity.address}</td>
                                    <td>${entity.departname}</td>
                                    <td>${entity.classname}</td>
                                    <td>${entity.createTime}</td>
                                    <td>
                                        <a class="btn btn-warning " type="button"
                                           href="/book/studentServlet?action=saveOrUpdatePage&id=${entity.id}">
                                            <i class="fa fa-edit"></i>更新
                                        </a>
                                        <button class="btn btn-danger " type="button"
                                                onclick="removeData(${ entity.id })">
                                            <i class="fa fa-remove"></i>&nbsp;删除
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <%@include file="/common/commonPage.jsp"%>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.7"></script>


<!-- Peity -->
<script src="/js/plugins/peity/jquery.peity.min.js"></script>

<!-- 自定义js -->
<script src="/js/content.js?v=1.0.0"></script>


<!-- iCheck -->
<script src="/js/plugins/iCheck/icheck.min.js"></script>

<!-- Peity -->
<script src="/js/demo/peity-demo.js"></script>

<!-- Sweet alert -->
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });

    // 删除
    function removeData(id) {
        swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        }, function () {
            $.get("/book/studentServlet?action=remove&id=" + id, function (msg) {
                window.location.href = "/book/studentServlet?action=list"
            })
        });
    }

    // 搜索
    function onSearch() {
        $("#pageNum").val(1);
        $("#myForm").submit();
    }

</script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<!--统计代码，可删除-->

</body>

</html>
```

**addOrUpdate.jsp**

```jsp
<%--
  Created by IntelliJ IDEA.
  User: AlbertHPW
  Date: 2024/8/3
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>校园图书管理系统 - 学生管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>学生管理</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="form_basic.html#">选项1</a>
                            </li>
                            <li><a href="form_basic.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="signupForm" action="/book/studentServlet?action=saveOrUpdate"
                          method="post">
                        <input type="hidden" name="id" value="${requestScope.entity.id}">
                        <input type="hidden" name="account" value="${requestScope.entity.account}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" aria-required="true"
                                       aria-invalid="true" class="error" value="${requestScope.entity.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">学号：</label>
                            <div class="col-sm-8">
                                <input id="stuno" name="stuno" class="form-control" type="text" aria-required="true"
                                       aria-invalid="false" class="valid" value="${requestScope.entity.stuno}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">年龄：</label>
                            <div class="col-sm-8">
                                <input id="age" name="age" class="form-control" type="number" aria-required="true"
                                       aria-invalid="false" class="valid" value="${requestScope.entity.age}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">性别：</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" ${ requestScope.entity.gender == "男" ? "checked": "" } value="男" id="gender1" name="gender">男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" ${ requestScope.entity.gender == "女" || requestScope.entity.gender == null ? "checked": "" } value="女" id="gender2" name="gender">女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电话：</label>
                            <div class="col-sm-8">
                                <input id="talephone" name="talephone" class="form-control" type="text"
                                       aria-required="true" aria-invalid="false" class="valid"
                                       value="${requestScope.entity.talephone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">邮箱：</label>
                            <div class="col-sm-8">
                                <input id="email" name="email" class="form-control" type="text" aria-required="true"
                                       aria-invalid="false" class="valid" value="${requestScope.entity.email}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">微信：</label>
                            <div class="col-sm-8">
                                <input id="wechat" name="wechat" class="form-control" type="text" aria-required="true"
                                       aria-invalid="false" class="valid" value="${requestScope.entity.wechat}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">地址：</label>
                            <div class="col-sm-8">
                                <input id="address" name="address" class="form-control" type="text"
                                       aria-required="true" aria-invalid="false" class="valid"
                                       value="${requestScope.entity.address}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">分配院系：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="departId" id="departId" onchange="dispatcherClasses(this.value)">
                                    <option value="-1">---请选择院系---</option>
                                    <c:forEach items="${requestScope.departs}" var="depart">
                                        <option value="${depart.id}" ${depart.id == requestScope.entity.departId ? 'selected' : ''}>${depart.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">分配班级：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="classId" id="classId">

                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <a class="btn btn-default" href="/book/studentServlet?action=list">取消</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.7"></script>

<!-- 自定义js -->
<script src="/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>

<script type="text/javascript" src="/js/demo/form-validate-demo.js"></script>

<!-- iCheck -->
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
        dispatcherClasses($("#departId").val())
    });
    function dispatcherClasses(departId) {
        if(departId === -1) return;
        let  classId = ${requestScope.entity.classId};
        $.get("/book/classesServlet?action=findByDepartId&departId="+ departId, function (data) {
            let opt = "";
            for (let i = 0; i < data.length; i++) {
                let selected = classId === data[i].id ? 'selected' : '';

                opt += "<option value='" + data[i].id + "' " + selected + " >" + data[i].name + "</option>"
            }
            $("#classId").html(opt);
        })
    }
</script>

</body>

</html>
```

### 4.图书类型

图书类型是我们图书信息中的一个非常重要的信息。所以我们需要单独的维护，功能非常简单就是一个普通的 **`CRUD`** 操作。

![图片101](/images/图片101.png)

### 5.图书管理

图书管理功能是整个系统的核心功能。当然这块也是一个基本的 **`CRUD`** 操作。只是这块的字段比较多。在实现的时候需要注意点即可。

![图片102](/images/图片102.png)

## 五、借阅管理

### 1.借书卡

#### 1.1 查询借书卡

借书卡在正常的 **`CRUD`** 操作的基础上，我们还需要注意一些特殊的情况。查询信息的时候。如果是管理员则可以查询所有的信息，如果是普通用户则只能查看自己的信息。这块的控制在登录的用户信息。

![图片103](/images/图片103.png)

然后就是在Dao中处理的时候，需要考虑根据当前登录用户查询的操作。

```java
@Override
public List<BorrowCard> listPage(PageUtils pageUtils,SysUser user) { 
    QueryRunner queryRunner = MyDbUtils.getQueryRunner();            
    String sql = "select * from t_borrow_card where 1 = 1";              
    if(StringUtils.isNotEmpty(pageUtils.getKey())){                    
        sql += " and stuname like '%"+pageUtils.getKey()+"%' ";                  
    }                     
    if(user != null && user.getIsAdmin() == false){                      
        // 不是管理员                       
        sql += " and stuid = " + user.getId();                          
    }                                 
    sql += " limit ?,? ";                              
    // 计算 分页开始的位置                               
    int startNo = pageUtils.getStart(); 
    try {
        return queryRunner.query(sql,new BeanListHandler<BorrowCard>(BorrowCard.class),startNo,pageUtils.getPageSize());
    } catch (SQLException throwables) { 
        throwables.printStackTrace();                                                                      
    }                                          
    return null;                        
}
```

```java
@Override
public int count(PageUtils pageUtils, SysUser user) {
    QueryRunner queryRunner = MyDbUtils.getQueryRunner();
    String sql = "select count(1) from t_borrow_card where 1 = 1 ";
    if(StringUtils.isNotEmpty(pageUtils.getKey())){
        sql += " and stuname like '%"+pageUtils.getKey()+"%' ";
    }
    if(user != null && user.getIsAdmin() == false){
        // 不是管理员
        sql += " and stuid = " + user.getId();
    }
    try {
        return queryRunner.query(sql, new ResultSetHandler<Integer>() { 
            @Override
            public Integer handle(ResultSet resultSet) throws SQLException { 
                resultSet.next();                                     
                return resultSet.getInt(1);                                       
            }
        });
    } catch (SQLException throwables) { 
        throwables.printStackTrace();
    }
    return 0;
}
```

效果：

![图片104](/images/图片104.png)

![图片105](/images/图片105.png)

#### 1.2 分配借书卡

分配借书卡就是对借书卡的添加和更新的操作。在这块我们需要注意的地方一个是需要查询所有的学生信息。

![图片106](/images/图片106.png)

然后使用到 **`layerDate`** ，这个日期时间的插件。

![图片107](/images/图片107.png)

然后在 **`Servlet`** 中获取到的是特定格式的 **`字符串`** ，我们需要自定义转换的方法来处理。

```java
public class DateUtils {

	public static final String DATE_PARTTERN1 = "YYYY-MM-DD hh:mm:ss";

    /**
    *字符串转换为Date类型
    *@param msg
    *@param parttern
    *@return
    */
	public static Date stringToDate(String msg,String parttern){ 
        SimpleDateFormat format = new SimpleDateFormat(parttern);
        try {
            return format.parse(msg);
        } catch (ParseException e) { 
            e.printStackTrace();                          
        }
        return new Date();
    }
}
```

就可以完成添加和更新的处理。

![图片108](/images/图片108.png)

#### 1.3 下架处理

当借书卡还没过期的情况下。管理员想要终止这个借书卡的使用。那么可以做 **`下架`** 的处理，**`下架`** 的本质是修改 **`state`** 的状态为 **`3`**。

![图片109](/images/图片109.png)

### 2.图书展示

图书展示是给学员查看的，方便学生根据不同的类型快速查找到对应的书籍信息，并且完成相关的 **`借阅`** 操作。

#### 2.1 标签页

需要根据不同的类别展示不同的图书信息。那么这块我们通过 **`bootstrap`** 中提供的标签来实现。

![图片110](/images/图片110.png)

在这块我们需要注意相关 **`CSS`** 属性的处理。

![图片111](/images/图片111.png)

动态管理ID信息，ID和类别的ID绑定。

![图片112](/images/图片112.png)

然后就给对应的标签页绑定对应的 **`点击`** 事件，同时随着我们的点击会给 **`tab-pane`** 添加对应的 **`active`** 的class属性。

```js
$(".tabs-container .nav-tabs li").click(
    function(){ 
        var href = $(this).children()[0].href
        // 做字符串的截取操作
        var aId = href.substring(href.lastIndexOf('tab-'),href.length);
        // 先给所有的 class= tab-pane 的都移除掉 active 属性
        $(".tab-pane").removeClass('active')
        // 然后单独给当前点击的添加 active 属性
        $("#"+aId).addClass("active")
    }
)
```

然后就是在页面第一次加载的时候，我们需要给第一个标签也做选中和加载 **`active`** 属性的行为。

```js
function initTab(){
    var li = $(".tabs-container .nav-tabs").children()[0];
    $(li).addClass('active')
    var href = $($(".tabs-container .nav-tabs").children()[0]).children()[0].href
    // 做字符串的截取操作
    var aId = href.substring(href.lastIndexOf('tab-'),href.length);
    // 先给所有的 class= tab-pane 的都移除掉 active 属性
    $(".tab-pane").removeClass('active')
    // 然后单独给当前点击的添加 active 属性
    $("#"+aId).addClass("active")
}
```

具体的效果如下：

![图片113](/images/图片113.png)

#### 2.2 图书信息

我们添加标签页的目的是更好的展示图书信息。所以在查询数据类型的时候我们需要同步的查询类型对应的书籍信息。首先在图书类型的 **`bean`** 中关联设置了对应的属性。

```java
@Data
public class BookType {

    private Integer id; 
    private String name; 
    private String notes; 
    private Date createtime;

    private List<Book> books; // 当前类型对应的图书信息
}
```

然后在Servlet中添加了对应的处理。

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 查询所有的类别信息
	List<BookType> list = typeService.list(); 
    if(list != null && list.size() > 0){
		// 遍历 每个类型 查询对应的 图书信息
		for (BookType type : list) {
            Book book = new Book(); 
            book.setTypeid(type.getId());
            List<Book> books = bookService.list(book); 
            type.setBooks(books);
        }
    }
    req.setAttribute("list",list);
    req.getRequestDispatcher("/book/book/showBook.jsp").forward(req,resp);
}
```

最后在页面中循序处理展示图书信息。

```html
<div class="tab-content">
    <c:forEach items="${list}" var="entity">
        <div id="tab-${entity.id}" class="tab-pane">
            <div class="panel-body">
                <div class="row">
                    <c:forEach items="${entity.books}" var="book">
                        <div class="col-sm-4">
                            <div class="contact-box">
                                <div class="col-sm-4">
                                    <div class="text-center">
                                        <img alt="image" class="m-t-xs img-responsive" 
                                             src="/sys/downloadServlet?fileName=${book.img}">
                                        <div class="m-t-xs font-bold">CTO</div>
                                    </div>
                                </div>
                                <div class="col-sm-8">
                                    <h3><strong>${book.name}</strong></h3>
                                    <p>
                                        <i class="fa fa-map-marker"></i> ${book.author}
                                    </p>
                                    <address>
                                        <strong>${book.price}</strong><br>
                                        ${book.publish}<br>
                                        Weibo:<a href="">${book.notes}</a><br>
                                        <abbr title="Phone">Tel:</abbr> (123) 456-7890
                                    </address>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
```

具体效果为：

![图片114](/images/图片114.png)

然后可以对展示的图书的信息做出相关的调整和优化。

![图片115](/images/图片115.png)

### 3.借书功能

![图片116](/images/图片116.png)

借阅数据的数据会存储在 **`t_borrow_recoder`** 这张表中，那么与之对应就需要完成对应的后端 **`CRUD`** 的基础功能。

![图片117](/images/图片117.png)

然后在借阅图书的时候我们需要先判断当前登录的用户是否有可以使用的借书卡，如果才能借阅，否则提示不能借阅。

```java
// 借阅书籍的方法
function goBorrowing(bookId){
	// 判断是否有 可用的借书卡
    $.get("/book/borrowCardServlet?action=checkHaveCard",function(data){  
        console.log("data",data);
    })
}
```

```java
/**
*检查当前登录的用户是否有可用的借书卡
*@param req
*@param resp
*@throws Exception
*/
public void checkHaveCard(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    List<BorrowCard> list =
        service.listCanUseCard(getCurrentLoginUser(req,resp).getId()); 
    String msg = "error";
    if(list != null && list.size() > 0){ 
        msg = "ok";                      
    }
    PrintWriter writer = resp.getWriter();
    writer.write(msg);
    writer.flush();
}
```

操作的效果如下：

![图片118](/images/图片118.png)

点击**借阅**按钮后弹出对应的窗口来实现借书操作的交互。我们需要使用到模态窗口

![图片133](/images/图片133.png)

然后我们具体的操作是：点击**借阅**弹出窗口

![图片134](/images/图片134.png)

对应的js方法

```js
// 借阅
function goBorrowing(bookId) {
    // 判断是否有可用的借书卡
    $.get("/book/borrowCardServlet?action=checkHaveCard", function (data) {
        console.log(data)
        if(data.msg == "ok") {
            // 可以借阅
            // 绑定数据
            $("#bookid").val(bookId)
            // 下拉菜单的信息
            let option = ""
            for (let i = 0; i < data.cards.length; i++) {
                let card = data.cards[i]
                option += "<option value='" + card.id + "'>" + card.id + "[" + timestampToDate(card.starttime) + "至" + timestampToDate(card.endtime) + "]" + "</option>"
            }
            $("#cardid").html(option)
            // 打开模态窗口
            $("#myModal").modal('show')

        } else {
            //不可以借阅
            swal("借阅失败！", "可以使用的借书卡不够，请申请后借阅。", "warning");
        }
    })
}
function timestampToDate(timestamp) {
    let date = new Date(timestamp)
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();
    return  year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second);
}
// 提交借阅信息
function submitData() {
    // 获取bookid 和 cardid
    let bookid = $("#bookid").val()
    let cardid = $("#cardid").val()
    // 发送请求
    $.get("/book/borrowRecoderServlet?action=saveOrUpdate&bookid="+ bookid +"&cardid=" + cardid, function (data) {
        if(data == "ok") {
            swal("借阅成功！", "请按时归还。", "success");
            // 刷新页面
            //window.location.reload()
            location.href = "/book/showBookServlet"
        } else {
            swal("借阅失败！", "请稍后再试。", "error");
        }
    })
}
```

这块做了模态窗口的数据绑定和提交后的数据处理。同时在模态窗口的处理如下

```html
<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <i class="fa fa-laptop modal-icon"></i>
                <h4 class="modal-title">借阅图书</h4>
                <small class="font-bold">请选择对应的借书卡来借阅。
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>借书卡</label>
                    <select class="form-control m-b" id="cardid" name="cardid">

                    </select>
                    <input type="hidden" id="bookid" name="bookid">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitData()">保存</button>
            </div>
        </div>
    </div>
</div>
```

还有保存数据的逻辑

1. 获取提交的数据

2. 封装图书信息同时更新图书状态

3. 封装借书卡信息同时更新借书卡状态

4. 新增借书记录

```java
@Override
public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    // 获取客户端端提交的数据
    String bookid = req.getParameter("bookid");
    String cardid = req.getParameter("cardid");
    String id = req.getParameter("id");

    SysUser loginUser = this.getCurrentLoginUser(req, resp);
    BorrowRecoder entity = new BorrowRecoder();
    if(!StringUtils.isEmpty(cardid)) {
        // 借书卡不为空
        BorrowCard borrowCard = borrowCardService.findById(Integer.parseInt(cardid));
        entity.setCardid(borrowCard.getId());
        entity.setStuname(borrowCard.getStuname());
        entity.setUserid(borrowCard.getUserid());
        // 新增借阅记录
        if(StringUtils.isEmpty(id)) {
            // 同步更新借书卡的状态
            borrowCard.setState(1);
            borrowCardService.updateById(borrowCard);
        }
    }
    if(!StringUtils.isEmpty(bookid)) {
        Book book = bookService.findById(Integer.parseInt(bookid));
        entity.setBookid(book.getId());
        entity.setBookname(book.getName());
        // 新增借阅记录
        if(StringUtils.isEmpty(id)) {
            book.setState(1);
            bookService.updateById(book);
        }
    }

    if(StringUtils.isEmpty(id)) {
        // 新增
        borrowRecoderService.save(entity);
    } else {
        borrowRecoderService.updateById(entity);
    }

    PrintWriter writer = resp.getWriter();
    writer.write("ok");
    writer.flush();
    //resp.sendRedirect("/book/borrowRecoderServlet?action=" + Constant.BASE_ACTION_LIST);
}
```

最后对应的效果如下

![图片135](/images/图片135.png)

### 4.借阅管理

学生借阅了相关的图书后。可以查看所有借阅信息。同时可以做出归还的操作。这块管理员可以看到所有的数据，但是不能归还。普通的学员只能看到自己的借阅记录。没有归还的图书可以做出归还的操作。

![图片119](/images/图片119.png)

![图片120](/images/图片120.png)

在后台代码中的处理核心。

![图片121](/images/图片121.png)

![图片122](/images/图片122.png)

展示数据的时候。注意按钮的操作。

![图片123](/images/图片123.png)

## 六、Echarts统计

### 1.图书统计

通过Echarts根据不同的数据类型统计数据的数量。并且通过柱状图来展示。

![图片124](/images/图片124.png)

实现的步骤：

引入echars的js文件

![图片125](/images/图片125.png)

然后定义柱状图展示的div。

![图片126](/images/图片126.png)

然后是基础的js代码。

```js
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echarts-book-chart'));
	// 指定图表的配置项和数据
	var option = { 
        tooltip: {}, 
        legend: {
            data: ['数量']
        },
        xAxis: {
            data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']    
        },
        yAxis: {},
        series: [     
            {
                name: '数量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }
        ]
    };
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	// 需要去后台加载相关的数据
    $.get("/homeServlet",function(data){
        // 更新option中的信息
        option.xAxis.data = data.typeNames
        option.series[0].data = data.bookNums
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    })
</script>
```

最后是Servlet中的统计查询操作。

```java
@WebServlet(name = "homeServlet",urlPatterns = {"/homeServlet"}) 
public class HomeServlet extends HttpServlet {
    
    private IBookTypeService typeService = new BookTypeServiceImpl();
    
    private IBookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询统计相关的数据 查询出所有的类别信息。以及对应的类型的图书数量
        List<BookType> types = typeService.list();
        // 需要返回的统计的数据
        List<String> typeNames = new ArrayList<>(); 
        List<Integer> bookNums = new ArrayList<>(); 
        if(types != null && types.size() > 0){
            for (BookType type : types) {
                // 根据 类型的编号查询图书的数量 Book book = new Book(); book.setTypeid(type.getId());
                List<Book> list = bookService.list(book); 
                typeNames.add(type.getName()); 
                bookNums.add(list.size());
            }
        }
        // 返回的统计的数据通过Map统一处理 
        Map<String,Object> map = new HashMap<>(); 
        map.put("typeNames",typeNames); 
        map.put("bookNums",bookNums);
        resp.setContentType("application/json;charset=utf-8");
        // map 转换为对应的json数据
        String json = JSONObject.toJSONString(map); 
        PrintWriter writer = resp.getWriter(); 
        writer.write(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
```

### 2.饼图的应用

统计不同的书籍类型的图书数量。我们也可以通过饼图的方式来展示数据。具体的效果如下。

![图片127](/images/图片127.png)

然后这块需要展示的数据的结构为：

```js
// 指定图表的配置项和数据
var option = { 
    tooltip: {
    	trigger: 'item'
    }, 
    legend: {
        orient: 'vertical',
        left: 'left'        
    }, 
    series: [         
        {
            type: 'pie',   
            data: [         
                {
                    value: 335, name: '直接访问'        
                },         
                {
                    value: 234, name: '联盟广告'        
                },         
                {
                    value: 1548, name: '搜索引擎'
                }
            ] 
        }    
    ]         
}
```

那么与之对应的在 **`HomeServlet`** 中，我们就需要对应的准备相关的数据。

![图片128](/images/图片128.png)

### 3.首页统计

在整个的首页布局中，我们可以使用模板中提供的统计样式。

![图片129](/images/图片129.png)

![图片130](/images/图片130.png)

这块有个小问题需要注意。echarts的版本兼容性问题。我们在这块通过 **`iframe`** 来解决。

![图片131](/images/图片131.png)

## 七、未实现功能补充

- 1、院系管理，不能随便删除
- 2、左侧顶部 - 修改头像、个人资料、修改密码
