/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/04/2020 16:52:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_article_content
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_content`;
CREATE TABLE `tbl_article_content`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `article_id` bigint(40) NOT NULL COMMENT '文章id',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '文章创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '文章修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 130 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article_content
-- ----------------------------
INSERT INTO `tbl_article_content` VALUES (52, '![SpringLogo.jpg][1]\n# 自动扫描\n\n```c\n组件扫描（component scanning）：Spring会自动发现应用上下文 中所创建的bean。 自动装配（autowiring）：Spring自动满足bean之间的依赖。\n```\n\n\n\n## 注解\n\n`@Component` **创建可被发现的bean**\n\n`@ContextConfiguration` **扫描配置和Springxml的<context:component-scan> 一样效果**\n\n`@ComponentScan` **启用了组件扫描**\n\n`@Autowired   `  **自动装配Bean**\n\n## 代码清单\n\ninterface \n\n```java\npublic interface CompactDisc {\n    void play();\n}\n\n```\n\nSgtPeppers **implements** CompactDisc\n\n```java\n@Component/*声明组件类*/\npublic class SgtPeppers implements CompactDisc {\n    @Override\n    public void play() {\n        System.out.println(\"播放:愛にできることはまだあるかい\");\n    }\n}\n```\n\nTest\n\n```java\n@RunWith(SpringJUnit4ClassRunner.class)\n@ContextConfiguration(classes = main.class)/*扫描组件*/\n@ComponentScan /*启用组件扫描*/\npublic class main {\n    @Autowired\n    private CompactDisc cd;\n    @Test\n    public void main()\n    {\n        cd.play();\n    }\n}\n```\n\n\n\n# JavaConfig\n\n```c\n方便第三方库加载,因为有时候别人的代码是不能修改所以也是需要手动装配Bean。\n《Spring实战4》作者：[美] Craig Walls 沃尔斯 推荐的是自动化。\n```\n\n\n\n## 注解\n\n`@Configuration` **声明spring配置类  **\n\n`@Bean`    **spring会生成一个bean实例 **\n\n## 代码清单\n\nSgtPeppers\n\n```java\npublic class SgtPeppers implements CompactDisc {\n    @Override\n    public void play() {\n        System.out.println(\"播放:愛にできることはまだあるかい\");\n    }\n}\n\n```\n\nAppConfig\n\n```java\n@Configuration\npublic class AppConfig {\n    @Bean(name = \"SgtPeppers\")\n    public SgtPeppers SgtPeppers()\n    {\n        return new SgtPeppers();\n    }\n}\n```\n\nTest\n\n```java\n@RunWith(SpringJUnit4ClassRunner.class)\n@ContextConfiguration(classes = main.class)\npublic class main {\n    @Test\n    public void main() {\n        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();\n        ctx.register(AppConfig.class);\n        ctx.refresh();\n        SgtPeppers cd=(SgtPeppers) ctx.getBean(\"SgtPeppers\");\n        cd.play();\n    }\n}\n```\n\n\n\n# XML配置Bean\n\n```\n在使用XML为Spring装配bean之前，需要创建一个新的配置规范。 在使用JavaConfig的时候，这意味着要创建一个带 有@Configuration注解的类，而在XML配置中，这意味着要创建 一个XML文件，并且要以<beans>元素为根。\n```\n\n`<constructor-arg> `元素为构造器\n\n`<property>`  Setter方法所提供的功能 \n\n## Spring XML 基础配置\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<beans xmlns=\"http://www.springframework.org/schema/beans\"\n       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\">\n    	\n</beans>\n```\n\n\n\n## 声明一个Bean\n\n**如果不配置id Spring 会帮生成  main.SgtPeppers#0 ，“#0”是一个计数的形 式，用来区分相同类型的其他bean**\n\n```xml\n<bean id=\"SgtPeppers\" class=\"main.SgtPeppers\"></bean> \n```\n\n\n\n\n\n\n  [1]: https://www.error0.cn/usr/uploads/2019/11/2998229092.jpg', 174, '2020-01-10 15:49:58', '2020-01-10 15:49:58');
INSERT INTO `tbl_article_content` VALUES (51, '## 题目描述\n\n一共有n（n≤20000）个人（以1--n编号）向佳佳要照片，而佳佳只能把照片给其中的k个人。佳佳按照与他们的关系好坏的程度给每个人赋予了一个初始权值W[i]。然后将初始权值从大到小进行排序，每人就有了一个序号D[i]（取值同样是1--n）。按照这个序号对10取模的值将这些人分为10类。也就是说定义每个人的类别序号C[i]的值为(D[i]-1) mod 10  +1，显然类别序号的取值为1--10。第i类的人将会额外得到E[i]的权值。你需要做的就是求出加上额外权值以后，最终的权值最大的k个人，并输出他们的编号。在排序中，如果两人的W[i]相同，编号小的优先。\n\n## 输入格式\n\n第一行输入用空格隔开的两个整数，分别是n和k。\n\n第二行给出了10个正整数，分别是E[1]到E[10]。\n\n第三行给出了n个正整数，第i个数表示编号为i的人的权值W[i]。\n\n## 输出格式\n\n只需输出一行用空格隔开的k个整数，分别表示最终的W[i]从高到低的人的编号。\n\n## 输入输出样例\n\n**输入 #1** \n\n```\n10 10\n1 2 3 4 5 6 7 8 9 10\n2 4 6 8 10 12 14 16 18 20\n```\n\n**输出 #1** \n\n```c\n10 9 8 7 6 5 4 3 2 1\n```\n\n\n\n## 代码\n\n```c++\n#include<iostream>\n#include <iostream>\n#include<algorithm>\nusing namespace std;\nstruct person {\n	int d; /*序号*/\n	int w; /*权值*/\n	int c;/*分类*/\n} ;\n\n/*权值相同 优先取序号小的*/\nbool cmp(person a, person b)\n{\n	if (a.w == b.w)\n	{\n		return a.d < b.d;\n	}\n	else return a.w > b.w;\n}\nperson p[20001];\nint e[10] = { 0 };\n\nint main() {\n\n	int n,  k;\n	cin >> n >> k;\n	for (int i =0; i <10; i++)\n	{\n		cin>>e[i];\n	}\n	for (int i = 0; i <n; i++)\n	{\n		cin >> p[i].w;\n		p[i].d = i + 1;\n	}\n	sort(p, p + n,cmp);\n	for (int i = 0; i < n; i++)\n	{\n		/*分类 加上额外权值*/\n		p[i].c = i%10; \n		p[i].w += e[p[i].c];\n	}\n	sort(p, p + n, cmp);\n	for (int i = 0; i <k; i++)\n		cout << (p[i].d)<<\" \";\n	return 0;\n}\n```\n\n', 173, '2020-01-10 15:49:00', '2020-01-10 15:49:00');
INSERT INTO `tbl_article_content` VALUES (50, ' 存储过程（Stored Procedure）是一种在数据库中存储复杂程序，以便外部程序调用的一种数据库对象。\n\n 存储过程是为了完成特定功能的SQL语句集，经编译创建并保存在数据库中，用户可通过指定存储过程的名字并给定参数(需要时)来调用执行。\n\n 存储过程思想上很简单，就是数据库 SQL 语言层面的代码封装与重用。 \n\n \n\n##  优点 \n\n-  存储过程可封装，并隐藏复杂的商业逻辑。\n-  存储过程可以回传值，并可以接受参数。\n-  存储过程无法使用 SELECT 指令来运行，因为它是子程序，与查看表，数据表或用户定义函数不同。\n-  存储过程可以用在数据检验，强制实行商业逻辑等。\n\n##  缺点\n\n-  存储过程，往往定制化于特定的数据库上，因为支持的编程语言不同。当切换到其他厂商的数据库系统时，需要重写原有的存储过程。\n-  存储过程的性能调校与撰写，受限于各种数据库系统。\n\n\n\n## staffs表结构\n\n| id   | name | age  |\n| ---- | ---- | ---- |\n| 1    | 李虎 | 13   |\n| 2    | 张三 | 14   |\n| 3    | 李四 | 13   |\n\n\n\n------\n\n\n\n```mysql\ndelimiter $$ 　#将语句的结束符号从分号;临时改为两个$$(可以是自定义)\nCREATE PROCEDURE delete_by_id(IN id INTEGER) ##定义存储过程和传参\nBEGIN  #存储过程开始\nDELETE FROM staffs WHERE id=id;\nEND$$#存储过程开始\ndelimiter;　#将语句的结束符号恢复为分号\n```\n\n\n\n------\n\n```mysql\nCALL delete_by_id(2)\n```\n\n------\n\n\n\n| id   | name | age  |\n| ---- | ---- | ---- |\n| 1    | 李虎 | 13   |\n| 3    | 李四 | 13   |\n\n', 172, '2020-01-10 15:45:00', '2020-01-10 15:45:00');
INSERT INTO `tbl_article_content` VALUES (129, '# 什么是线段树\n\n线段树也称区间树,更多的用于查询数据区间的值。\n\n线段树和二分搜索树相似也是二叉树，但具体的现实是不一样的。\n\n线段树对于需要**频繁动态更新**和查询的数据是比普通算法是更有利。(当然lazy我暂时留个坑有空再填)\n\n| 时间复杂度 | 查询    | 修改    |\n| ---------- | ------- | ------- |\n| 普通算法   | O(1)    | O(n)    |\n| 线段树     | O(logn) | O(logn) |\n\n下方图以数值区间**求和**为例，每个节点的数值的和都平坦到子节点上。所以查询的时候只需要访问到区间节点就好了，而不需要每个值都访问。\n\n`例如：数组A[0,1,2,3,4,5,6,7]` 查询0到3的区间值，只需要访问到根节点的**右子节点**就可以拿到区间值。\n\n当然线段树不只是可以拿来做区间求和也可以拿来比较**最大值**或**最小值**等操作。\n\n**线段树的构建样例图：**\n![线段树.png][1]\n\n# 用数组实现线段树\n\n- 用数组实现**线段树**需要用把它当作**满二叉树**存储。\n- 对于满二叉树h层一共有2^h-1个节点。\n- 最后一层(h-1) 有2^(h-1)个节点。\n- 如果n=2^k 只需要2n的空间,但是最坏的情况为n=2^k+1 所以需要开4n的空间 （右边高度比左边高度多1层）\n\n## 例子：计算区间值的和\n\n\n\n![普通数组.png][2]\n\n构建算法大概为从中间分开 为左右子节点。（同时计算或比较等操作）\n\n**左子节点**：父节点下标*2+1\n\n**右子节点**：父节点下标*2+2\n\n![线段树构建.png][3]\n\n## 构建线段树\n\n```java\n  static int data[] = {0, 1, 2, 3, 4}; // 原数据\n  static int Tree[] = new int[data.length * 4]; // 线段树\n\n  public static void main(String[] args) {\n      \n    build(0, 0, data.length - 1);\n    System.out.println(Arrays.toString(Tree));\n  }\n\n  private static void build(int NodeIndex, int l, int r) {\n    // 当区间只有一个\n    if (l == r) {\n      Tree[NodeIndex] = data[l];\n      return;\n    }\n    // 计算左右子节点的索引\n    int LeftIndex = 2 * NodeIndex + 1;\n    int RiIndex = 2 * NodeIndex + 2;\n\n    //把数值区间二分\n    int mid = l + (r - l) / 2;\n    build(LeftIndex, l, mid);\n    build(RiIndex, mid + 1, r);\n\n    // 当前节点等于子节点的和。\n    Tree[NodeIndex] =Tree[LeftIndex]+Tree[RiIndex] ;\n  }\n    \n    \n 输出结果:[10, 3, 7, 1, 2, 3, 4, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n```\n\n## 区间查询\n![线段树查询.png][4]\n\n\n\n```java\n private static int query(int NodeIndex,int l,int r,int start, int end) {\n\n      if((l==start)&&(r==end)) {\n          return Tree[NodeIndex];\n      }\n\n      // 计算左右子节点的索引\n      int LeftIndex = 2 * NodeIndex + 1;\n      int RiIndex = 2 * NodeIndex + 2;\n\n      int mid = l + (r - l) / 2;\n       //如果要查询的起始位置在右边范围内只需要去右边查询就好了,\n      // 同理如果区间结束位置只在左边范围内就只需要去左边查询。\n      if(start>=mid+1)\n      {\n          return query(RiIndex,mid+1,r,start,end);\n      }\n      else if(end<=mid)\n      {\n          return query(LeftIndex,l,mid,start,end);\n      }\n        // 当左右子树都存在区间时，查询的区间值也要分开去子树查询\n      return query(LeftIndex,l,mid,start,mid)+query(RiIndex,mid+1,r,mid+1,end);\n  }\n\n```\n\n## 更新数据\n\n```java\n\n/*\n* index 需要更新的索引位置\n* value 需要更新的值\n* NodeIndex 当前节点\n* l 区间起始\n* r 区间结束\n*/\nprivate static void update(int index,int value,int NodeIndex,int l,int r) {\n\n        if(l==r)\n        {\n            //更新原数据\n            data[index]=value;\n            //更新树节点\n            Tree[NodeIndex]=value;\n        }\n        else {\n\n            int mid = l + (r - l) / 2;\n            int LeftIndex = 2 * NodeIndex + 1;\n            int RiIndex = 2 * NodeIndex + 2;\n\n            //如果节点属于在右边范围就去右边更新子节点，属于左边就去左边更新。\n            if(index>=mid+1)\n            {\n                update(index,value,RiIndex,mid+1,r);\n            }\n            else {\n                update(index,value,LeftIndex,l,mid);\n            }\n            //维护更新父节点\n            Tree[NodeIndex]=Tree[LeftIndex]+Tree[RiIndex];\n        }\n  }\n\n```\n\n\n\n# 线段树Lazy\n\n如果用普通方法去频繁更新值的话会造成 时间复杂度变成O(N logN)。其实每次更新没有必要去更新到子节点 ,只需要更新到要查询的区间即可（偷懒），其余存放在另一个地方等查询超出未更新的结点再去更新。这样时间复杂度是O(logn)的。（暂时没学留个坑到时再填。）\n\n\n  [1]: https://www.error0.cn/usr/uploads/2020/03/1766789269.png\n  [2]: https://www.error0.cn/usr/uploads/2020/03/4296720.png\n  [3]: https://www.error0.cn/usr/uploads/2020/03/1034061157.png\n  [4]: https://www.error0.cn/usr/uploads/2020/03/1222898058.png', 256, '2020-04-15 15:59:29', '2020-04-15 15:59:29');

-- ----------------------------
-- Table structure for tbl_article_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_info`;
CREATE TABLE `tbl_article_info`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `traffic` int(10) NOT NULL DEFAULT 0 COMMENT '文章访问量',
  `top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 257 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article_info
-- ----------------------------
INSERT INTO `tbl_article_info` VALUES (172, ' MySQL 存储过程', 108, 0);
INSERT INTO `tbl_article_info` VALUES (173, 'P1583 魔法照片', 73, 0);
INSERT INTO `tbl_article_info` VALUES (174, 'SpringBean装配', 195, 0);
INSERT INTO `tbl_article_info` VALUES (256, '线段树', 6, 1);

-- ----------------------------
-- Table structure for tbl_article_sort
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_sort`;
CREATE TABLE `tbl_article_sort`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `sort_id` bigint(40) NOT NULL COMMENT '分类id',
  `article_id` bigint(40) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of tbl_article_sort
-- ----------------------------
INSERT INTO `tbl_article_sort` VALUES (117, 140, 256);
INSERT INTO `tbl_article_sort` VALUES (41, 124, 174);
INSERT INTO `tbl_article_sort` VALUES (40, 123, 173);
INSERT INTO `tbl_article_sort` VALUES (39, 123, 172);

-- ----------------------------
-- Table structure for tbl_sort_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sort_info`;
CREATE TABLE `tbl_sort_info`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `number` tinyint(10) NOT NULL DEFAULT 0 COMMENT '该分类下的文章数量',
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '分类修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_sort_info
-- ----------------------------
INSERT INTO `tbl_sort_info` VALUES (124, 'Java', 1, '2019-12-22 10:10:07', '2020-04-15 15:58:31');
INSERT INTO `tbl_sort_info` VALUES (123, 'C++', 2, '2019-12-22 10:10:17', '2020-04-15 16:00:46');
INSERT INTO `tbl_sort_info` VALUES (140, '数据结构', 1, '2020-04-15 15:59:16', '2020-04-15 16:00:37');

-- ----------------------------
-- Table structure for user_admin
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `last_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_admin
-- ----------------------------
INSERT INTO `user_admin` VALUES (1, 'admin', '202cb962ac59075b964b07152d234b70', '2020-04-15 07:55:38');
INSERT INTO `user_admin` VALUES (2, 'test', '202cb962ac59075b964b07152d234b70', '2020-03-12 13:57:15');

SET FOREIGN_KEY_CHECKS = 1;
