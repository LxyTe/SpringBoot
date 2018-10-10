# SpringBoot
SpringBoot个人学习资料

在您第1次接触和学习Spring框架的时候，是否因为其繁杂的配置而退却了？在你第n次使用Spring框架的时候，是否觉得一堆反复黏贴的配置有一些厌烦？<br>
那么您就不妨来试试使用Spring Boot来让你更易上手，更简单快捷地构建Spring应用！</br>
Spring Boot让我们的Spring应用变的更轻量化。</br>
比如：你可以仅仅依靠一个Java类来运行一个Spring引用。你也可以打包你的应用为jar并通过使用java -jar来运行你的Spring Web应用。</br>
Spring Boot的主要优点：<br>
>  >为所有Spring开发者更快的入门<br>
开箱即用，提供各种默认配置来简化项目配置<br>
内嵌式容器简化Web项目<br>
没有冗余代码生成和XML配置的要求<br>
本Demo主要目标完成Spring Boot基础项目的构建，并且实现一个简单的Http请求处理，通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。<br>

#### 快速入门
  创建一个Maven工程，名字为XXXX
  ![如图](https://github.com/LxyTe/SpringBoot/blob/master/image/%E5%9F%BA%E6%9C%AC%E4%BE%9D%E8%B5%96.png)<br>

>>spring-boot-starter-parent作用:<br>
在pom.xml中引入spring-boot-start-parent,spring官方的解释叫什么stater poms,它可以提供dependency management,也就是说依赖管理，引入以后在申明其它dependency的时候就不需要version了，后面可以看到。<br>
spring-boot-starter-web作用:<br>
springweb 核心组件 <br>
spring-boot-maven-plugin作用:<br>
 如果我们要直接Main启动spring，那么以下plugin必须要添加，否则是无法启动的。如果使用maven 的spring-boot:run的话是不需要此配置的。（我在测试的时候，如果不配置下面的plugin也是直接在Main中运行的。）


 
*  创建package命名为com.dist.controller（根据实际情况修改）<br>
 >> 创建HelloController类，内容如下 如果是直接使用ide工具生成的springboot项目则此类会被默认创建<br>
>>@RestController<br>
>>@EnableAutoConfiguration <br>
public class HelloController { <br>
	@RequestMapping("/hello")<br>
	public String index() {<br>
		return "Hello World";<br>
	}	<br>
public static void main(String[] args) {<br>
		SpringApplication.run(HelloController.class, args);<br>
	}<br>
}<br>

            注解                     |    说明
            @RestController          | 在上加上RestController 表示修饰该Controller所有的方法返回JSON格式,直接可以编写Restful接口
            @SpringBootApplication   | 从源代码中得知 @SpringBootApplication 被 @Configuration、@EnableAutoConfiguration、@ComponentScan 注解所修饰，换言之 Springboot 提供了统一的注解来替代以上三个注解，简化程序的配置。
            @Configuration           | @Configuration 是一个类级注释，指示对象是一个bean定义的源。@Configuration 类通过 @bean 注解的公共方法声明bean。被修饰的类，会扫描，如果有@bean注解，就会创建这个bean
            @bean                    | @Bean 注释是用来表示一个方法实例化，配置和初始化是由 Spring IoC 容器管理的一个新的对象。
            @EnableAutoConfiguration |  自动的加载一些配置文件，以达到智能的依赖加载
            @ComponentScan           |   表示要扫描哪些包下的类  相当于以下代码   <context:component-scan base-package="com.dist">
	    
  #  SpringBoot启动方式
  SpringBoot由于内嵌了Tomcat，所有不用在放在tomcat下进行执行，可以直接使用run方法来执行，下面说明两种启动方式
  * One
> > >@RestController <br>
@EnableAutoConfiguration<br>
public class HelloController {<br>
	@RequestMapping("/hello")<br>
	public String index() {<br>
		return "Hello World";<br>
	}<br>	
public static void main(String[] args) {<br>
		SpringApplication.run(HelloController.class, args);<br>
	}<br>
}<br>
启动主程序，打开浏览器访问http://localhost:8080/hello，可以看到页面输出Hello World
  * Two 使用spring插件自动生成的SpringBoot项目，会带有下面的代码 <br>
@SpringBootApplication <br>
       public class SpringBootAicdApplication { <br>
	public static void main(String[] args) { <br>
		SpringApplication.run(SpringBootAicdApplication.class, args);<br>
	    }<br>
      }<br>

### Web 开发
    在我们开发Web应用的时候，需要引用大量的js、css、图片等静态资源。
       默认配置
    Spring Boot默认提供静态资源目录位置需置于classpath下，目录名需符合如下规则：
    /static
    /public
    /resources
    /META-INF/resources
    举例：我们可以在src/main/resources/目录下创建static，在该位置放置一个图片文件。启动程序后，尝试访问http://localhost:8080/D.jpg。如能显示图片，配置成功。

 ###### 全局异常的处理
 
     @ControllerAdvice 
     public class GlobalExceptionHandler {
	  @ExceptionHandler(RuntimeException.class)//表示拦截所有运行时异常
	  @ResponseBody  //返回一个json串
	 public Map<String, Object> exceptionHandler() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCode", "101");
		map.put("errorMsg", "系統错误!");
		return map;
	}
     }
       出现异常的时候给用户展示某个指定的页面(可用于遮羞)
        @ControllerAdvice  可以全局拦截指定的异常，并做想要的包装处理，比如跳转到别的页面，或者返回指定的数据格式等等。
     public class GlobalExceptionHandler {
	  @ExceptionHandler(RuntimeException.class)//表示拦截所有运行时异常
	 public  String  exceptionHandler(ModelAndView mode) {
		return error.html;
	}
     }
     
