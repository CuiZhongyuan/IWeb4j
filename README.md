# IWeb4j项目基于po模型开发测试主要用于回归测试

包目录说明
- base包：负责存放公用的操作方法
- dto包：存放数据实体对象，对应excel表头字段或数据库字段
- listener包: testng监听类
- page包：页面元素操作和执行测试数据(包含excel数据驱动和数据库驱动)
- testcase包:业务逻辑用例编写
- utils包:工具类

# IWeb4j 项目思想
- IWeb项目的测试用例使用数据驱动测试，当下数据驱动使用最多的无非是excel、yaml、json、数据库驱动。
目前实现excel、mysql数据源驱动。excel通过开源的Easypoi文档导入导出工具完成，mysql数据库通过SpringDataJPA实现数据驱动方式。
- 页面元素定位使用封装的关键字库测试，通过dom4j解析xml方式实现。
- 整体项目属于混合模型开发测试

# 项目主要技术
 
### 该项目主要技术

![](https://upload-images.jianshu.io/upload_images/16753854-66edc0876097e425.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

技术  | 用途  | 备注
:----------- | :----------- |-----------
springboot| 后端项目框架  | 后期可扩展平台
lombok| java实用工具  | 通过注解减少代码量
testNG| 测试用例执行套件| 控制测试流程
allure| 魅力的测试报告工具|测试报告
Easypoi| 文档的导入导出工具|数据驱动，读写excel测试用例
dom4j| xml的dom树解析工具|元素关键字的实现载体
log4j2| 日志工具|记录系统日志
selenium| 通过WebDriver API 操作页面|模拟真实用户操作
...| ...|...

# EXCEL数据驱动使用easypoi实现
- easypoi注解说明
![](https://upload-images.jianshu.io/upload_images/16753854-65458befe4d5b7e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 项目使用Spring类注解说明

- 由于该项目是测试项目不像发开那样把每层区分Controller层、Service层、dao层
- 该项目除了dao层用@Repository其它使用通用注解@Component

- 总结
  - @Component, @Service, @Controller, @Repository是spring注解，注解后可以被spring框架所扫描并注入到spring容器来进行管理
  - @Component是通用注解，其他三个注解是这个注解的拓展，并且具有了特定的功能
  - @Repository注解在持久层中，具有将数据库操作抛出的原生异常翻译转化为spring的持久层异常的功能。
  - @Controller层是spring-mvc的注解，具有将请求进行转发，重定向的功能。
  - @Service层是业务逻辑层注解，这个注解只是标注该类处于业务逻辑层。
  
  用这些注解对应用进行分层之后，就能将请求处理，义务逻辑处理，数据库操作处理分离出来，为代码解耦，也方便了以后项目的维护和开发。
