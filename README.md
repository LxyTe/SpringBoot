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
            @SpringBootApplication   | 从源代码中得知 @SpringBootApplication 被 @Configuration、@En ableAutoConfiguration、@ComponentScan 注解所修饰，换言之 Springboot 提供了统一的注解来替代以上三个注解，简化程序的配置。
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
	 public  String  exceptionHandler(Model model) {
		return error.html;
	}
     }
     
  ### 数据访问
  > springboot整合使用JdbcTemplate
  
     	       <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>  
		
  数据源配置
  
    spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8    //对编码进行过滤，防止出现乱码
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    
 测试代码,run方法主类要注意加@ComponentScan(basePackages = "com.dist") 扫描包，此处意思为 扫描以com.dist.* 所有包
            @Service<br>
                public class UserServiceImpl implements UserService {<br>
	        @Autowired<br>
	          private JdbcTemplate jdbcTemplate;  //自动注入模板类，开箱即用，此层一般都放在 dao层进行配置,以确保代码之间冗余度降低，这里只做测试使用<br>
	           public void createUser(String name, Integer age) {<br>
		System.out.println("ssss");<br>
		jdbcTemplate.update("insert into users values(null,?,?);", name, age);  //此处增，删，改，都可用update接口，查询用select<br>
	        }<br>
             }<br>
