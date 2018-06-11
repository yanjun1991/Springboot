# Springboot
Springboot 学习搭框架，集成以前学过的知识点。
项目配置在application.properties配置文件中
项目日志处理采用log4j2配置的方式，用到的jar包如下：注意这里移除了springboot自带的logback日志
 <!-- 去除spring-boot默认日志依赖包 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <!-- 去除对默认日志的依赖 -->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
项目集成Spring AOP 处理日志
项目处理热部署，修改Java文件可以实现自动重启
项目集成jpa
项目采用是mysql数据库
除此之外项目还添加了lombok，以及redis、Junit测试
待续。。。集成quartz定时任务

        
