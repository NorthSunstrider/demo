说明:整合springboot和swagger的demo
步骤
1.引入springfox的swagger jar包
2.编写Swagger的配置启动类，可以是任何类名，在任何spring能扫描到的路径下
一般是Swagger2的类在springboot启动类同层下
3.在接口中应用@Api @ApiOperation等注释编写接口文档需要的对应说明
4.启动项目后，在http://localhost:8080/demo-springboot-swagger/swagger-ui.html即可访问