> springboot整合使用mybatis
                pom  依赖
    	        <dependency><br>
			<groupId>org.mybatis.spring.boot</groupId><br>
			<artifactId>mybatis-spring-boot-starter</artifactId><br><br>
			<version>1.1.1</version><br>
		</dependency><br>
 代码 纯注解开发
       
      注意要点 @MapperScan(basePackages = "com.dist.dao.mapper") 此注解需要放在SpringBoot主类run方法所在的类,作用：扫描mybatis的注解驱动
      @Service
      public interface UserMapper {  此处为一个借口类，省略实现类，和调用细节,和其它MVC三层操作一样
	@Select("SELECT * FROM USERS WHERE NAME = #{name}")   
	User findByName(@Param("name") String name);
	@Insert("INSERT INTO USERS(NAME, AGE) VALUES(#{name}, #{age})")
	int insert(@Param("name") String name, @Param("age") Integer age);
      }
  建议使用参数注入的时候用#不要用$,具体原因请 [点击](https://blog.csdn.net/u011884440/article/details/78058540)
   
 > springboot整合使用springjpa（由于spring data jpa是依赖于 hibernate实现的，所有我们的实体类，需要按照hibernate的规则来进行配置 ）
      pom 依赖
      
                <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
    创建一个测试实体类
    
      @Entity(name = "users")
    public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer age;
      // ..get/set方法
    }   
    
    创建一个UserDaS   优点 jpa可以大大加快我们的开发速度，它自己封装了很多的接口方法，缺点，一些复杂查询和动态查询没有mybatis灵活
    public interface UserDao extends JpaRepository<User, Integer> { 泛型参数第一个要查询的对象，第二个参数为主键的类型，可以Integer ,String,等  
    }
     以下为测试代码，但是在实际开发用 还是建议使用mvc实现，分层调用
    @RestController
    public class IndexController {
	@Autowired
	private UserDao userDao;
	@RequestMapping("/index")
	public String index(Integer id) {
		User findUser = userDao.findOne(id);  具体的api可以在JpaRepository  里面查询，封装了N多接口
		System.out.println(findUser.getName());
		return "success";
	}
    }
    run方法的主类需要加上@EnableJpaRepositories(basePackages = "com.dist.dao") 用来扫描jpa配置 
                       @EntityScan(basePackageClasses=UserEntity.class) 用来扫描实体类中映射数据配置
     
   >>  springboot整合多数据源
    
    应用场景：我们用springboot进行多模块开发的时候，有可能每个模块都是一个不同的业务功能，这些业务功能可能对应着不同的数据库，这样就会出现，多数据源的情景了。
      数据源配置
       #spring.datasource.test1.driverClassName = com.mysql.jdbc.Driver
       #spring.datasource.test1.jdbc-url = jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8
       #spring.datasource.test1.username = root
       #spring.datasource.test1.password = 123
      ##
      #spring.datasource.test2.driverClassName = com.mysql.jdbc.Driver
       注意此处使用了jdbc-url来进行url的配置，如果不这样配置，会导致使用多数据源的时候，SpringBoot加载不到这个url
      #spring.datasource.test2.jdbc-url = jdbc:mysql://localhost:3306/tt?useUnicode=true&characterEncoding=utf-8  
      #spring.datasource.test2.username = root
      #spring.datasource.test2.password = 123 
      
     由于springBoot默认加载数据源的时候 ，加载的是 spring.datasource.url 这种格式的配置，所以这里我们需要使用代码来指定加载某个特定的数据源
    
    
         @Configuration //表示扫描配置信息，比如创建Bean，数据源等。
     @MapperScan(basePackages = "com.dist.service", sqlSessionFactoryRef = "test1SqlSessionFactory"),basePackages = "com.dist.service" 表示 这个包下所有 有关 数据库的操作都使用的是 后面引用的那个 session工厂 所指定的数据源。,扫描包引用某个sessionFactory
     public class Datasource1 {
 
	@Bean(name = "test1DataSource") 创建一个bean名字叫xxx
	@Primary  //此注解，表明此数据源为默认数据源，一个项目中建议只出现一个默认数据源，否则会抛异常
	@ConfigurationProperties(prefix = "spring.datasource.test1")在 application.properties中按照这种头部来加载，配置文件
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();// 构建一个datasource

	
	
	@Bean(name = "test1SqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)  引用 上面创建的DataSource并且注入到这个对象中 dataSource
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);[跳到自己博客列表](http://write.blog.csdn.net/postlist)
		   下面两行注释的代码表示加载**.xml配置中的属性，如果么有配置，则不需要使用
      //		bean.setMapperLocations(
     //				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
		return bean.getObject();
       }
       }
  第二个源代码省略，[点击查看代码](https://github.com/LxyTe/SpringBoot/blob/master/springBoot-AICD/src/main/java/com/dist/datarouse/Datasource2.java)
  
   ###  SpringBoot事务管理
    
        以前在spring中我们管理数据源的事务的时候，需要在xml配置文件中做一下配置
	<!-- 配置spring的PlatformTransactionManager，名字为默认值 -->	
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">	
	<property name="dataSource" ref="dataSource" />	</bean>	 绑定一个数据源	
	<!-- 开启事务控制的注解支持 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
	
  在springBoot中我们不需要在配置xml文件了，可以做个开箱即用（默认集成了）
    在service层或者dao层中加上@Transactional 注解即可使用，
        
	  注意要点：
	   @Transactional 可以作用于接口、接口方法、类以及类方法上。当作用于类上时，该类的所有 public 方法将都具有该类型的事务属性，同时，我们也可以在方法级别使用该标注来覆盖类级别的定义。
         虽然 @Transactional 注解可以作用于接口、接口方法、类以及类方法上，但是 Spring 建议不要在接口或者接口方法上使用该注解，因为这只有在使用基于接口的代理时它才会生效。
	 另外， @Transactional注解应该只被应用到 public 方法上，这是由 Spring AOP 的本质决定的。如果你在 protected、private 或者默认可见性的方法上使用 @Transactional 注解，这将被忽略，也不会抛出任何异常。
	 
	 属性              |       类型             |  描述
	 value             |   String              |  指定使用的事务管理器
	propagation        |   enum: Propagation   |  事务传播行为设置	
        isolation          |   enum: Isolation     |  事务的隔离级别设置
	readOnly           |   boolean             |  读写或者只读，默认为读写
	timeout            | int (in seconds granularity)|超时时间设置
         以上为@Transactional 注解常用属性说明。
	
 >> SpringBoot分布式事务管理	
    目前我所知道解决分布式事务的方法有三种
   
    1.  automatic+jta （下面代码依赖于这个）
    2.  两段提交协议 
    3.  MQ推送(后续可以使用rabbitmq，和redis集成解决，)。
   
   pom依赖
   
       <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jta-atomikos</artifactId>
       </dependency>

     连接池基于 automatic配置 ，这里只设置了一个，第二个把下面配置中的test修改为test2即可
     mysql.datasource.test.url = jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8
     mysql.datasource.test.username = root
     mysql.datasource.test.password = 123
     mysql.datasource.test.minPoolSize = 3    最小连接数
     mysql.datasource.test.maxPoolSize = 25
     mysql.datasource.test.maxLifetime = 20000  <!--连接最大存活时间，超过这个且没有正在使用的连接将自动销毁,0无限制，1000 =1000s,对于一些会自动中断连接的数据库如mysql，可以设置这个参数，在达到这个时间的时候会自动关闭连接，下次数据库调用的时候就会新建-->
     mysql.datasource.test.borrowConnectionTimeout = 30 <!--获取连接失败重新获等待最大时间，在这个时间内如果有可用连接，将返回-->
     mysql.datasource.test.loginTimeout = 30    <!--java数据库连接池，最大可等待获取datasouce的时间-->
     mysql.datasource.test.maintenanceInterval = 60  <!--连接回收时间-->
     mysql.datasource.test.maxIdleTime = 60    <!--最大闲置时间，超过最小连接池连接的连接将将关闭-->
     
     集成automatic+jta之后，我们的配置方式也需要做一定的修改。
      @ConfigurationProperties(prefix = "mysql.datasource.test") 表示读取application.properties中的对应开头的配置属性，然后注入到下面的对应属性中 ,DBConfig2数据源的配置和下面的代码一致，只是需要修改成 mysql.datasource.test2
      public class DBConfig1 {

	private String url;
	private String username;
	private String password;
	private int minPoolSize;
	private int maxPoolSize;
	private int maxLifetime;
	private int borrowConnectionTimeout;
	private int loginTimeout;
	private int maintenanceInterval;
	private int maxIdleTime;
	private String testQuery;
     } 
      
   具体注入数据源的代码, [点击查看代码](https://github.com/LxyTe/SpringBoot/blob/master/springBoot-AICD/src/main/java/com/dist/datarouse/TestMyBatisConfig1.java)
    就是把数据源都交给automatic进行管理，这样保证在automatic中的数据源中原子性一致.但是要注意在run方法所在的类中加入下面的注解，表示扫描并且注册配置文件中的值到具体的属性<br>
    @EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })  //加载具体的属性配置文件内容
    
   ### 日志管理
   [配置log4j.properties](https://github.com/LxyTe/SpringBoot/blob/master/springBoot-AICD/src/main/resources/log4j.properties)
     主要要导入log4j的jar包
     	     
	     private static Logger log=Logger.getLogger(TestController.class);  这样使用可以把TestController里面的打印信息都做记录
	     syso(log.info("...."));
	     
    当我们Controller太多的时候，使用上面的日志记录过于麻烦，这里我们可使用AOP做统一处理
    
    pom依赖
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>
  [查看具体代码，请跳转,里面有详细注释](https://github.com/LxyTe/SpringBoot/blob/master/springBoot-AICD/src/main/java/com/dist/demo/WebLogAspect.java)
  
  ### 集成缓存
    这里我们主要讲两种缓存方式
    1.springBoot自带的EhCache缓存
    2.集成redis 
 > Ehcache
     
     pom文件依赖
     <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-cache</artifactId>
     </dependency>
      新建eache配置文件
      <?xml version="1.0" encoding="UTF-8"?>
        <ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	  xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
	  updateCheck="false">
	<diskStore path="java.io.tmpdir/Tmp_EhCache" />

	<!-- 默认配置 -->
	<defaultCache maxElementsInMemory="5000" eternal="false"
		timeToIdleSeconds="120" timeToLiveSeconds="120"
		memoryStoreEvictionPolicy="LRU" overflowToDisk="false" />

	<cache name="baseCache" maxElementsInMemory="10000"
		maxElementsOnDisk="100000" />
        </ehcache>
       配置信息详细介绍
        1. <!--  
        name:缓存名称。  
       maxElementsInMemory：缓存最大个数。  
       
       eternal:对象是否永久有效，一但设置了，timeout将不起作用。  
       
       timeToIdleSeconds：设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。  
       
       timeToLiveSeconds：设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。  
       
       overflowToDisk：当内存中对象数量达到maxElementsInMemory时，Ehcache将会对象写到磁盘中。  
       
       diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。  
       
       maxElementsOnDisk：硬盘最大缓存个数。  
       
       diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false. 
       
       memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。  
       
       clearOnFlush：内存数量最大时是否清除。  
    --> 
    
    代码中具体使用 特别快  
    @CacheConfig(cacheNames = "baseCache") 名字是在xml文件中起的名字
      public interface UserMapper {
	@Select("select * from users where name=#{name}")
	@Cacheable  //表示缓存这个查询语句的结果，缓存在java内存中，
	UserEntity findName(@Param("name") String name);
     }
     注意要在run方法所在的类加上此注解 @EnableCaching  //开启缓存注解  其它操作，可以自己查看API，这里只做接单演示
  上面的那种方法，个人认为是比较快的，但是没有redis给力，redis是一个非常强大缓存中间件，它还可以做消息的发布订阅，分布式事务，锁等管理，可谓是非常的强大，后续我会写一篇专注于redis的文档。
  
     pom依赖
     <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-redis</artifactId>   
    </dependency>
    
      redis连接配置，配置在application.properties 
      #使用redis存储中的第一个库,redis的默认排序第一个为0，共有16个库
      spring.redis.database=0

     #redis要连接的ip
    spring.redis.host=127.0.0.1

     #端口号 
    spring.redis.port=6379

     #密码，可以在redis.windows.conf中 requirepass 行配置 
    spring.redis.password=123

    #redis连接池的最大连接数量
    spring.redis.jedis.pool.max-active=1000

    #连接池的最大阻塞时间
    spring.redis.jedis.pool.max-wait=-1

    #连接池的最大空闲个数
    spring.redis.jedis.pool.max-idle=10

    #连接池的最小空闲连接
     spring.redis.jedis.pool.min-idle=2

    #超时后，连接时间
    spring.redis.timeout=5000
    
    使用redis做基本缓存处理 在开发使用的时候，将redis注入dao层，本项目代码，只做测试所用 
    @Autowired
	private StringRedisTemplate template;
	
		if(template.opsForValue().get("tt")!=null){ redis里面的接口大多存在于 DefaultValueOperations<K, V>此类中，template.opsForValue()方法就是获取DefaultValueOperations 实例对象
		System.out.println("从缓存查出数据");
			return template.opsForValue().get("tt"); 此处查询返回的string字符串，如果想使用对象，可以json工具类，自行转换
		}else {
			template.opsForValue().set("tt", User2Service.findA().toString());
		}
		
		上面只演示了最简单的缓存操作，其它操作可以在 private StringRedisTemplate template; 模板类中查看相关API
	

  ### 扩展操作
   >  定时任务
   SpringBoot对定时任务的操作，也做了封装，开发非常快速，是真的方便，jar包都不需要导入
         
	  在Spring Boot的主类中(run方法的类)加入@EnableScheduling注解，启用定时任务的配置
	  @Component
     public class ScheduledTasks {
            private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            @Scheduled(fixedRate = 5000)   5秒执行一次，演示所用，具体使用场景，查看相关API zone="0 0 12 * * ?" 表示每天中午12点触发。。
            public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
        }
     }
   
   > SpringBoot也集成了Async操作
          
	  可以跳过某个执行方法特别长的等待时间，注解式的操作，比spring代码要精简很多
	     @Async 主需要在service层加入此注解，然后在run方法所在的类上加入@EnableAsync //开启异步注解，即可使用
	public void async(){
		System.out.println("正在执行.....循环");
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("i--------------"+i);
		}
		System.out.println("循环执行完毕......");
	}  这样就可以实现上面方法的异步调用，当这个方法执行时间过长的时候，也不会影响其他方法的异步执行。就是这么简单的配置
	
  > SpringBoot多环境配置
    SpringBoot启动的时候，默认加载的application.preperties 前缀的配置文件，当我们需要测试环境和开发环境的时候我们只需要
                   
		    spring.profiles.active=dev  表示加载开发环境
   application-dev.properties：开发环境
   application-test.properties：测试环境
   application-prod.properties：生产环境
     
  > SpringBoot yml 使用
      创建yml文件 前缀必须是application.yml才会被加载
        
	 yml对比properties() 两个文件看个人喜好。感觉没撒大区别
 * yml的数据结构是 树形式的
 * yml的数据格式和json的格式很像，都是K-V格式，并且通过”:”进行赋值.properties通过.和=
 * 每个key的冒号后面一定都要加一个空格； 不加会报错
            server:
              port:  8090   
       context-path: /dist
 	 
  > 热部署
   
         #表示放弃模板缓存，这样在修改模板页面的时候，可以实时看到效果
         spring.thymeleaf.cache=true  
        #表示开启热部署,配置文件的热部署，配置文件修改后，可以实时看到效果
	spring.devtools.restart.enabled=true   
	#表示在src/main/java下的java代码有改动之后就会重启项目，热部署的重启比手动重启要快
	spring.devtools.restart.additional-paths=src/main/java
	
 > 发布打包
 
    可以在代码目录下使用 mvn  package 进行打包，也可以使用eclispe进行打包处理
      打包之后我们可以在jar包所在的具体目录 使用 java –jar   xxx.jar 进行运行
      特别注意，这样运行的时候我们需要指定一个启动类
      	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<maimClass>com.dist.demo.SpringBootAicdApplication</maimClass> 表示启动的时候默认run的类
				</configuration>
			</plugin>
		</plugins>
	</build>
	
  > 优化
     1.jvm参数调优
     2.扫包优化(启动项目优化)
     3.默认tomcat容器改为undertow
     tomcat吞吐量5000 undertow8000
     
     1)jvm参数调优
     调优策越：初始化堆内存和最大堆尽量相同，可以减少GC回收次数（吞吐量由堆值决定）
      -XX:+PrintGCDetails -Xmx32M -Xms32M（越大越好，128,256,512）
      打印详细gc日志，最大堆内存为32M，初始化堆内存为32m。会导致gc回收特别频繁
       命令行启动配置方式: java -server -Xms32M -Xmx32M -jar xxx.jar                 
     
    2) 原理解决：
     @SpringbootApplicaiton 注解底层会调用 @ComponentScan("com.dist.*)  这样项目启动的时候扫包会扫描同级包和递归下面的子类包。影响项目启动时间。可以修改为 @ComponentScan(basePackages = {"具体扫描的包1","具体要扫描的包2"})，@EnableAutoConfiguration 配合使用代替@SpringbootApplication
     
     3)修改web容器:
      排除tomcat依赖
      <exclusions>
      <exclusion>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-tomcat</artifactId>
      </exclusion>
      </exclusions>
     加入新依赖 undertow
    <dev>
    <groupId>org.springframework.boot</<groupId>>
     <artifactId>spring-boot-starter-undertow</artifactId>
    </dev>
  
