# 影荐人电影推荐网站

### 介绍
本项目是一款可以浏览、收藏电影信息，获取电影推荐，实时记录与分享电影影评的电影推荐网站。
### 使用技术
SpringMVC+JPA+Thymeleaf，推荐算法使用Keras实现NN，由Flask封装成Rest API

### 环境配置

服务端：SpringBoot 2.3.1, Maven, MySql

算法：Keras, Tensorflow, Pandas, Scikit learn, Flask

爬虫：Pandas, Numpy

### 安装教程
1.  下载[部署包](https://github.com/yfoaix/MovieRecommender-Deploy) 
2.  运行CoverImage文件夹中的getimage.py，爬取封面图片
3.  复制img中图片至yingjianren\src\main\resources\static\img
4.  Mysql中导入.sql数据库
5.  替换yingjianren/src/main/resources/application.properties中的数据库连接字符串(spring.datasource.url、username、password)
6.  运行Algorithm文件夹中的算法movie.py
7.  运行服务端

### 网站截图
![image](https://github.com/yfoaix/MovieRecommend/blob/master/DemoImages/home.png)
![image](https://github.com/yfoaix/MovieRecommend/blob/master/DemoImages/list.png)
![image](https://github.com/yfoaix/MovieRecommend/blob/master/DemoImages/genres.png)
![image](https://github.com/yfoaix/MovieRecommend/blob/master/DemoImages/movieinfo.png)
![image](https://github.com/yfoaix/MovieRecommend/blob/master/DemoImages/comment.png)
![image](https://github.com/yfoaix/MovieRecommend/blob/master/DemoImages/login.png)
![image](https://github.com/yfoaix/MovieRecommend/blob/master/DemoImages/space.png)
