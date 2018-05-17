说明：dubbo项目的demo，api、provider、consumer三个项目为完整示例
api项目定义抽象接口
provider完成实现，注册并暴露服务
consumer调用服务

一开始根据dubbo wiki写demo时，根据wiki编写一直不成功，几天后还是没解决，然后决定从头开始写，又成功了
原来是wiki也更新了，之前wiki上写的代码，其引用的头文件是
http://dubbo.apache.org/schema/dubbo 
http://dubbo.apache.org/schema/dubbo/dubbo.xsd

更新后，其引用的头文件是
http://code.alibabatech.com/schema/dubbo        http://code.alibabatech.com/schema/dubbo/dubbo.xsd

使用新的头文件引入就正确了。
之前一直报错，dubbo.xsd文件解析有问题，尝试了各种办法都没解决。

现在demo中使用的服务注册是使用的广播机制，这是为了简便，生存环境应使用zookeeper等组件实现。