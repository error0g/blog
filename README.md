#  Blog

此项目的初衷是学习前后台交互的业务逻辑关系和巩固基础知识。

实习原因就没有继续维护下去了

## 项目结构



![](https://www.error0.cn/usr/uploads/2020/04/503109079.png)

![](https://www.error0.cn/usr/uploads/2020/04/844984281.png)

![](https://www.error0.cn/usr/uploads/2020/04/4173937583.png)

## 技术选型

开发环境：

| Name  | Version |
| ----- | ------- |
| JDK   | 1.8     |
| Mysql | 5.7     |
| IDEA  | 2020.1  |

前端

| Name                   | Version |
| ---------------------- | ------- |
| Jquery                 | 3.2.1   |
| Bootstrap              | 2.1.0   |
| Prism.js(代码高亮插件) | 2.7.0   |

后端：

| Name       | Version       |
| ---------- | ------------- |
| SpringBoot | 2.2.1.RELEASE |
| Mybatis    | 2.1.0         |
| Swagger2   | 2.7.0         |

### 

### 运行项目

请按照以下流程运行项目：

1. 检查自己本地的开发环境是否与我的一致
2. 创建名称为`blog`的数据库，并执行`db.sql`
3. 修改项目`src\main\resources\application.properties`中关于数据库的配置
4. 保证Maven已经完全加载了项目所需的依赖
5. 默认登录用户名和密码：`username: admin; passsword: 123456`